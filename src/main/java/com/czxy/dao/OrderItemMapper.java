package com.czxy.dao;

import com.czxy.domain.OrderItem;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderItemMapper extends Mapper<OrderItem> {


	// 根据传递的 oid 查询所有的 orderItemList
	@Select("select * from orderItem where oid = #{oid}")
	@Results({
			@Result(property = "itemid",column = "itemid"),
			@Result(property = "quantity",column = "quantity"),
			@Result(property = "sub_price",column = "sub_price"),
			@Result(property = "pid",column = "pid"),
			@Result(property = "oid",column = "oid"),
			@Result(property = "product",one = @One(select = "com.czxy.dao.ProductMapper.findByPid"),column = "pid")
	})
	public List<OrderItem> findOrderItemListByOid(@Param("oid") String oid);

}
