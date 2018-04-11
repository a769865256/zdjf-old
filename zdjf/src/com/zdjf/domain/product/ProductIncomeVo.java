package com.zdjf.domain.product;

import java.util.List;

public class ProductIncomeVo extends ProductIncome {

	private static final long serialVersionUID = 114878135693431353L;
	public ProductIncomeVo(){
		super();
	}
	public ProductIncomeVo(Long id){
		super();
		this.id=id;
	}
	public List<ProductIncome> getProductIncomeList() {
		return productIncomeList;
	}
	public void setProductIncomeList(List<ProductIncome> productIncomeList) {
		this.productIncomeList = productIncomeList;
	}
	private List<ProductIncome> productIncomeList;

}
