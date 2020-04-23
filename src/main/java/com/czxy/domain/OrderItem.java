package com.czxy.domain;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * -- 5 创建订单项表
 * CREATE TABLE `orderitem` (
 *   `itemid` VARCHAR(32) NOT NULL,
 *   `quantity` INT(11) DEFAULT NULL,			#购买数量
 *   `sub_price` DOUBLE DEFAULT NULL,			#小计
 *   `pid` VARCHAR(32) DEFAULT NULL,			#购买商品的id
 *   `oid` VARCHAR(32) DEFAULT NULL,			#订单项所在订单id
 *   PRIMARY KEY (`itemid`),
 *   KEY `order_item_fk_0001` (`pid`),
 *   KEY `order_item_fk_0002` (`oid`),
 *   CONSTRAINT `order_item_fk_0001` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
 *   CONSTRAINT `order_item_fk_0002` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`)
 * ) ;
 */
@Table(name="orderitem")
public class OrderItem {
	@Id
	private  String itemid;
	private  Integer quantity;
	private  Double sub_price;
	private  String pid;
	private  String oid;

	//商品对象
	private Product product;


	@Override
	public String toString() {
		return "OrderItem{" +
				"itemid='" + itemid + '\'' +
				", quantity=" + quantity +
				", sub_price=" + sub_price +
				", pid='" + pid + '\'' +
				", oid='" + oid + '\'' +
				", product=" + product +
				'}';
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getSub_price() {
		return sub_price;
	}

	public void setSub_price(Double sub_price) {
		this.sub_price = sub_price;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderItem(String itemid, Integer quantity, Double sub_price, String pid, String oid, Product product) {

		this.itemid = itemid;
		this.quantity = quantity;
		this.sub_price = sub_price;
		this.pid = pid;
		this.oid = oid;
		this.product = product;
	}

	public OrderItem() {

	}
}
