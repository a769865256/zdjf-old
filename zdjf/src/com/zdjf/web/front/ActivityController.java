package com.zdjf.web.front;

import com.zdjf.components.events.FundEvent;
import com.zdjf.components.events.UserBenefitsEvent;
import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.domain.activity.AwardRec;
import com.zdjf.domain.fund.CoinStream;
import com.zdjf.domain.fund.Coupon;
import com.zdjf.domain.fund.Gift;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserCoupon;
import com.zdjf.domain.user.UserCouponVo;
import com.zdjf.domain.user.UserGift;
import com.zdjf.domain.user.UserGiftVo;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.ICouponService;
import com.zdjf.service.fund.IGiftService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.DateUtil;
import com.zdjf.util.StrUtils;
import com.zdjf.webservice.express.JsonReturn;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Description 活动类
 * @Copyright Copyright (c) 2016
 * @Company 杭州云翳网络科技有限公司
 * @Author Andrew
 * @Date 2018-03-12 10:55
 */
@Controller
@RequestMapping("")
@PropertySource(value= "classpath:project.properties")
public class ActivityController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IProductBuyService productBuyService;

    @Autowired
    private IUserCouponService userCouponService;

    @Autowired
    private IUserGiftService userGiftService;

    @Autowired
    private IGiftService giftService;

    @Autowired
    private ICouponService couponService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private IUserFundStatService userFundStatService;

    @Autowired
    ICoinStreamService coinStreamService;

    //新春活动参数
    @Value("${activity.start.date}")
    private String newYearActivityStartDate;

    @Value("${activity.end.date}")
    private String newYearActivityEndDate;

    @Value("${coupon.name}")
    private String newYearActivityCouponName;

    //女神节活动参数
    @Value("${activity4.start.date}")
    private String goddessActivityStartDate;

    @Value("${activity4.end.date}")
    private String getGoddessActivityEndDate;

    @Value("${gift4.name.first}")
    private String firstGoddessGiftName;

    @Value("${coupon4.name}")
    private String goddessCouponName;

    //愚人节活动参数
    @Value("${activity5.start.date}")
    private String foolsActivityStartDate;

    @Value("${activity5.end.date}")
    private String foolsActivityEndDate;

    @Value("${gift5.name.second}")
    private String foolsAwardGift;

    @Value("${coupon5.name}")
    private String foolsAwardCoupon;

    @Value("${fools.award.num}")
    private String awardNum;

    /**
     * @description banner到达新春活动页面 web端
     * @author Andrew
     * @date_time 2018-03-12 11:07
     * @param request
     * @param response
     * @param model
     * @return java.lang.String
     */
    @RequestMapping(value = "/activity/toNewYear", method = RequestMethod.GET)
    public String toNewYearActivity(HttpServletRequest request, HttpServletResponse response, Model model) {
        String user_name = BrowseUtil.getCookie(request, response);
        int flag = -1;//领取标示：0满足条件，未领取；1满足条件已领取;2不满足条件;3未登录
        if (StrUtils.emptyJudge(user_name)) {
            User user = userService.selectForObjectByPhone(user_name);
            //查询用户从平台上线到活动期间是否投资过（即是否满足领取条件）
            Map<String,Object> hashMap = new HashMap<>();
            hashMap.put("userId",user.getId());
            boolean isInvested = productBuyService.selectUserIsInvested(hashMap);
            if (!isInvested) {
                flag = 2;
            }
            //查询用户是否领取过奖励
            UserCoupon userCoupon = new UserCoupon();
            userCoupon.setUser_id(user.getId());
            userCoupon.setCoupon_name("新春加息");
            List<UserCouponVo> userCouponVos = userCouponService.selectForList(userCoupon);
            if ((userCouponVos == null || userCouponVos.size() == 0) && isInvested) {//没有获得过此奖励则发放
                flag = 0;
            }
            if (userCouponVos.size() > 0) {
                flag = 1;
            }
            model.addAttribute("flag","");
        } else {
            model.addAttribute("user",null);
            flag = 3;
        }
        model.addAttribute("flag",flag);
        //查询用户在活动时间范围内的投资排行
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("startDate",newYearActivityStartDate);
        hashMap.put("endDate",newYearActivityEndDate);
        List<Map<String, Object>> list = productBuyService.selectNewYearInvt(hashMap);
        model.addAttribute("investList",list);
        return "front/active/springFestival";
    }

    /**
     * @description 新春加息券领取接口
     * @author Andrew
     * @date_time 2018-02-02 11:07
     * @param request
     * @param response
     * @return com.zdjf.webservice.express.JsonReturn
     */
    @ResponseBody
    @RequestMapping(value = "/activity/getNewYearCoupon", method = RequestMethod.POST)
    public JsonReturn getNewYearCoupon(HttpServletRequest request, HttpServletResponse response) {
        JsonReturn jr = new JsonReturn();
        String user_name = "";
        String req_source = request.getParameter("reg_source");//必填
        if (!StrUtils.emptyJudge(req_source)) {
            jr.setStatus(101);
            jr.setContent("关键字段为空");
            return jr;
        }
        if(StrUtils.emptyJudge(req_source) && !"1".equals(req_source)){  //判断是否来自手机端
            user_name = request.getParameter("phone");
        }else{
            user_name = BrowseUtil.getCookie(request, response);
        }
        if (!StrUtils.emptyJudge(user_name)) {
            jr.setStatus(102);
            jr.setContent("用户未登录");
            return jr;
        }
        //当前系统时间yyyy-MM-dd 毫秒值
        long currDateLong = DateUtil.convert(DateUtil.formatDate(new Date(),"yyyy-MM-dd"),2).getTime();
        //判断是否在活动时间范围内
        if (currDateLong < DateUtil.convert(newYearActivityStartDate,0).getTime()
                || currDateLong > DateUtil.convert(newYearActivityEndDate,0).getTime()) {
            jr.setStatus(103);
            jr.setContent("不在活动时间范围内");
            return jr;
        }
        User user = userService.selectForObjectByPhone(user_name);
        //查询用户从平台上线到活动期间是否投资过（即是否满足领取条件）
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("userId",user.getId());
        boolean isInvested = productBuyService.selectUserIsInvested(hashMap);
        if (!isInvested) {//投资过
            jr.setStatus(104);
            jr.setContent("用户不满足领取条件");
            return jr;
        }
        //判断用户是否拿到过奖励
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setUser_id(user.getId());
        userCoupon.setCoupon_name(newYearActivityCouponName);
        List<UserCouponVo> userCouponVos = userCouponService.selectForList(userCoupon);
        if (userCouponVos.size() > 0) {//未获得过此奖励则发放
            jr.setStatus(105);
            jr.setContent("领取成功");
            return jr;
        }
        //满足条件发放奖励
        Coupon coupon = new Coupon();
        coupon.setStatus(1);
        coupon.setCoupon_name(newYearActivityCouponName);
        applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
                coupon,couponService,userCouponService));
        jr.setStatus(100);
        jr.setContent("领取成功");
        return jr;
    }
    /**
     * @description 新春红包领取条件判断接口提供给移动端
     * @author Andrew
     * @date_time 2018-02-05 11:06
     * @param request
     * @return com.zdjf.webservice.express.JsonReturn
     */
    @ResponseBody
    @RequestMapping(value = "/activity/getUserFlag", method = RequestMethod.POST)
    public JsonReturn getUserFlag(HttpServletRequest request) {
        JsonReturn jr = new JsonReturn();
        String user_name = request.getParameter("phone");
        int flag = -1;//领取标示：0满足条件，未领取；1满足条件已领取;2不满足条件;3未登录
        if (StrUtils.emptyJudge(user_name)) {
            User user = userService.selectForObjectByPhone(user_name);
            //查询用户从平台上线到活动期间是否投资过（即是否满足领取条件）
            Map<String,Object> hashMap = new HashMap<>();
            hashMap.put("userId",user.getId());
            boolean isInvested = productBuyService.selectUserIsInvested(hashMap);
            if (!isInvested) {
                flag = 2;
            }
            //查询用户是否领取过奖励
            UserCoupon userCoupon = new UserCoupon();
            userCoupon.setUser_id(user.getId());
            userCoupon.setCoupon_name(newYearActivityCouponName);
            List<UserCouponVo> userCouponVos = userCouponService.selectForList(userCoupon);
            if ((userCouponVos == null || userCouponVos.size() == 0) && isInvested) {//没有获得过此奖励则发放
                flag = 0;
            }
            if (userCouponVos.size() > 0) {
                flag = 1;
            }
        } else {
            flag = 3;
        }
        jr.setStatus(100);
        jr.setContent(flag);
        return jr;
    }
    /**
     * @description 查询新春活动投资排行接口
     * @author Andrew
     * @date_time 2018-02-07 11:05
     * @return com.zdjf.webservice.express.JsonReturn
     */
    @ResponseBody
    @RequestMapping(value = "/activity/queryNewYearInvt", method = RequestMethod.POST)
    public JsonReturn queryNewYearInvt() {
        JsonReturn jr = new JsonReturn();
        //查询用户在活动时间范围内的投资排行
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("startDate",newYearActivityStartDate);
        hashMap.put("endDate",newYearActivityEndDate);
        List<Map<String, Object>> list = productBuyService.selectNewYearInvt(hashMap);
        jr.setStatus(100);
        jr.setContent(list);
        return jr;
    }

    /**
     * @description banner到达元宵节活动页面
     * @author Andrew
     * @date_time 2018-02-28 11:04
     * @return java.lang.String
     */
    @RequestMapping(value = "/activity/lanternFestival", method = RequestMethod.GET)
    public String toLanternFestival() {
        return "front/active/lanternFestival";
    }

    /**
     * @description banner到达女神节活动页面
     * @author Andrew
     * @date_time 2018-03-05 11:03
     * @param request
     * @param response
     * @param model
     * @return java.lang.String
     */
    @RequestMapping(value = "/activity/goddess", method = RequestMethod.GET)
    public String toGoddess(HttpServletRequest request, HttpServletResponse response, Model model) {
        String user_name = BrowseUtil.getCookie(request, response);
        Date currDate = DateUtil.StingToDate(DateUtil.DateToString(new Date(),"yyyy-MM-dd"),"yyyy-MM-dd");
        if (StrUtils.emptyJudge(user_name)) {
            User user = userService.selectForObjectByPhone(user_name);
            //查询用户当日是否领取过红包
            UserGift ug = new UserGift();
            ug.setUser_id(user.getId());
            ug.setGift_name(firstGoddessGiftName);
            ug.setStart_date(currDate);
            List<UserGiftVo> userGfs = userGiftService.selectForList(ug);
            int giftFlag;//1未领取，2已领取
            if (userGfs != null && userGfs.size() > 0) {
                giftFlag = 2;
            } else {
                giftFlag = 1;
            }
            //查询用户当日累计投资额（即是否满足领取条件）
            Map<String,Object> hashMap = new HashMap<>();
            hashMap.put("userId",user.getId());
            Double curDayIntAmt = productBuyService.selectUserInvestByCurDay(hashMap);
            String couponFlag = "";//a满足条件，未领取；b满足条件已领取;c不满足条件
            if (curDayIntAmt >= 2000) {//满足条件
                //查询用户当日是否领取过奖励
                UserCoupon userCoupon = new UserCoupon();
                userCoupon.setUser_id(user.getId());
                userCoupon.setCoupon_name(goddessCouponName);
                userCoupon.setStart_date(currDate);
                List<UserCouponVo> userCouponVos = userCouponService.selectForList(userCoupon);
                if ((userCouponVos == null || userCouponVos.size() == 0)) {//没有获得过此奖励则发放
                    couponFlag = "a";//a满足条件，未领取；
                }
                if (userCouponVos.size() > 0) {
                    couponFlag = "b";//b满足条件已领取;
                }
            } else {
                couponFlag = "c";//c不满足条件
            }
            model.addAttribute("giftFlag",giftFlag);
            model.addAttribute("couponFlag",couponFlag);
            model.addAttribute("user",user);
        } else {
            model.addAttribute("user",null);
        }
        //查询用户在活动时间范围内的投资排行
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("startDate",goddessActivityStartDate);
        hashMap.put("endDate",getGoddessActivityEndDate);
        List<Map<String, Object>> list = productBuyService.selectNewYearInvt(hashMap);
        model.addAttribute("investList",list);
        return "front/active/goddessFestival";
    }

    /**
     * @description 女神节活动礼包领取条件判断提供给移动端
     * @author Andrew
     * @date_time 2018-03-05 11:02
     * @param request
     * @return com.zdjf.webservice.express.JsonReturn
     */
    @ResponseBody
    @RequestMapping(value = "/goddessActivity/getUserFlag", method = RequestMethod.POST)
    public JsonReturn getUserFlagByGoddessActivity(HttpServletRequest request) {
        JsonReturn jr = new JsonReturn();
        String user_name = request.getParameter("phone");
        String flag = "";//领取标示：00未登录;
        Date currDate = DateUtil.StingToDate(DateUtil.DateToString(new Date(),"yyyy-MM-dd"),"yyyy-MM-dd");
        if (StrUtils.emptyJudge(user_name)) {
            User user = userService.selectForObjectByPhone(user_name);
            if (user == null) {
                jr.setStatus(101);
                jr.setContent("用户不存在");
                return jr;
            }
            //查询用户当日是否领取过红包
            UserGift ug = new UserGift();
            ug.setUser_id(user.getId());
            ug.setGift_name(firstGoddessGiftName);
            ug.setStart_date(currDate);
            List<UserGiftVo> userGfs = userGiftService.selectForList(ug);
            String giftFlag = "";//1未领取，2已领取
            if (userGfs != null && userGfs.size() > 0) {
                giftFlag = "2";
            } else {
                giftFlag = "1";
            }
            //查询用户当日累计投资额（即是否满足领取条件）
            Map<String,Object> hashMap = new HashMap<>();
            hashMap.put("userId",user.getId());
            Double curDayIntAmt = productBuyService.selectUserInvestByCurDay(hashMap);
            String couponFlag = "";//a满足条件，未领取；b满足条件已领取;c不满足条件
            if (curDayIntAmt >= 2000) {//满足条件
                //查询用户当日是否领取过奖励
                UserCoupon userCoupon = new UserCoupon();
                userCoupon.setUser_id(user.getId());
                userCoupon.setCoupon_name(goddessCouponName);
                userCoupon.setStart_date(currDate);
                List<UserCouponVo> userCouponVos = userCouponService.selectForList(userCoupon);
                if ((userCouponVos == null || userCouponVos.size() == 0)) {//没有获得过此奖励则发放
                    couponFlag = "a";//a满足条件，未领取；
                }
                if (userCouponVos.size() > 0) {
                    couponFlag = "b";//b满足条件已领取;
                }
            } else {
                couponFlag = "c";//c不满足条件
            }
            flag = giftFlag + couponFlag;
        } else {
            flag = "00";
        }
        jr.setStatus(100);
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("flag",flag);
        jr.setContent(hashMap);
        return jr;
    }

    /**
     * @description 女神节活动礼包领取
     * @author Andrew
     * @date_time 2018-03-05 11:02
     * @param request
     * @param response
     * @return com.zdjf.webservice.express.JsonReturn
     */
    @ResponseBody
    @RequestMapping(value = "/activity/getGoddessActivityGift",method = RequestMethod.POST)
    public JsonReturn getGoddessActivityGift(HttpServletRequest request, HttpServletResponse response) {
        JsonReturn jr = new JsonReturn();
        String user_name = "";
        String req_source = request.getParameter("reg_source");//必填
        String type = request.getParameter("type");//领取类型：1红包；2加息券
        Date currDate = DateUtil.StingToDate(DateUtil.DateToString(new Date(),"yyyy-MM-dd"),"yyyy-MM-dd");
        if (!StrUtils.emptyJudge(req_source) || !StrUtils.emptyJudge(type)) {
            jr.setStatus(101);
            jr.setContent("关键字段为空");
            return jr;
        }
        if(StrUtils.emptyJudge(req_source) && !"1".equals(req_source)){  //判断是否来自手机端
            user_name = request.getParameter("phone");
        }else{//pc端
            user_name = BrowseUtil.getCookie(request, response);
        }
        if (!StrUtils.emptyJudge(user_name)) {
            jr.setStatus(102);
            jr.setContent("用户未登录");
            return jr;
        }
        //当前系统时间yyyy-MM-dd 毫秒值
        long currDateLong = DateUtil.convert(DateUtil.formatDate(new Date(),"yyyy-MM-dd"),2).getTime();
        //判断是否在活动时间范围内
        if (currDateLong < DateUtil.convert(goddessActivityStartDate,0).getTime()
                || currDateLong > DateUtil.convert(getGoddessActivityEndDate,0).getTime()) {
            jr.setStatus(103);
            jr.setContent("不在活动时间范围内");
            return jr;
        }
        User user = userService.selectForObjectByPhone(user_name);

        if ("1".equals(type)) {//领取红包
            //查询用户当日是否领取过红包
            UserGift ug = new UserGift();
            ug.setUser_id(user.getId());
            ug.setGift_name(firstGoddessGiftName);
            ug.setStart_date(currDate);
            List<UserGiftVo> userGfs = userGiftService.selectForList(ug);
            if (userGfs != null && userGfs.size() > 0) {
                jr.setStatus(105);
                jr.setContent("您今天已领取过奖励");
                return jr;
            } else {//未领取则发放
                Gift nsgift = new Gift();
                nsgift.setGift_name(firstGoddessGiftName);
                nsgift.setStatus(1);
                applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
                        nsgift,giftService,userGiftService));
            }
        } else { //领取加息券
            //查询用户当日累计投资额（即是否满足领取条件）
            Map<String,Object> hashMap = new HashMap<>();
            hashMap.put("userId",user.getId());
            Double curDayIntAmt = productBuyService.selectUserInvestByCurDay(hashMap);
            if (curDayIntAmt < 2000) {//不满足条件
                jr.setStatus(104);
                jr.setContent("不满足领取条件");
                return jr;
            }
            //判断用户当日是否拿到过奖励
            UserCoupon userCoupon = new UserCoupon();
            userCoupon.setUser_id(user.getId());
            userCoupon.setCoupon_name(goddessCouponName);
            userCoupon.setStart_date(currDate);
            List<UserCouponVo> userCouponVos = userCouponService.selectForList(userCoupon);
            if (userCouponVos.size() > 0) {//未获得过此奖励则发放
                jr.setStatus(105);
                jr.setContent("您今天已领取过奖励");
                return jr;
            }
            //满足条件发放奖励
            Coupon coupon = new Coupon();
            coupon.setStatus(1);
            coupon.setCoupon_name(goddessCouponName);
            applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
                    coupon,couponService,userCouponService));
        }

        jr.setStatus(100);
        jr.setContent("您已成功领取奖励");
        return jr;
    }

    /**
     * @description 女神节活动投资排行
     * @author Andrew
     * @date_time 2018-03-05 11:00
     * @param
     * @return com.zdjf.webservice.express.JsonReturn
     */
    @ResponseBody
    @RequestMapping(value = "/activity/queryGoddessInvt", method = RequestMethod.POST)
    public JsonReturn queryGoddessInvt() {
        JsonReturn jr = new JsonReturn();
        //查询用户在活动时间范围内的投资排行
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("startDate",goddessActivityStartDate);
        hashMap.put("endDate",getGoddessActivityEndDate);
        List<Map<String, Object>> list = productBuyService.selectNewYearInvt(hashMap);
        jr.setStatus(100);
        jr.setContent(list);
        return jr;
    }

    /**
     * @description 到达愚人节活动页面(web端)
     * @author Andrew
     * @date_time 2018-03-20 13:25
     * @param request
     * @param response
     * @param model
     * @return java.lang.String
     */
    @RequestMapping(value = "/activity/toAprilFoolsDay", method = RequestMethod.GET)
    public String toAprilFoolsDay(HttpServletRequest request, HttpServletResponse response, Model model) {
        String user_name = BrowseUtil.getCookie(request, response);
        if (StrUtils.emptyJudge(user_name)) {
            //获取用户信息
            User user = userService.selectForObjectByPhone(user_name);
            //查询用户正经值余额
            UserFundStat userFundStat=new UserFundStat();
            userFundStat.setUser_id(user.getId());
            userFundStat=userFundStatService.selectForObjectById(userFundStat);
            model.addAttribute("coinBlance",userFundStat.getCoin_balance());
            //查询用户当日已抽奖次数（每日抽奖次数：3次上限）
            int userAwardNum = userService.selectUserAwardRecByCurrDay(user.getId());
            model.addAttribute("remainAwardNum",Integer.parseInt(awardNum)-userAwardNum);
        }
        //查询活动期间投资排行
        model.addAttribute("investList", qryInvtByTime(foolsActivityStartDate,foolsActivityEndDate));
        return "front/active/aprilFoolsDay";
    }

    /**
     * @description 提供给移动端获取所有用户投资排行及登录用户正经值余额和当日所剩抽奖次数
     * @author Andrew
     * @date_time 2018-03-21 14:39
     * @param
     * @return com.zdjf.webservice.express.JsonReturn
     */
    @RequestMapping(value="getUserFoolsInfoAndInvtList", method = RequestMethod.POST)
    @ResponseBody
    public JsonReturn getUserFoolsInfoAndInvtList(HttpServletRequest request) {
        JsonReturn jr = new JsonReturn();
        String req_source = request.getParameter("reg_source");//必填
        String user_name = request.getParameter("phone");
        if (!StrUtils.emptyJudge(req_source)) {
            jr.setStatus(101);
            jr.setContent("关键字段为空");
            return jr;
        }
        JSONObject obj = new JSONObject();
        if (StrUtils.emptyJudge(user_name)) {
            //获取用户信息
            User user = userService.selectForObjectByPhone(user_name);
            //查询用户正经值余额
            UserFundStat userFundStat=new UserFundStat();
            userFundStat.setUser_id(user.getId());
            userFundStat=userFundStatService.selectForObjectById(userFundStat);
            obj.put("coinBlance",userFundStat.getCoin_balance());
            //查询用户当日已抽奖次数（每日抽奖次数：3次上限）
            int userAwardNum = userService.selectUserAwardRecByCurrDay(user.getId());
            obj.put("awardNum",Integer.parseInt(awardNum)-userAwardNum);
        }
        obj.put("investList", qryInvtByTime(foolsActivityStartDate,foolsActivityEndDate));
        jr.setStatus(100);
        jr.setContent(obj);
        return  jr;
    }
    /**
     * @description 查询某个时间范围内的投资排行
     * @author Andrew
     * @date_time 2018-03-20 16:06
     * @param startDate
     * @param endDate
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    public List<Map<String, Object>> qryInvtByTime(String startDate, String endDate) {
        //查询用户在活动时间范围内的投资排行
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("startDate",startDate);
        hashMap.put("endDate",endDate);
        List<Map<String, Object>> list = productBuyService.selectNewYearInvt(hashMap);
        return list;
    }

    /**
     * @description 愚人节活动执行抽奖
     * @author Andrew
     * @date_time 2018-03-20 16:10
     * @param request
     * @param response
     * @return com.zdjf.webservice.express.JsonReturn
     */
    @ResponseBody
    @RequestMapping(value = "/activity/doAward",method = RequestMethod.POST)
    public JsonReturn doAward(HttpServletRequest request, HttpServletResponse response) {
        JsonReturn jr = new JsonReturn();
        String user_name = "";
        String req_source = request.getParameter("reg_source");//必填
        if (!StrUtils.emptyJudge(req_source)) {
            jr.setStatus(101);
            jr.setContent("关键字段为空");
            return jr;
        }
        if(StrUtils.emptyJudge(req_source) && !"1".equals(req_source)){  //判断是否来自手机端
            user_name = request.getParameter("phone");
        }else{//pc端
            user_name = BrowseUtil.getCookie(request, response);
        }
        if (!StrUtils.emptyJudge(user_name)) {
            jr.setStatus(102);
            jr.setContent("用户未登录");
            return jr;
        }
        //当前系统时间yyyy-MM-dd 毫秒值
        long currDateLong = DateUtil.convert(DateUtil.formatDate(new Date(),"yyyy-MM-dd"),2).getTime();
        //判断是否在活动时间范围内
        if (currDateLong < DateUtil.convert(foolsActivityStartDate,0).getTime()
                || currDateLong > DateUtil.convert(foolsActivityEndDate,0).getTime()) {
            jr.setStatus(103);
            jr.setContent("不在活动时间范围内");
            return jr;
        }
        User user = userService.selectForObjectByPhone(user_name);
        //查询用户正经值余额
        UserFundStat userFundStat=new UserFundStat();
        userFundStat.setUser_id(user.getId());
        userFundStat=userFundStatService.selectForObjectById(userFundStat);
        if (userFundStat.getCoin_balance()-5.0 < 0) {
            jr.setStatus(104);
            jr.setContent("正经值余额不足");
            return jr;
        }
        //查询用户当日已抽奖次数（每日抽奖次数：3次上限）
        int userAwardNum = userService.selectUserAwardRecByCurrDay(user.getId());
        if (userAwardNum == Integer.parseInt(awardNum)) {
            jr.setStatus(105);
            jr.setContent("今日抽奖次数已达上限");
            return jr;
        }
        //按概率返回中奖奖品，并保存用户中奖记录并更新正经值
        int awardResult = createAwardByRate();
        AwardRec awdRec = new AwardRec();
        awdRec.setUser_id(user.getId());
        awdRec.setActivity_type(1);//活动类型：愚人节
        awdRec.setAward_type(awardResult);
        awdRec.setReg_source(Integer.parseInt(req_source));
        userService.saveUserAwardRec(awdRec);
        //正经值消耗5点
        Double coinBance = userFundStat.getCoin_balance();
        Double coinCost = userFundStat.getCoin_cost();
        userFundStat.setCoin_cost(coinCost + 5);//正经值总消费
        userFundStat.setCoin_balance(coinBance-5);//正经值余额
        userFundStatService.updateUserFundStatById(userFundStat);

        //记录正经值明细
        CoinStream coinStream = new CoinStream();
        coinStream.setAmount(5.0);
        coinStream.setUser_id(user.getId());
        coinStream.setCreate_time(new Date());
        coinStream.setAction(2);//消费
        coinStream.setRemark("愚人节活动抽奖");
        coinStream.setType(51);//活动抵扣
        coinStream.setNum(5);
        coinStream.setStatus(1);//2018-1-12 add
        coinStream.setBalance(coinBance-5);
        coinStreamService.save(coinStream);

        //如果中奖为红包即award_type=1
        if (1 == awardResult) {
            //红包 流程
            Gift nygift = new Gift();
            nygift.setGift_name(foolsAwardGift);
            nygift.setStatus(1);
            applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
                    nygift,giftService,userGiftService));
        } else if (2 == awardResult) {//中奖加息券
            Coupon coupon = new Coupon();
            coupon.setStatus(1);
            coupon.setCoupon_name(foolsAwardCoupon);
            applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
                    coupon,couponService,userCouponService));
        } else if (3 == awardResult) {//奖励正经值
            applicationContext.publishEvent(new FundEvent(this,user.getId(),52,58*1.0,
                    userFundStatService,coinStreamService));
        }
        jr.setStatus(100);
        jr.setContent(awardResult);
        return jr;
    }

    /**
     * @description 中奖生成
     * @author Andrew
     * @date_time 2018-03-20 16:35
     * @param
     * @return int
     */
    public int createAwardByRate() {
        int randomNum = new Random().nextInt(100) + 1;
        if (randomNum < 80) {
            return 1;
        } else if (randomNum >= 80 && randomNum < 100) {
            return 2;
        } else {
            return 3;
        }
    }
}
