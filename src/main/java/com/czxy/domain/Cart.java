package com.czxy.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart {

	// 该集合 用来 存储 多个购物车项   key:商品的 pid   value : 购物车项
	private Map<String,CartItem> map = new HashMap<>();

	private Double totalMoney;  //  商品总金额     =  所有购物车项的小计 之和


	//添加购物车
	public  void addCart(Product product,Integer count){
		// 情况1: 之前没添加过该商品  -->  创建新的 购物车项 把数据 存里
		// 通过 product的 pid 去map中获取 cartItem对象 ct  ,如果 ct == null  此时要创建一个cartItem对象  给 ct赋值, 然后把商品信息和 count添加到ct中
		// 再把 ct 添加到 map集合中

		// 情况2: 之前添加过该商品  只需获取购物车项 改变 count
		// ct !=null  更改 ct的 count 即可

		CartItem ct = map.get(product.getPid());
		if(ct == null){

			ct = new CartItem(product, count, count*product.getShop_price());
			map.put(product.getPid(), ct);

		}else{

			ct.setCount(ct.getCount()+count);
		}


	}
	//清空购物车
	public void clear(){
		map.clear();
	}

	//删除购物车项
	public void removeCartItem(String pid){
		map.remove(pid);
	}


	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}

	public Double getTotalMoney() {
		// 所有的 小计的 综合

		//遍历 map集合 获取所有的小计 求和
		// 防止上一次计算的结果 影响到 这一次计算, 所有每次计算之前 清零
		totalMoney= 0.0;

		Set<String> keySet = map.keySet();

		for (String key : keySet) {
			CartItem cartItem = map.get(key);
			totalMoney+=cartItem.getSubTotal();
		}

		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Cart(Map<String, CartItem> map, Double totalMoney) {

		this.map = map;
		this.totalMoney = totalMoney;
	}

	public Cart() {

	}
}
