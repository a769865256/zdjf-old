package com.zdjf.web.fund;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zdjf.domain.fund.CoinGoods;
import com.zdjf.frame.spring.Result;
import com.zdjf.service.fund.ICoinGoodsService;
import com.zdjf.util.RoofNumberUtils;

@Controller
@RequestMapping("/coingoods")
public class CoinGoodsController {
	
	private ICoinGoodsService coinGoodsService;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)//添加管理用户
	public Result addCoinGoods(HttpServletRequest request) throws ParseException {
		
		CoinGoods coinGoods = new CoinGoods();
		
		String goods_name  =  request.getParameter("goods_name");
		String coin_cost  =  request.getParameter("coin_cost");
		String goods_type  =  request.getParameter("goods_type");
		String exchange_name  =  request.getParameter("exchange_name");
		String amount  =  request.getParameter("amount");
		//String price  =  request.getParameter(" price ");
		String max_count  =  request.getParameter("max_count");
		String status  =  request.getParameter("status");
		String order_num  =  request.getParameter("order_num");
		String des  =  request.getParameter("des");
		String remark  =  request.getParameter("remark");
		String goods_number  =  request.getParameter("goods_number");
		
		coinGoods.setGoods_name(goods_name);
		coinGoods.setCoin_cost(Integer.parseInt(coin_cost));
		coinGoods.setGoods_type(Integer.parseInt(goods_type));
		coinGoods.setExchange_name(exchange_name);
		coinGoods.setAmount(RoofNumberUtils.StringtoDouble(amount));
		coinGoods.setMax_count(Integer.parseInt(max_count));
		coinGoods.setStatus(Integer.parseInt(status));
		coinGoods.setOrder_num(Integer.parseInt(order_num));
		coinGoods.setDes(des);
		coinGoods.setRemark(remark);
		coinGoods.setGoods_number(goods_number);
		coinGoods.setCreate_time(new Date());
		coinGoodsService.save(coinGoods);
		
		return new Result(Result.SUCCESS,"保存成功!");
	}
	
	@Autowired(required = true)
	public void setCoinGoodsService(ICoinGoodsService coinGoodsService) {
		this.coinGoodsService = coinGoodsService;
	}

}
