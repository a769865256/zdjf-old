package com.zdjf.web.fund;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zdjf.domain.fund.Coin;
import com.zdjf.domain.fund.CoinVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.frame.spring.Result;
import com.zdjf.service.fund.ICoinService;

@Controller
@RequestMapping("/coin")
public class CoinController {
	
	private ICoinService coinService;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)//添加管理用户
	public Result addCoin(HttpServletRequest request) throws ParseException {
		
		Coin coin = new Coin();
		
		String coin_name = request.getParameter("coin_name");
		String coin_source = request.getParameter("coin_source");
		String coin_num = request.getParameter("coin_num");
		String coin_max = request.getParameter("coin_max");
		String coin_min = request.getParameter("coin_min");
		String coin_multiple = request.getParameter("coin_multiple");
		String invite_ratio = request.getParameter("invite_ratio");
		String deduction_ratio = request.getParameter("deduction_ratio");
		String coin_type = request.getParameter("coin_type");
		
		String status = request.getParameter("status");
		String remark = request.getParameter("remark");
		
		
		coin.setCoin_name(coin_name);
		coin.setCoin_source(Integer.parseInt(coin_source));
		if(coin_type.equalsIgnoreCase("1")){
			coin.setCoin_max(Integer.parseInt(coin_max));
			coin.setCoin_min(Integer.parseInt(coin_min));
		}else if(coin_type.equalsIgnoreCase("2")){
			coin.setCoin_num(Integer.parseInt(coin_num));
		}
		if(!coin_multiple.isEmpty())
			coin.setCoin_multiple(Integer.parseInt(coin_multiple));
		if(!invite_ratio.isEmpty())
			coin.setInvite_ratio(Double.parseDouble(invite_ratio));
		if(!deduction_ratio.isEmpty())
			coin.setDeduction_ratio(Double.parseDouble(deduction_ratio));
		coin.setStatus(Integer.parseInt(status));
		coin.setRemark(remark);
		coin.setCoin_type(Integer.parseInt(coin_type));
		coin.setCreate_time(new Date());


		coinService.save(coin);
		
		return new Result("保存成功!");
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)//添加管理用户
	public String getGiftPage(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		Coin coin = new Coin();
		Page page = PageUtils.createPage(request);
		page = coinService.page(page, coin);
		
		//List<>page.getDataList();
		
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		
		return "giftPage";
	}
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)//添加管理用户
	public String getListPage(HttpServletRequest request,
            HttpServletResponse response, Model model){
		
		String name = "新手注册";
		
		Coin coin = new Coin();
		
		/*coin.setGift_name(name);
		gift.setGift_source(1);*/
		
		coin.setCoin_name(name);
		coin.setCoin_source(1);
		
		List<CoinVo> list = coinService.selectForList(coin);
		
		model.addAttribute("list", list);
		
		return "giftList";
	}
	
	
	@RequestMapping(value="/details",method=RequestMethod.GET)//添加管理用户
	public String getGiftDetails(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		String id  = request.getParameter("id");
		Coin coin = coinService.selectForObjectById(Long.parseLong(id));
		model.addAttribute("coin", coin);
		
		return "gift";
	}
	
	@RequestMapping(value="/del",method=RequestMethod.DELETE)//添加管理用户
	public Result delGift(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		String id  = request.getParameter("id");
		coinService.deleteById(Integer.parseInt(id));
		
		
		return new Result("ok");
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)//添加管理用户
	public Result updateGift(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		String id = request.getParameter("id");
		String coin_name = request.getParameter("coin_name");
		String coin_source = request.getParameter("coin_source");
		String coin_num = request.getParameter("coin_num");
		String coin_max = request.getParameter("coin_max");
		String coin_min = request.getParameter("coin_min");
		String coin_multiple = request.getParameter("coin_multiple");
		String invite_ratio = request.getParameter("invite_ratio");
		String deduction_ratio = request.getParameter("deduction_ratio");
		String coin_type = request.getParameter("coin_type");
		
		String status = request.getParameter("status");
		String remark = request.getParameter("remark");
		
		Coin coin = coinService.selectForObjectById(Long.parseLong(id));
		
		
		coin.setCoin_name(coin_name);
		coin.setCoin_source(Integer.parseInt(coin_source));
		if(coin_type.equalsIgnoreCase("1")){
			coin.setCoin_max(Integer.parseInt(coin_max));
			coin.setCoin_min(Integer.parseInt(coin_min));
		}else if(coin_type.equalsIgnoreCase("2")){
			coin.setCoin_num(Integer.parseInt(coin_num));
		}
		if(!coin_multiple.isEmpty())
			coin.setCoin_multiple(Integer.parseInt(coin_multiple));
		if(!invite_ratio.isEmpty())
			coin.setInvite_ratio(Double.parseDouble(invite_ratio));
		if(!deduction_ratio.isEmpty())
			coin.setDeduction_ratio(Double.parseDouble(deduction_ratio));
		coin.setStatus(Integer.parseInt(status));
		coin.setRemark(remark);
		coin.setCoin_type(Integer.parseInt(coin_type));

		coinService.save(coin);
		
		
		
		return new Result("ok");
	}
	
	@Autowired(required = true)
	public void setCoinService(ICoinService coinService) {
		this.coinService = coinService;
	}

}
