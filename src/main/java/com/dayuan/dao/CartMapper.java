package com.dayuan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.dayuan.bean.Cart;

@MapperScan
public interface CartMapper {
	/**
	 * 根据ucid查询购物车商品id，数量
	 * 
	 * @param ucid
	 * @return
	 */
	List<Cart> selectCart(@Param("ucid") Long ucid,@Param("isSelected") Integer isSelected);
	
	Cart selectCartGoods(Cart cart);

	/**
	 * 购物车更新商品数量
	 * 
	 * @param cart
	 * @return
	 */
	int updateCart(Cart cart);

	/**
	 * 购物车添加商品
	 * 
	 * @param cart
	 * @return
	 */
	int insertCart(Cart cart);
	/**
	 * 删除购物车商品
	 * @param cart
	 */
	void deleteCart(Cart cart);

}
