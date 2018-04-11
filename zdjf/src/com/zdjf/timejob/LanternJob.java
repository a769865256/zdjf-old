package com.zdjf.timejob;

import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 元宵节活动红包发送定时任务
 */
@Component
@PropertySource(value= "classpath:project.properties")
public class LanternJob {

    Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private IUserGiftService userGiftService;

    @Autowired
    private IProductBuyService productBuyService;

    @Value("${activity3.start.date}")
    private String activityStartDate;

    @Value("${activity3.end.date}")
    private String activityEndDate;

    @Value("${gift3.name}")
    private String activityGiftName;

    //元宵红包发放 0 0 10 * * ? 本地关闭定时任务
//    @Scheduled(cron = "0 0 10 * * ?")
    public void giftGiveOut() {
        //当前系统时间
        Date curDate = new Date();
        Long time1 = curDate.getTime();
        //当前系统时间yyyy-MM-dd 毫秒值
        long currDateLong = DateUtil.convert(DateUtil.formatDate(curDate,"yyyy-MM-dd"),2).getTime();
        //在活动时间范围内，发放红包
        if (currDateLong >= DateUtil.convert(activityStartDate,0).getTime()
                && currDateLong <= DateUtil.convert(activityEndDate,0).getTime()) {

            //查询有过投资记录的用户
            List<Long> list = productBuyService.selectInvestor();
            Map<String,Object> hashMap = groupList(list);
            //遍历map中的值
            for (Object value : hashMap.values()) {
                userGiftService.addUserGift((List<Long>) value,activityGiftName);
            }
        }
        Long time2 = new Date().getTime();
        log.info("元宵快乐红包发放定时任务耗时:" + (time2-time1)/1000 + "秒");
    }

    public Map groupList(List list){
        int listSize=list.size();
        int toIndex=1000;
        Map map = new HashMap();
        int keyToken = 0;
        for(int i = 0;i<list.size();i+=1000){
            if(i+1000>listSize){
                toIndex=listSize-i;
            }
            List newList = list.subList(i,i+toIndex);
            map.put("keyName"+keyToken, newList);
            keyToken++;
        }
        return map;
    }
}
