package com.czxy.dao;

import com.czxy.domain.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductMapper extends Mapper<Product> {



	// 查询热门商品
	@Select("select * from product where is_hot = 1 limit 0,9")
	public List<Product> findHotProducts();

	//查询最新商品
	@Select("select * from product order by pdate desc limit 0,9")
	public List<Product> findNewProducts();

	@Select("select * from product where cid = #{cid}")
	public List<Product> findProductsByCid(@Param("cid") String cid);

	// 根据 pid 查询 product信息
	@Select("select * from product where pid =#{pid}")
	public Product findByPid(@Param("pid") String pid);

}
