package com.zdjf.timejob;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdjf.domain.admin.BusinessDayStat;
import com.zdjf.service.admin.IStatService;
import com.zdjf.service.admin.ISysUserService;

/**
 * 后台首页的数据统计
 * @author csh
 *
 */
@Component
public class BusinessDayStatJob {

	private static String stat_date = "";
	
	private IStatService statService;
	
	private ISysUserService sysUserService;
	
	//@Scheduled(cron = "0 0/10 * * * ?")
	public void dayStat(){
		Long dateTime = new Date().getTime()-11*60*1000; //11分钟前的时间戳
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");  
		String dateStr = sdf.format(dateTime);
		stat_date = dateStr;
		BusinessDayStat bds = new BusinessDayStat();
		bds.setStat_date(dateStr);
		BusinessDayStat businessDayStat = sysUserService.getBusinessDayStat(bds);
		bds.setWeb_register(1); //web注册人数
		bds.setAndroid_register(1); 
		bds.setIos_register(1);
		bds.setWap_register(1);
		bds.setFirst_invest_count(1); //首投人数
		bds.setWeb_recharge(100d); //web充值金额
		bds.setAndroid_recharge(100d);
		bds.setIos_recharge(100d);
		bds.setWap_recharge(100d);
		bds.setWeb_invest_amount(100d);//decimal(11,2) comment 'web投资金额',
		bds.setAndroid_invest_amount(100d);
		bds.setIos_invest_amount(100d);
		bds.setWap_invest_amount(100d);
		bds.setWeb_withdrawals_amount(100d);  //web提现金额
		bds.setAndroid_withdrawals_amount(100d);
		bds.setIos_withdrawals_amount(100d);
		bds.setWap_withdrawals_amount(100d);
		bds.setInvest_service_charge(50d);   //充值手续费
		bds.setWithdrawals_service_charge(50d); //提现手续费
		bds.setPending_withdrawals_count(1); //待审核提现人数
		bds.setUser_count(100); //总用户数
		bds.setReal_user_count(10); //实名用户数
		bds.setInvest_user_count(10); //投资用户数量
		bds.setInvest_amount(100000d); //在投金额
		bds.setPending_principal(88888d); //待回本金
		bds.setInterest(1111d);//待回利息
		bds.setAll_invest_amount(11111111d);//总投资金额
		bds.setAll_behind_amout(1111111d);//总留存金额
		bds.setRest_amout(22222d);//平台站岗资金
		bds.setAll_service_charge(222222d); //总手续费
		bds.setToday_pending_amout(5555d); //今日待回款金额
		if(businessDayStat!=null){  //更新
			bds.setToday_pending_amout(88888d);
			statService.updateBusinessDayStat(bds);
		}else{  //保存
			bds.setTomorrow_pending_amout(1111d);
			bds.setThird_pending_amout(1111d);
			bds.setFourth_pending_amout(1111d);
			bds.setFifth_pending_amout(11111d);
			bds.setSixth_pending_amout(11111d);
			bds.setSeventh_pending_amout(1111d);
			statService.saveBusinessDayStat(bds);
		}
	}

	@Autowired
	public void setStatService(IStatService statService) {
		this.statService = statService;
	}
	
	@Autowired(required = true)
	public void setSysUserService(ISysUserService userService) {
		this.sysUserService = userService;
	}
}
