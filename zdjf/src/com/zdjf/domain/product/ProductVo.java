package com.zdjf.domain.product;

import java.util.List;

public class ProductVo extends Product {

	private static final long serialVersionUID = 4755934252755612560L;
	private static List<Product> productList;
	public ProductVo(){
		super();
	}
	public ProductVo(Long id){
		super();
		this.id=id;
	}
	public static List<Product> getProductList() {
		return productList;
	}
	public static void setProductList(List<Product> productList) {
		ProductVo.productList = productList;
	}

}
