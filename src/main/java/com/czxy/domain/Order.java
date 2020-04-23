package com.czxy.domain;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * -- 4 创建订单表
 * CREATE TABLE `orders` (
 *   `oid` VARCHAR(32) NOT NULL,			#订单编号
 *   `ordertime` DATETIME DEFAULT NULL,		#下单时间
 *   `total_price` DOUBLE DEFAULT NULL,		#总价
 *   `state` INT(11) DEFAULT NULL,				#订单状态：1=未付款;2=已付款,未发货;3=已发货,没收货;4=收货,订单结束
 *   `address` VARCHAR(30) DEFAULT NULL,		#收获地址
 *   `name` VARCHAR(20) DEFAULT NULL,			#收获人
 *   `telephone` VARCHAR(20) DEFAULT NULL,		#收货人电话
 *   `uid` VARCHAR(32) DEFAULT NULL,		#用户的id 该订单属于哪个用户
 *   PRIMARY KEY (`oid`),
 *   KEY `order_fk_0001` (`uid`),
 *   CONSTRAINT `order_fk_0001` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
 * ) ;
 */
@Table(name="orders")
public class Order {
	@Id
	private String  oid ;
	private Date ordertime ;
	private Double  total_price ;
	private Integer  state ;
	private String  address ;
	private String  name ;
	private String  telephone ;
	private String  uid ;


	//订单项集合
	List<OrderItem> orderItemList = new ArrayList<>();

	@Override
	public String toString() {
		return "Order{" +
				"oid='" + oid + '\'' +
				", ordertime=" + ordertime +
				", total_price=" + total_price +
				", state=" + state +
				", address='" + address + '\'' +
				", name='" + name + '\'' +
				", telephone='" + telephone + '\'' +
				", uid='" + uid + '\'' +
				", orderItemList=" + orderItemList +
				'}';
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public Order(String oid, Date ordertime, Double total_price, Integer state, String address, String name, String telephone, String uid, List<OrderItem> orderItemList) {

		this.oid = oid;
		this.ordertime = ordertime;
		this.total_price = total_price;
		this.state = state;
		this.address = address;
		this.name = name;
		this.telephone = telephone;
		this.uid = uid;
		this.orderItemList = orderItemList;
	}

	public Order() {

	}
}
