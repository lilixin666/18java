package com.czxy.domain;

public class CartItem {


	private Product product; // 商品对象
	private Integer count; // 要购买的数量
	private Double subTotal; // 小计      =  数量 * 单价

	@Override
	public String toString() {
		return "CartItem{" +
				"product=" + product +
				", count=" + count +
				", subTotal=" + subTotal +
				'}';
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSubTotal() {
		subTotal = count * product.getShop_price();
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public CartItem(Product product, Integer count, Double subTotal) {

		this.product = product;
		this.count = count;
		this.subTotal = subTotal;
	}

	public CartItem() {

	}
}
