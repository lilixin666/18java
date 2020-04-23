package com.czxy.domain;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * CREATE TABLE `product` (
 *   `pid` VARCHAR(32) NOT NULL,
 *   `pname` VARCHAR(50) DEFAULT NULL,		#商品名称
 *   `market_price` DOUBLE DEFAULT NULL,	#商场价
 *   `shop_price` DOUBLE DEFAULT NULL,		#商城价
 *   `pimage` VARCHAR(200) DEFAULT NULL,	#商品图片路径
 *   `pdate` DATE DEFAULT NULL,			#上架时间
 *   `is_hot` INT(11) DEFAULT NULL,		#是否热门：0=不热门,1=热门
 *   `pdesc` VARCHAR(255) DEFAULT NULL,	#商品描述
 *   `pflag` INT(11) DEFAULT 0,			#商品标记：0=未下架(默认值),1=已经下架
 *   `cid` VARCHAR(32) DEFAULT NULL,		#分类id
 *   PRIMARY KEY (`pid`),
 *   KEY `product_fk_0001` (`cid`),
 *   CONSTRAINT `product_fk_0001` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
 * ) ENGINE=INNODB DEFAULT CHARSET=utf8;
 */
@Table(name="product")
public class Product {

	@Id
	private String pid ;
	private String pname ;
	private Double market_price ;
	private Double shop_price ;
	private String pimage ;
	private Date pdate ;
	private Integer is_hot ;
	private String pdesc ;
	private Integer pflag ;
	private String cid ;


	@Override
	public String toString() {
		return "Product{" +
				"pid='" + pid + '\'' +
				", pname='" + pname + '\'' +
				", market_price=" + market_price +
				", shop_price=" + shop_price +
				", pimage='" + pimage + '\'' +
				", pdate=" + pdate +
				", is_hot=" + is_hot +
				", pdesc='" + pdesc + '\'' +
				", pflag=" + pflag +
				", cid='" + cid + '\'' +
				'}';
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Double getMarket_price() {
		return market_price;
	}

	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}

	public Double getShop_price() {
		return shop_price;
	}

	public void setShop_price(Double shop_price) {
		this.shop_price = shop_price;
	}

	public String getPimage() {
		return pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public Integer getIs_hot() {
		return is_hot;
	}

	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public Integer getPflag() {
		return pflag;
	}

	public void setPflag(Integer pflag) {
		this.pflag = pflag;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Product(String pid, String pname, Double market_price, Double shop_price, String pimage, Date pdate, Integer is_hot, String pdesc, Integer pflag, String cid) {

		this.pid = pid;
		this.pname = pname;
		this.market_price = market_price;
		this.shop_price = shop_price;
		this.pimage = pimage;
		this.pdate = pdate;
		this.is_hot = is_hot;
		this.pdesc = pdesc;
		this.pflag = pflag;
		this.cid = cid;
	}

	public Product() {

	}
}
