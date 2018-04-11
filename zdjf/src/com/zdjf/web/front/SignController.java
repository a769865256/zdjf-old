package com.zdjf.web.front;

import com.zdjf.components.events.FundEvent;
import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.components.mobile.SignMathRandom;
import com.zdjf.components.mobile.TenRandom;
import com.zdjf.components.mobile.ThousandRandom;
import com.zdjf.domain.activity.Sign;
import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.user.User;
import com.zdjf.service.activity.ISignService;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.DateUtil;
import com.zdjf.util.StrUtils;
import com.zdjf.util.UrlConstant;
import com.zdjf.webservice.express.JsonReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("")
public class SignController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISignService signService;

    @Autowired
    private IProductBuyService productBuyService;

    @Autowired
    private ICoinStreamService coinStreamService;

    @Autowired
    private IUserFundStatService userFundStatService;

    @Autowired
    private ApplicationContext applicationContext;


    /**
     * 至签到页面
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/toSign", method= RequestMethod.GET)
    public String toSign(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("coin",request.getParameter("coin"));//签到后获取的正经值
        model.addAttribute("doubleStatus",request.getParameter("doubleStatus"));//正经值是否双倍 0否1是
        model.addAttribute("errMsg","1".equals(request.getParameter("errMsg"))?"今日签到机会已用光":"");
        String user_name = "";
        String domainName = request.getServerName();
        if(UrlConstant.MOBILE_URL.equals(domainName)){  //判断是否来自手机端 手机端返回手机页面
            user_name = request.getParameter("phone");
        }else{
            user_name = BrowseUtil.getCookie(request, response);
        }

        User user = null;
        if (StrUtils.emptyJudge(user_name)) {//用户已登录
            user = userService.selectForObjectByPhone(user_name);//用户名即手机号
            //当日签到记录
            Sign sg = new Sign();
            sg.setUser_id(user.getId());
            sg.setCreate_date(DateUtil.formatDate(new Date()));
            sg.setStatus(1);
            List<Sign> list = signService.selectForList(sg);
            //当日已签到次数
            int signUsedNum = list.size();
            //用户当日所有签到机会，有基础签到机会一次
            int signTotalNum = user.getSign_num();
            //分享获得签到
            int shareNum = 0;
            //邀请好友获得签到
            int inviteNum = 0;
            if (signUsedNum == 0) {//当日未签到
                model.addAttribute("currSignNum",1);//有基础签到机会一次
                if (signTotalNum == 0) {//未获得额外签到机会
                    shareNum = 0;
                    inviteNum = 0;
                } else if (signTotalNum>0 && user.getSign_data()!=null &&!"2000-12-20".equals(DateUtil.formatDate(user.getSign_data(),"yyyy-MM-dd"))) {
                    shareNum = 1;
                    inviteNum = signTotalNum-1;
                } else if (signTotalNum>0 && (user.getSign_data()== null || "2000-12-20".equals(DateUtil.formatDate(user.getSign_data(),"yyyy-MM-dd")))) {
                    shareNum = 0;
                    inviteNum = signTotalNum;
                }
                signTotalNum = user.getSign_num() + 1;
            } else {//当日已签到
                model.addAttribute("currSignNum",0);
                if (signTotalNum == 0) {//未获得额外签到机会
                    shareNum = 0;
                    inviteNum = 0;
                } else if (signTotalNum>0 && user.getSign_data()!=null &&!"2000-12-20".equals(DateUtil.formatDate(user.getSign_data(),"yyyy-MM-dd"))) {
                    shareNum = 1;
                    inviteNum = signTotalNum-1;
                } else if (signTotalNum>0 && (user.getSign_data()== null || "2000-12-20".equals(DateUtil.formatDate(user.getSign_data(),"yyyy-MM-dd")))) {
                    shareNum = 0;
                    inviteNum = signTotalNum;
                }
            }

            model.addAttribute("signTotalNum",signTotalNum);
            model.addAttribute("shareNum",shareNum);
            //邀请好友注册成功获得签到
            model.addAttribute("inviteNum",inviteNum);
            model.addAttribute("signTotalNum",signTotalNum);
        }

        //最近签到用户,按签到获取的正经值、时间倒序
        List<Map<String,Object>> list = signService.selectForLatestSignMap();
        model.addAttribute("latestSignList",list);
        //当月签到获取的正经值在前八位的用户
        List<Map<String,Object>> list2 = signService.selectForSignUserByCoins();
        //判断当前用户是否有排名1-8名
        String isRank = "0"; //是否上榜 0否，大于0时i+1表示当前排名
        for (int i=0; i<list2.size(); i++ ) {
            Map<String, Object> mapSignUser = list2.get(i);
            if(StrUtils.emptyJudge(user_name) && user_name.equals(mapSignUser.get("phone").toString())) {
                isRank = String.valueOf(i+1);
            }
        }
        model.addAttribute("frontEightSignUsers",list2);
        model.addAttribute("isRank",isRank);
        model.addAttribute("user",user);

        if(domainName.equals(UrlConstant.MOBILE_URL)){  //判断是否来自手机端 手机端返回手机页面
            model.addAttribute("phone",user_name);
            return "front/m/new_sign";
        }else{
            return "front/sign";
        }

    }

    /**
     * 签到获取正经值奖励
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/sign", method = RequestMethod.GET)
    public String sign(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String domainName = request.getServerName();
        String user_name = "";
        String reg_source = "0";//签到来源:1.web 2.ios 3.安卓
        if(UrlConstant.MOBILE_URL.equals(domainName)){  //判断是否来自手机端 手机端返回手机页面
            user_name = request.getParameter("phone");
            reg_source = request.getParameter("reg_source");
        }else{
            user_name = BrowseUtil.getCookie(request, response);
            reg_source = "1";
        }
        //签到
        Sign sign  = new Sign();
        Date date = new Date();
        User user = userService.selectForObjectByPhone(user_name);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Double coin = SignMathRandom.PercentageRandom()*1.0;
        //判断是不是首次签到
        Sign week = new Sign();
        week.setUser_id(user.getId());
        week.setCreate_date(DateUtil.formatDate(date));
        //首次签到
        List<Sign> list = signService.selectForList(week);
        String doubleStatus = "0";
        if(list.size() == 0){
            //昨日发生的—代码需要优化
            ProductBuy buy = new ProductBuy();
            buy.setUser_id(user.getId());
            buy.setStatus(1);//已付款

            //  昨天
            Date yesterday = DateUtil.addDay(date, -1);
            String start = DateUtil.formatDateTimeS(DateUtil.dateBegin(yesterday));
            String end = DateUtil.formatDateTimeS(DateUtil.dateEnd(yesterday));

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("page_name","selectProductBuyYesterday");
            map.put("start_date", Timestamp.valueOf(start));
            map.put("end_date",Timestamp.valueOf(end));
            map.put("status",2);
            map.put("user_id",user.getId() + "");

            List<ProductBuy> listBuy = productBuyService.selectForList(map);

            Double totle = 0.0;


            for(int i = 0;i<listBuy.size();i++){
                totle += listBuy.get(i).getAmount();
            }

            //三选一
            if(totle>= Constants.SIGN_TOTLE_YESTERDAY_TEN){
                coin = coin* TenRandom.PercentageRandom();
            }else if(totle>=Constants.SIGN_TOTLE_YESTERDAY){
                coin = coin* ThousandRandom.PercentageRandom();
            }else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY||//周末所发
                    cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                coin = coin*2.0;
                doubleStatus = "1";
            }
        } else {
            if(user.getSign_num()>0){
                if (user.getSign_data() != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date oldDate = sdf.parse("2000-12-20");
                    user.setSign_date(oldDate);
                }
                int num = user.getSign_num();
                user.setSign_num(--num);
                userService.updateForObjectByPhone(user);
            }else{
                if(domainName.equals(UrlConstant.MOBILE_URL)){  //判断是否来自手机端 手机端返回手机页面
                    return "redirect:/toSign.action?errMsg=1&phone="+user_name;
                }else{
                    return "redirect:/toSign.action?errMsg=1";
                }
            }
        }

        sign.setCoin(coin);
        sign.setCreate_time(date);
        sign.setCreate_date(DateUtil.formatDate(date));
        sign.setPhone(user_name);
        sign.setUser_id(user.getId());
        sign.setReg_source(Integer.parseInt(reg_source));//签到来源:1.web 2.ios 3.安卓
        sign.setStatus(1);

        signService.save(sign);

        //签到
        applicationContext.publishEvent(new FundEvent(this,user.getId(),33,coin,
                userFundStatService,coinStreamService));
        if(UrlConstant.MOBILE_URL.equals(domainName)){  //判断是否来自手机端 手机端返回手机页面
            return "redirect:/toSign.action?coin="+coin+"&doubleStatus="+doubleStatus+"&phone="+user_name;
        }else{
            return "redirect:/toSign.action?coin="+coin+"&doubleStatus="+doubleStatus;
        }
    }

    /**
     * 获取用户签到信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getUserSignInfo", method = RequestMethod.POST)
    @ResponseBody
    public JsonReturn getUserSignInfo(HttpServletRequest request, HttpServletResponse response) {
        JsonReturn jr = new JsonReturn();
        String domainName = request.getServerName();
        String user_name = "";
        if(UrlConstant.MOBILE_URL.equals(domainName)){  //判断是否来自手机端 手机端返回手机页面
            user_name = request.getParameter("phone");
        }else{
            user_name = BrowseUtil.getCookie(request, response);
        }
        if (StrUtils.emptyJudge(user_name)) {//用户已登录
            //查询当前用户每天签到信息
            User user = userService.selectForObjectByPhone(user_name);
            List<Map<String,Object>> list = signService.selectForUserSignInfEveryDay(user.getId());
            jr.setStatus(100);
            jr.setContent(list);
        }
        return jr;
    }
}
