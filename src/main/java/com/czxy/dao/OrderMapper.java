package com.czxy.dao;

import com.czxy.domain.Order;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderMapper extends Mapper<Order> {


	// 根据 uid  查询 响应的 orderList
	@Select("select * from orders where uid = #{uid}")
	@Results(id="rel_order",value = {
			@Result(property = "oid",column = "oid"),
			@Result(property = "ordertime",column = "ordertime"),
			@Result(property = "total_price",column = "total_price"),
			@Result(property = "state",column = "state"),
			@Result(property = "address",column = "address"),
			@Result(property = "name",column = "name"),
			@Result(property = "telephone",column = "telephone"),
			@Result(property = "uid",column = "uid"),
			@Result(property = "orderItemList",many = @Many(select = "com.czxy.dao.OrderItemMapper.findOrderItemListByOid"),column = "oid")
	})
	public List<Order> findOrderListByUid(@Param("uid") String uid);


	@Select("select * from orders where oid = #{oid}")
	@ResultMap("rel_order")
	public Order findByOid(@Param("oid") String oid);


	//更新指定oid的 收货人信息
	@Update("update orders set address=#{address},name=#{name},telephone=#{telephone} where oid = #{oid}")
	public void updateInfo(@Param("address") String address, @Param("name") String name, @Param("telephone") String telephone, @Param("oid") String oid);



	@Update("update orders set state=2 where oid = #{oid}")
	public void updateStateByOid(@Param("oid") String oid);


}
