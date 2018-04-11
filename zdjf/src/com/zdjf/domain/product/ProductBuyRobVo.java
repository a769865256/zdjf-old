package com.zdjf.domain.product;

import java.util.List;

public class ProductBuyRobVo extends ProductBuyRob {

	private static final long serialVersionUID = -2302809326326819801L;
	public ProductBuyRobVo(){
		super();
	}
	public ProductBuyRobVo(Long id){
		super();
		this.id=id;
	}
	public List<ProductBuyRob> getProductBuyRobList() {
		return productBuyRobList;
	}
	public void setProductBuyRobList(List<ProductBuyRob> productBuyRobList) {
		this.productBuyRobList = productBuyRobList;
	}
	private List<ProductBuyRob> productBuyRobList;
}
