package com.zdjf.domain.product;

import java.util.List;

public class ProductBuyIncomeVo extends ProductBuyIncome {
	private static final long serialVersionUID = 2026452509795876966L;
	public ProductBuyIncomeVo(){
		super();
	}
	public ProductBuyIncomeVo(Long id){
		super();
		this.id=id;
	}
	public List<ProductBuyIncome> getProductBuyIncomeList() {
		return productBuyIncomeList;
	}
	public void setProductBuyIncomeList(List<ProductBuyIncome> productBuyIncomeList) {
		this.productBuyIncomeList = productBuyIncomeList;
	}
	private List<ProductBuyIncome> productBuyIncomeList;
}
