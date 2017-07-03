package com.dayuan.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuan.bean.Cart;
import com.dayuan.bean.User;
import com.dayuan.constant.ConstantCode;
import com.dayuan.dto.UserCartDTO;
import com.dayuan.exception.ParamException;
import com.dayuan.service.CartService;
import com.dayuan.vo.ResultVo;
import com.dayuan.vo.ShopCartVo;

@Controller
@RequestMapping("/cart")
public class CartController {
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Resource
	private CartService cartService;

	// 购物车查询接口
	@RequestMapping(value = "/showCart.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo shopCart(HttpSession session, HttpServletResponse response,Integer isSelected) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			User user = (User) session.getAttribute("user");
			ShopCartVo shopCartVo = cartService.selectCart(user.getId(),isSelected);
			resultVo.setCode(ConstantCode.SUCCESS.getCode());
			resultVo.setData(shopCartVo);
			return resultVo;
		} catch (Exception e) {
			resultVo.setCode(ConstantCode.FAIL.getCode());
			resultVo.setMsg(ConstantCode.FAIL.getMsg());
			logger.error(ConstantCode.FAIL.printMsg() + "," + e.getMessage());
			return resultVo;
		}
	}

	// 添加购物车
	@RequestMapping(value = "/addCart.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo addCart(HttpSession session, HttpServletResponse response, Cart cart) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			User user = (User) session.getAttribute("user");
			cartService.addCart(user.getId(), cart);
			resultVo.setCode(ConstantCode.SUCCESS.getCode());
			return resultVo;
		} catch (ParamException pe) {
			resultVo.setCode(ConstantCode.PARAM_EMPTY.getCode());
			resultVo.setMsg(ConstantCode.PARAM_EMPTY.getMsg());
			logger.error(ConstantCode.PARAM_EMPTY.printMsg() + "," + pe.getMessage());
			return resultVo;
		}catch (Exception e) {
			resultVo.setCode(ConstantCode.FAIL.getCode());
			resultVo.setMsg(ConstantCode.FAIL.getMsg());
			logger.error(ConstantCode.FAIL.printMsg() + "," + e.getMessage());
			return resultVo;
		}
	}
	
	// 更新购物车商品数量
	@RequestMapping(value = "/updateCart.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo udCart(HttpSession session, HttpServletResponse response,UserCartDTO userCartDTO) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			//参数 商品集合
			//遍历集合，验证购物车id，用户
			resultVo = new ResultVo();
			User user = (User) session.getAttribute("user");
			cartService.updateCart(user.getId(),userCartDTO);
			resultVo.setCode(ConstantCode.SUCCESS.getCode());
			return resultVo;
		} catch (Exception e) {
			resultVo.setCode(ConstantCode.FAIL.getCode());
			resultVo.setMsg(ConstantCode.FAIL.getMsg());
			logger.error(ConstantCode.FAIL.printMsg() + "," + e.getMessage());
			return resultVo;
		}
	}
	// 删除购物车商品
	@RequestMapping(value = "/deleteCart.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo deleteCart(HttpSession session, HttpServletResponse response,UserCartDTO userCartDTO ) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			User user = (User) session.getAttribute("user");
			cartService.deleteCart(user.getId(),userCartDTO);
			resultVo.setCode(ConstantCode.SUCCESS.getCode());
			return resultVo;
		} catch (Exception e) {
			resultVo.setCode(ConstantCode.FAIL.getCode());
			resultVo.setMsg(ConstantCode.FAIL.getMsg());
			logger.error(ConstantCode.FAIL.printMsg() + "," + e.getMessage());
			return resultVo;
		}
	}
}
