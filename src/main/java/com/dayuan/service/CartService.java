package com.dayuan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dayuan.bean.Cart;
import com.dayuan.bean.GoodsInfo;
import com.dayuan.bean.Stock;
import com.dayuan.bean.UserCart;
import com.dayuan.constant.ConstantCode;
import com.dayuan.dao.CartMapper;
import com.dayuan.dao.GoodsInfoMapper;
import com.dayuan.dao.StockMapper;
import com.dayuan.dao.UserCartMapper;
import com.dayuan.dto.CartDTO;
import com.dayuan.dto.UserCartDTO;
import com.dayuan.exception.ParamException;
import com.dayuan.vo.CartGoodsVo;
import com.dayuan.vo.ShopCartVo;

@Service
@Transactional
public class CartService {
	@Resource
	private CartMapper cartMapper;
	@Resource
	private GoodsInfoMapper goodsInfoMapper;
	@Resource
	private StockMapper stockMapper;
	@Resource
	private UserCartMapper userCartMapper;

	// 查询购物车
	public ShopCartVo selectCart(Long uid,Integer isSelected) {
		ShopCartVo shopCartVo = new ShopCartVo();
		try {
			
			List<CartGoodsVo> cartGoodsVoList = new ArrayList<>();
			// 查询g_user_cart表返回id
			UserCart userCart = userCartMapper.selectUserCart(uid);
			// 查询购物车表返回商品id，数量
			List<Cart> cart = cartMapper.selectCart(userCart.getId(),isSelected);
			Map<String, Object> params = new HashMap<>();
			for (Cart cartGoods : cart) {
				// 根据商品id查询商品信息
				GoodsInfo goodsInfo = goodsInfoMapper.selectGoodsInfo(cartGoods.getGid());
				// 计算每种商品（同规格）总价
				int sum = cartGoods.getNumber() * goodsInfo.getPromotionPrice();
				// 查询库存
				params.put("gid", goodsInfo.getId());
				Stock stock = stockMapper.selectStock(params);
				CartGoodsVo cartGoodsVo = new CartGoodsVo(cartGoods.getGid(), goodsInfo.getProduct(),
						goodsInfo.getPromotionPrice(), goodsInfo.getOriginalPrice(), goodsInfo.getPicture(),
						cartGoods.getNumber(), stock.getNumber(), sum);
				cartGoodsVoList.add(cartGoodsVo);
			}
			shopCartVo.setCartGoodsVo(cartGoodsVoList);
			shopCartVo.setId(userCart.getId());
			shopCartVo.setUid(userCart.getUid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return shopCartVo;
	}

	// 购物车添加
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void addCart(Long uid, Cart cart) throws Exception {
		// 查询g_user_cart表id
		UserCart userCart = userCartMapper.selectUserCart(uid);
		if (userCart == null) {
			// 添加数据
			userCartMapper.insertUserCart(uid);
		}
		// 验证商品id是否存在
		GoodsInfo goodsInfo = goodsInfoMapper.selectGoodsInfo(cart.getGid());
		if (goodsInfo == null) {
			throw new ParamException(ConstantCode.PARAM_EMPTY.getMsg());
		}
		// 查询购物车中是否已经存在商品,有就更新，没有就插入
		Cart cartGoods = cartMapper.selectCartGoods(cart);
		cart.setUcid(userCart.getId());
		if (cartGoods == null) {
			// 插入g_cart表
			cartMapper.insertCart(cart);
		} else {
			// 更新g_cart表
			cart.setNumber(cartGoods.getNumber() + cart.getNumber());
			cartMapper.updateCart(cart);
		}
	}

	// 删除商品
	public void deleteCart(Long uid, UserCartDTO userCartDTO) throws Exception {

		// uid=(long) 29;
		List<CartDTO> cartDTO = userCartDTO.getCartDTO();
		// List<CartDTO> cartDTO = new ArrayList<>();
		// CartDTO cartDTO1 = new CartDTO();
		// cartDTO1.setGid(Long.parseLong("2"));

		// cartDTO.add(cartDTO1);
		// 验证商品存在，购物车
		UserCart userCart = userCartMapper.selectUserCart(uid);
		if (userCart == null) {
			throw new ParamException(ConstantCode.PARAM_EMPTY.getMsg());
		}
		// 遍历集合
		for (CartDTO cartGoods : cartDTO) {
			Cart cart = new Cart();
			cart.setGid(cartGoods.getGid());
			cart.setUcid(userCart.getId());
			System.out.println(String.valueOf(cart.getUcid()));
			Cart cartTemp = cartMapper.selectCartGoods(cart);
			if (cartTemp == null) {
				throw new ParamException(ConstantCode.PARAM_EMPTY.getMsg());
			}
			cartMapper.deleteCart(cart);
		}

	}

	// 更新商品
	public void updateCart(Long uid, UserCartDTO userCartDTO) throws Exception {
		// uid=(long) 29;
		List<CartDTO> cartDTO = userCartDTO.getCartDTO();
		// List<CartDTO> cartDTO = new ArrayList<>();
		// CartDTO cartDTO1 = new CartDTO();
		// cartDTO1.setGid(Long.parseLong("2"));
		// cartDTO1.setNumber(3);
		// cartDTO.add(cartDTO1);
		// 验证商品存在，购物车
		UserCart userCart = userCartMapper.selectUserCart(uid);
		if (userCart == null) {
			throw new ParamException(ConstantCode.PARAM_EMPTY.getMsg());
		}
		for (CartDTO cartGoods : cartDTO) {
			Cart cart = new Cart();
			cart.setGid(cartGoods.getGid());
			cart.setNumber(cartGoods.getNumber());
			cart.setUcid(userCart.getId());
			Cart cartTemp = cartMapper.selectCartGoods(cart);
			if (cartTemp == null) {
				throw new ParamException(ConstantCode.PARAM_EMPTY.getMsg());
			}
			cartMapper.updateCart(cart);
		}
	}
}
