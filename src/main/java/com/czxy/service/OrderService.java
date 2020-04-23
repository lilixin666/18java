package com.czxy.service;

import com.czxy.dao.OrderItemMapper;
import com.czxy.dao.OrderMapper;
import com.czxy.domain.Order;
import com.czxy.domain.OrderItem;
import com.czxy.utils.MyBatisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class OrderService {

	// 根据指定的 oid 更新 支付状态
	public void updateStateByOid(String oid){
		//1.获取mapper
		OrderMapper mapper = MyBatisUtils.getMapper(OrderMapper.class);
		//2.执行查询
		mapper.updateStateByOid(oid);
		//3.释放资源
		MyBatisUtils.commitAndClose();

	}


	//根据 oid 更新收货人信息
	public void updateInfo(Order order){
		//1.获取mapper
		OrderMapper mapper = MyBatisUtils.getMapper(OrderMapper.class);
		//2.执行查询
		mapper.updateInfo(order.getAddress(), order.getName(), order.getTelephone(), order.getOid());
		//3.释放资源
		MyBatisUtils.commitAndClose();

	}



	// 根据oid 返回 order对象
	public Order findByOid(String oid){
		//1.获取mapper
		OrderMapper mapper = MyBatisUtils.getMapper(OrderMapper.class);
		//2.执行查询
		Order order = mapper.findByOid(oid);
		//3.释放资源
		MyBatisUtils.close();
		//4.返回结果
		return order;
	}


	//根据uid 返回 pageInfo
	public PageInfo<Order> findPageInfoByUid(String uid,String pageNumber){

		//1.获取mapper
		OrderMapper mapper = MyBatisUtils.getMapper(OrderMapper.class);
		//2.开启分页
		int pageNum =1;
		if(pageNumber != null && !"".equalsIgnoreCase(pageNumber)){
			pageNum = Integer.parseInt(pageNumber);
		}
		PageHelper.startPage(pageNum, 3);

		//3.执行查询   list
		List<Order> list = mapper.findOrderListByUid(uid);
		MyBatisUtils.close();

		//4.使用pageInfo 封装 list
		PageInfo<Order> pageInfo = new PageInfo<>(list);

		//5.返回 pageInfo
		return pageInfo;


	}

	 //根据uid 返回 pageInfo
	public PageInfo<Order> findPageInfoByUid(String uid){

		//1.获取mapper
		OrderMapper mapper = MyBatisUtils.getMapper(OrderMapper.class);
		//2.开启分页
		int pageNum =1;

		PageHelper.startPage(pageNum, 3);

		//3.执行查询   list
		List<Order> list = mapper.findOrderListByUid(uid);
		MyBatisUtils.close();

		//4.使用pageInfo 封装 list
		PageInfo<Order> pageInfo = new PageInfo<>(list);

		//5.返回 pageInfo
		return pageInfo;


	}


	// 把订单信息存储到 数据库中
	public void saveOrder(Order order){

		// 1.调用OrderMapper 把 order的基本信息存储到 数据库
		OrderMapper mapper = MyBatisUtils.getMapper(OrderMapper.class);
		mapper.insert(order);
		MyBatisUtils.commitAndClose();


		//2.遍历 order中的 orderItemList  把每个元素都使用 OrderItemMapper 存储到数据库中
		List<OrderItem> orderItemList = order.getOrderItemList();
		OrderItemMapper orderItemMapper = MyBatisUtils.getMapper(OrderItemMapper.class);
		for (OrderItem orderItem : orderItemList) {
			orderItemMapper.insert(orderItem);
		}
		MyBatisUtils.commitAndClose();
	}
}
