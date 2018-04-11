package com.zdjf.service.front;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.advertise.IAdvertiseDao;
import com.zdjf.dao.notice.INoticeDao;
import com.zdjf.dao.product.IProductBuyDao;
import com.zdjf.dao.product.IProductDao;
import com.zdjf.domain.dto.Cache;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.domain.user.User;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.CacheManager;
import com.zdjf.util.StrUtils;

@Service
public class FrontService implements IFrontService {

	private IAdvertiseDao advertiseDao; 

	private INoticeDao noticeDao;

	private IProductDao productDao;

	private IProductBuyDao productBuyDao;

	private IUserService userService;

	/**
	 * 首页PC与手机端缓存加载
	 */
	public void reloadIndexCache(){
		Map hashMap = new HashMap();
		hashMap.put("web_site", "1");
		List advertiseList = advertiseDao.selectForList("selectIndexShowAdvertise",hashMap); //获取pc首页的banner广告位
		hashMap = new HashMap();
		hashMap.put("web_site", "1");
		hashMap.put("type", 1);
		List noticeList = noticeDao.selectForList("selectIndexShowNotice",hashMap);  //获取首页展示的公告位
		hashMap = new HashMap();
		hashMap.put("is_fresh", -1);
		hashMap.put("limit", 8);
		List productList = productDao.selectForList("selectProductForIndex",hashMap); //获取非新手标
		//2018-01-01 获取首页展示的媒体报道
		List newList = noticeDao.selectForList("selectNewsForIndex");
//		for(int i=0;i<productList.size();i++){
//			ProductVo product=(ProductVo) productList.get(i);
//			String photo=product.getPhoto();
//			if(photo.indexOf("www.zdjf.com")>0){
//				photo.replace("www.zdjf.com", "pctest.zdjfu.com");
//				product.setPhoto(photo);
//			}
//		}
		//        hashMap = new HashMap();
		//        hashMap.put("is_fresh", 1);
		//        hashMap.put("limit", 1);
		//        List freshProductList = productDao.selectForList("selectProductForIndex",hashMap); //获取新手标
		//        ProductVo freshProduct=(ProductVo) freshProductList.get(0);

		List<Map<String,Object>> productBuyList=(List<Map<String, Object>>) productBuyDao.selectForList("selectAmountByProductId");
		Map<String,Object> productBuyMap=new HashMap<String, Object>();
		for(int i=0;i<productBuyList.size();i++){
			Map<String,Object> map=productBuyList.get(i);
			productBuyMap.put(map.get("product_id").toString(), map.get("can_buy_money"));
		}
		//        if(productBuyMap.containsKey(freshProduct.getId().toString())){
		//            freshProduct.setCan_buy_money(freshProduct.getBalance()-Double.parseDouble(productBuyMap.get(freshProduct.getId().toString()).toString()));
		//        }else{
		//        	freshProduct.setCan_buy_money(freshProduct.getBalance());
		//        }
		for(int i=0;i<productList.size();i++){
			ProductVo product=(ProductVo) productList.get(i);
			String product_id=product.getId().toString();
			product.setSpeed((product.getMoney()-product.getSale_money())/product.getMoney()*100);
			if(productBuyMap.containsKey(product_id)){
				product.setCan_buy_money(product.getBalance()-Double.parseDouble(productBuyMap.get(product_id).toString()));
			}else{
				product.setCan_buy_money(product.getBalance());
			}
		}

		CacheManager.clearOnly("pc_index_cache");  //清除缓存
		CacheManager.clearOnly("m_index_cache");   //清除缓存
		Map cacheMap = new HashMap();
		cacheMap.put("advertiseList", advertiseList);
		cacheMap.put("noticeList", noticeList);
		cacheMap.put("productList", productList);
		cacheMap.put("newList",newList);
		Cache c1 = new Cache();
		c1.setKey("index_cache");
		c1.setValue(cacheMap);
		CacheManager.putCacheInfo("pc_index_cache", c1,1000*60*10);

		hashMap = new HashMap();
		hashMap.put("web_site", "2");
		advertiseList = advertiseDao.selectForList("selectIndexShowAdvertise",hashMap); //获取手机首页的banner广告位
		hashMap = new HashMap();
//		hashMap.put("web_site", "2");
//		hashMap.put("type", 1);
//		noticeList = noticeDao.selectForList("selectIndexShowNotice",hashMap);
		cacheMap = new HashMap();
		cacheMap.put("advertiseList", advertiseList);
		cacheMap.put("noticeList", noticeList);
		cacheMap.put("productList", productList);
		cacheMap.put("newList",newList);
		Cache c2 = new Cache();
		c2.setKey("index_cache");
		c2.setValue(cacheMap);
		CacheManager.putCacheInfo("m_index_cache", c2,1000*60*10);
	}

	public Page getProductPageList(Page p,Map map){
		return productDao.getProductPageList(p,map);
	}


	@Autowired
	public void setAdvertiseDao(
			@Qualifier("advertiseDao") IAdvertiseDao  advertiseDao) {
		this.advertiseDao = advertiseDao;
	}

	@Autowired
	public void setNoticeDao(
			@Qualifier("noticeDao") INoticeDao  noticeDao) {
		this.noticeDao = noticeDao;
	}

	@Autowired
	public void setProductDao(
			@Qualifier("productDao") IProductDao  productDao) {
		this.productDao = productDao;
	}

	@Autowired
	public void setUserService(
			IUserService  userService) {
		this.userService = userService;
	}

	@Autowired
	public void setProductBuyDao(
			@Qualifier("productBuyDao") IProductBuyDao  productBuyDao) {
		this.productBuyDao = productBuyDao;
	}

	@Override
	public String createHeader(HttpServletRequest request) {
		Cookie[] cookies=request.getCookies();
		String header="<div class='nav'><div class='pack'><span><i class='iconfont'>&#xe605;</i>客服电话:400-690-9898</span>"
				+"<div class='p_right'>";

		Boolean flag=false;
		String user_name="";
		if(null!=cookies){
			for(int i=0;i<cookies.length;i++){
				Cookie cookie=cookies[i];
				if(cookie.getName().equals("user_name")){
					user_name=cookie.getValue();
					flag=true;
				}
			}
		}
		if(flag){
			String name=user_name.substring(3, 7);
			String sname=user_name.replace(name, "****");
			header+="<span>"+sname+"</span>";
			header+="<span>消息(2)</span>";
		}
		header+= "<span><i class='iconfont'>&#xe6b8;</i>下载APP</span>"
				+"<span>帮助中心</span><span>新手指引</span>"
				+"</div></div></div>"
				+"<div class='bar'><div class='pack'><div class='l_pk'></div>"
				+"<div class='r_pk'><ul>"
				+"<li><a href='javascript:'>首页</a></li><li>"
				+"<a href='javascript:'>理财项目</a>"
				+"<div class='nav'>"
				+"<a href='javascript:'>债转专区</a>"
				+"<a href='javascript:'>直投专区</a>"
				+"</div></li><li>"
				+"<a href='javascript:'>信息披露</a>"
				+"<div class='nav'>"
				+"<a href='javascript:'>平台数据</a>"
				+"<a href='javascript:'>安全保障</a>"
				+"<a href='javascript:'>公司简介</a>"
				+"<a href='javascript:'>平台事迹</a>"
				+"<a href='javascript:'>公司动态</a>"
				+"<a href='javascript:'>联系我们</a>"
				+"</div></li><li>"
				+"<a href='mywealth/wealth.html'>我的财富</a>"
				+"<div class='nav'>"
				+"<a href='javascript:'>我的财富</a>"
				+"<a href='javascript:'>资金明细</a>"
				+"<a href='javascript:'>我的优惠</a>"
				+"<a href='javascript:'>账户设置</a>"
				+"<a href='javascript:'>邀请有礼</a>"
				+"<a href='javascript:'>消息中心</a>"
				+"</div></li></ul>"
				+"<div class='sign'>";
		if(!flag){
			header+="<a href='login.html'>登录签到</a>"
					+"<a href='javascript:'>注册有礼</a>";	
		}

		header+="</div></div></div></div>";
		return header;
	}

	@Override
	public User getUser(HttpServletRequest request) {
		Cookie[] cookies=request.getCookies();
		String user_name="";
		String name="";
		String sname="";
		String detail="";
		User user=null;
		Boolean flag=false;
		if(null!=cookies){
			for(int i=0;i<cookies.length;i++){
				Cookie cookie=cookies[i];
				if(cookie.getName().equals("user_name")){
					user_name=cookie.getValue();
					name=user_name.substring(3, 8);
					sname=user_name.replace(name, "*****");
					flag=true;
				}
			}
		}
		if(flag){
			user=userService.selectForObjectByPhone(user_name);
			user.setEncrypt_phone(sname);
		}
		return user;
	}

	@Override
	public void reloadFundsCache(Boolean flage,String lender_id) {
		CacheManager.clearOnly("fundsTimeJob");  //清除缓存
		Map cacheMap = new HashMap();
		cacheMap.put("isFundsOpen", flage);
		if(!lender_id.equals("")){
			cacheMap.put("lender_id", lender_id);
		}
		Cache c1 = new Cache();
		c1.setKey("fundsTimeJob");
		c1.setValue(cacheMap);
		CacheManager.putCacheInfo("fundsTimeJob", c1,1000*60*10);
	}

}
