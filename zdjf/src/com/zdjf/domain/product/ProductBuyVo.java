package com.zdjf.domain.product;

import java.util.List;

public class ProductBuyVo extends ProductBuy {

	private static final long serialVersionUID = 2447209596115158234L;
	private List<ProductBuy> productBuyList;
	
	public ProductBuyVo(){
		super();
	}
	public ProductBuyVo(Long id){
		super();
		this.id=id;
	}
	public List<ProductBuy> getProductBuyList() {
		return productBuyList;
	}
	public void setProductBuyList(List<ProductBuy> productBuyList) {
		this.productBuyList = productBuyList;
	}
	

}
