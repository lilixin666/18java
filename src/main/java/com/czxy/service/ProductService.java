package com.czxy.service;

import com.czxy.dao.ProductMapper;
import com.czxy.domain.Product;
import com.czxy.utils.MyBatisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class ProductService {


	// 根据商品的 主键 pid 查找商品信息

	public Product findProductByPid(String pid){
		//1.获取mapper
		ProductMapper mapper = MyBatisUtils.getMapper(ProductMapper.class);
		//2.执行查询
		Product product = mapper.selectByPrimaryKey(pid);
		//3.释放资源
		MyBatisUtils.close();
		//4.返回 对象
		return  product;
	}




	// 根据分类id 获取分类下对应的商品信息
	public PageInfo<Product> findPageInfoByCid(String cid,String pageNumber){

		int pageNum = 1;
		if(!"".equals(pageNumber) && pageNumber !=null){
			pageNum = Integer.parseInt(pageNumber);
		}


		//1.设置分页
		PageHelper.startPage(pageNum, 9);

		//2.指定dao中的查询  得到集合
		ProductMapper mapper = MyBatisUtils.getMapper(ProductMapper.class);
		List<Product> list = mapper.findProductsByCid(cid);
		MyBatisUtils.close();

		//3.把集合封装到 pageInfo对象中
		PageInfo<Product> pageInfo  = new PageInfo<>(list);

		//4.返回pageInfo对象
		return pageInfo;

	}




	// 查询热门商品
	public List<Product> findHotProducts(){
		//1.获取mapper
		ProductMapper mapper = MyBatisUtils.getMapper(ProductMapper.class);
		//2.执行查询
		List<Product> hotProducts = mapper.findHotProducts();
		//3.释放资源
		MyBatisUtils.close();
		//4.返回数据
		return  hotProducts;
	}

	// 查询 最新商品
	public  List<Product> findNewProduts(){
		//1.获取mapper
		ProductMapper mapper = MyBatisUtils.getMapper(ProductMapper.class);
		//2.执行查询
		List<Product> newProducts = mapper.findNewProducts();
		//3.释放资源
		MyBatisUtils.close();
		//4.返回数据
		return  newProducts;
	}
}
