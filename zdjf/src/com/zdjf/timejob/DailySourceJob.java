package com.zdjf.timejob;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdjf.domain.source.Source;
import com.zdjf.domain.source.SourceVo;
import com.zdjf.domain.statistics.SourceSurvey;
import com.zdjf.service.source.ISourceService;
import com.zdjf.service.statistics.ISourceSurveyService;
import com.zdjf.util.DateUtil;
import com.zdjf.util.StrUtils;


@Component
public class DailySourceJob {
	@Autowired
	private ISourceSurveyService isss;
	@Autowired
	private ISourceService iss;
//	@Scheduled(cron = "0 0 10 * * ?")
	public void dailyJob(){
			Date date= new Date();
			date=DateUtil.addDate(date, 0, 0, -1);
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			String time=df.format(date);
			List<SourceVo> sourceList=iss.selectForList(new Source());
			Map map=new HashMap();
			map.put("sql_name", "selectDailyInvest");
			List<Map> investAll=isss.selectForListByMap(map);//总投资人数 总投资金额
			map.put("sql_name", "selectNewRegister");
			List<Map> registerAll=isss.selectForListByMap(map);//总注册量
			map.put("auth", "true");
			List<Map> registerAuthAll=isss.selectForListByMap(map);//总实名量
			map.put("sql_name", "selectRepeatInvest");
			List<Map> repeatInvestAll=isss.selectForListByMap(map);//复投
			map.clear();
			map.put("time", time);
			map.put("sql_name", "selectNewEffectiveInvestmentTimes");
			List<Map> newFirstInvestmentTimes=isss.selectForListByMap(map);//新增有效投资次数 新增有效投资人数
			map.put("sql_name", "selectNewFirstInvestment");
			List<Map> newFirstInvestment=isss.selectForListByMap(map);//新增有效投资额
			map.put("sql_name", "selectNewRegister");
			List<Map> newRegister=isss.selectForListByMap(map);//新增注册量
			map.put("auth", "true");
			List<Map> newRegisterAuth=isss.selectForListByMap(map);//新增实名量
			map.put("sql_name", "selectNewRegisterInvest");
			List<Map> newRegisterInvest=isss.selectForListByMap(map);//新增注册投资人数
			map.put("sql_name", "selectDailyInvest");
			List<Map> dailyInvest=isss.selectForListByMap(map);//日投资人数 日投资金额
			map.put("sql_name", "selectDailyFund");
			map.put("operate_type", "11");
			List<Map> dailyFund1=isss.selectForListByMap(map);//充值
			map.put("operate_type", "21");
			List<Map> dailyFund2=isss.selectForListByMap(map);//提现
			map.put("sql_name", "selectRepeatInvest");
			List<Map> repeatInvest=isss.selectForListByMap(map);//复投
			map.put("sql_name", "selectStationFund");
			List<Map> stationFund=isss.selectForListByMap(map);//站岗
			map.put("sql_name", "selectRepeatInvestAll");
			map.put("payBack", "true");
			List<Map> repeatInvestAll2=isss.selectForListByMap(map);//复投
			
			for(int i=0;i<sourceList.size();i++){
				SourceSurvey ss=new SourceSurvey();
				ss.setCreate_time(date);
				ss.setSubordinate_platform(sourceList.get(i).getSource_platform()+"");
				ss.setSource_name(sourceList.get(i).getSource_code());
				ss.setNew_effective_investment_times(StrUtils.convToInt(newFirstInvestmentTimes.get(i).get("count1").toString()));
				ss.setNew_effective_investment_people(StrUtils.convToInt(newFirstInvestmentTimes.get(i).get("count2").toString()));
				ss.setNew_first_investment(StrUtils.convToDouble(newFirstInvestment.get(i).get("amount").toString()));
				int newreg=StrUtils.convToInt(newRegister.get(i).get("count").toString());
				ss.setNew_registrations(newreg);
				ss.setNew_real_name_authentication(StrUtils.convToInt(newRegisterAuth.get(i).get("count").toString()));
				int newreg_invest=StrUtils.convToInt(newRegisterInvest.get(i).get("count").toString());
				ss.setNew_registrations_investments(newreg_invest);
				if(newreg>0){
					ss.setNew_registered_investment_rate((double)(newreg_invest/newreg));
				}else{
					ss.setNew_registered_investment_rate(0.0);
				}
				int dailyinvest=StrUtils.convToInt(dailyInvest.get(i).get("count").toString());
				ss.setDaily_investment(dailyinvest);
				double dailyinvest_amount=StrUtils.convToDouble(dailyInvest.get(i).get("count").toString());
				ss.setDaily_investment_amount(dailyinvest_amount);
				if(dailyinvest>0){
					ss.setDaily_per_capita_investment(dailyinvest_amount/dailyinvest);
				}else{
					ss.setDaily_per_capita_investment(0.0);
				}
				
				ss.setWithdrawals_times(StrUtils.convToInt(dailyFund1.get(i).get("count").toString()));
				ss.setWithdrawals_people(StrUtils.convToInt(dailyFund1.get(i).get("count2").toString()));
				ss.setWithdrawals_amount(StrUtils.convToDouble(dailyFund1.get(i).get("amount").toString()));
				ss.setRecharge_times(StrUtils.convToInt(dailyFund2.get(i).get("count").toString()));
				ss.setRecharge_people(StrUtils.convToInt(dailyFund2.get(i).get("count2").toString()));
				ss.setRecharge_amount(StrUtils.convToDouble(dailyFund2.get(i).get("amount").toString()));
				int repeatinvest=StrUtils.convToInt(repeatInvest.get(i).get("count").toString());
				ss.setRepeat_investment_people(repeatinvest);
				ss.setRepeat_investment_amount(StrUtils.convToDouble(repeatInvest.get(i).get("amount").toString()));
				if(dailyinvest>0){
					ss.setRepeat_investment_rate((double)(repeatinvest/dailyinvest));
				}else{
					ss.setRepeat_investment_rate(0.0);
				}
				int register=StrUtils.convToInt(registerAll.get(i).get("count").toString());
				ss.setTotal_registration(register);
				ss.setTotal_real_name(StrUtils.convToInt(registerAuthAll.get(i).get("count").toString()));
				int investpeople=StrUtils.convToInt(investAll.get(i).get("count").toString());
				if(register>0){
					ss.setTotal_investment_rate((double)(investpeople/register));
				}else{
					ss.setTotal_investment_rate(0.0);
				}
				ss.setTotal_effective_capital(investpeople);
				double investamount=StrUtils.convToDouble(investAll.get(i).get("amount").toString());
				ss.setTotal_investment(investamount);
				if(investpeople>0){
					ss.setTotal_per_capita_investment(investamount/investpeople);
				}else{
					ss.setTotal_per_capita_investment(0.0);
				}
				
				ss.setSentry_funds(StrUtils.convToDouble(stationFund.get(i).get("amount").toString()));
				int repeatpeople=StrUtils.convToInt(repeatInvestAll.get(i).get("count").toString());
				ss.setTotal_repeat_investment_people(repeatpeople);
				ss.setTotal_repeat_investment_rate(StrUtils.convToDouble(repeatInvestAll.get(i).get("amount").toString()));
				int debtpeople=StrUtils.convToInt(repeatInvestAll2.get(i).get("count").toString());
				ss.setTotal_debt_service_interest(debtpeople);
				if(repeatpeople>0){
					ss.setTotal_debt_service_rate((double)(debtpeople/repeatpeople));
				}else{
					ss.setTotal_debt_service_rate(0.0);
				}
				
				double debtamount=StrUtils.convToDouble(repeatInvestAll2.get(i).get("amount").toString());
				ss.setTotal_debt_service_amount(debtamount);
				if(debtpeople>0){
					ss.setTotal_debt_per_amount(debtamount/debtpeople);
				}else{
					ss.setTotal_debt_per_amount(0.0);
				}
				
				isss.save(ss);
			}
		}
 	}
