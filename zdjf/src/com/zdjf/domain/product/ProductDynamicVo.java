package com.zdjf.domain.product;

import java.util.List;

public class ProductDynamicVo extends ProductDynamic {

	private static final long serialVersionUID = -8899780579523956850L;
	public ProductDynamicVo(){
		super();
	}
	public ProductDynamicVo(Long id){
		super();
		this.id=id;
	}
	List<ProductDynamic> productDynamicList;
	public List<ProductDynamic> getProductDynamicList() {
		return productDynamicList;
	}
	public void setProductDynamicList(List<ProductDynamic> productDynamicList) {
		this.productDynamicList = productDynamicList;
	}
	
}
