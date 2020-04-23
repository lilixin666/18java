package com.czxy.domain;

/**
 * CREATE TABLE `category` (
 *   `cid` VARCHAR(32) NOT NULL,
 *   `cname` VARCHAR(20) DEFAULT NULL,	#分类名称
 *   PRIMARY KEY (`cid`)
 * ) ENGINE=INNODB DEFAULT CHARSET=utf8;
 */
public class Category {

	private String cid;
	private String cname;


	@Override
	public String toString() {
		return "Category{" +
				"cid='" + cid + '\'' +
				", cname='" + cname + '\'' +
				'}';
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Category(String cid, String cname) {

		this.cid = cid;
		this.cname = cname;
	}

	public Category() {

	}
}
