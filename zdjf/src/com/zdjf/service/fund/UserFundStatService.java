package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.dao.fund.IUserFundStatDao;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.fund.UserFundStatVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.util.JsonUtil;

@Service
public class UserFundStatService implements IUserFundStatService {
	private IUserFundStatDao userFundStatDao;
	@Override
	public Serializable save(UserFundStat userFundStat) {
		return userFundStatDao.save(userFundStat);
	}

	@Override
	public void delete(UserFundStat userFundStat) {
		userFundStatDao.delete(userFundStat);
	}

	@Override
	public void update(UserFundStat userFundStat) {
		userFundStatDao.update(userFundStat);
	}

	@Override
	public UserFundStat selectForObjectById(UserFundStat userFundStat) {
		return (UserFundStat) userFundStatDao.selectForObject("selectUserFundStatById", userFundStat);
	}

	@Override
	public void updateUserFundStatById(UserFundStat userFundStat) {
		userFundStatDao.update("updateUserFundStatById", userFundStat);
	}
	
	public void updateUserBalance(UserFundStat userFundStat){
		userFundStatDao.update("updateUserBalance", userFundStat);
	}
	
	public void updateUserBalance(Long id,String ip){
		String result = SinaUtil.queryBalance(id,ip,"");
		Map resultMap = JsonUtil.jsonToMap(result);
		try{
			Object ob = resultMap.get("data");
			if(ob != null){
				Map dataMap =  (Map) ob;
				if(dataMap!=null){			
					String balance =  dataMap.get("balance")+"" ;
					
					UserFundStat userFundStat = new UserFundStat();
					userFundStat.setUser_id(id);
					userFundStat.setBalance(Double.valueOf(balance));
					userFundStatDao.update("updateUserBalance", userFundStat);
				}		
			}
				
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(long id) {
		//TODO
		userFundStatDao.delete("deleteUserFundStatById", id);
	}

	@Override
	public Page page(Page page, UserFundStat userFundStat) {
		return userFundStatDao.page(page, userFundStat);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(UserFundStat userFundStat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(UserFundStat userFundStat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(UserFundStat userFundStat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(UserFundStat userFundStat) {
		// TODO Auto-generated method stub
		return null;
	}
	@Autowired
	public void setIUserFundStatDao(
			@Qualifier("userFundStatDao") IUserFundStatDao  userFundStatDao) {
		this.userFundStatDao = userFundStatDao;
	}

	@Override
	public UserFundStatVo load(UserFundStat userFundStat) {
		return (UserFundStatVo) userFundStatDao.reload(userFundStat);
	}

	@SuppressWarnings("unchecked")
	public List<UserFundStatVo> selectForList(UserFundStat userFundStat) {
		return (List<UserFundStatVo>) userFundStatDao.selectForList("selectUserFundStat", userFundStat);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> selectUserFundStatForList() {
		return (List<Map<String, Object>>) userFundStatDao.selectForList("selectUserFundStatForList");
	}
}
