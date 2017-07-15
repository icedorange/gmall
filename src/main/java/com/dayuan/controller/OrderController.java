package com.dayuan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuan.bean.Order;
import com.dayuan.bean.User;
import com.dayuan.constant.ConstantCode;
import com.dayuan.dto.GoodsDTO;
import com.dayuan.dto.OrderDTO;
import com.dayuan.exception.ParamException;
import com.dayuan.exception.StockExctption;
import com.dayuan.service.OrderService;
import com.dayuan.vo.ResultVo;

@Controller
@RequestMapping("/order")
public class OrderController {
	private static Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Resource
	private OrderService orderService;

	/**
	 * 查询所有订单
	 * 
	 * @param session
	 * @param response
	 * @param transactionStatus
	 * @return
	 */
	@RequestMapping(value = "/orderItem.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo orderItem(HttpSession session, HttpServletResponse response, Integer transactionStatus) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			// transactionStatus=2000查询所有订单
			if (transactionStatus == null) {
				throw new ParamException(ConstantCode.PARAM_EMPTY);
			}
			// 从session中获取用户，
			User user = (User) session.getAttribute("user");
			List<Order> order = orderService.selectOrder(transactionStatus, user.getId());
			resultVo.setCode(ConstantCode.SUCCESS.getCode());
			resultVo.setData(order);
			return resultVo;
		} catch (ParamException pe) {
			resultVo.setCode(ConstantCode.PARAM_EMPTY.getCode());
			resultVo.setMsg(ConstantCode.PARAM_EMPTY.getMsg());
			logger.error(ConstantCode.PARAM_EMPTY.printMsg() + "," + pe.getMessage());
			return resultVo;
		} catch (Exception e) {
			resultVo.setCode(ConstantCode.FAIL.getCode());
			resultVo.setMsg(ConstantCode.FAIL.getMsg());
			logger.error(ConstantCode.FAIL.printMsg() + "," + e.getMessage());
			return resultVo;
		}
	}

	// 订单详情
	@RequestMapping(value = "/orderInfo.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo orderInfo(HttpSession session, HttpServletResponse response, Long id) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			// transactionStatus=2000查询所有订单
			if (id == null) {
				throw new ParamException(ConstantCode.PARAM_EMPTY);
			}

			Order order = orderService.selectOrderById(id);

			order.setOrderDetails(orderService.selectOrderDetails(id));

			resultVo.setCode(ConstantCode.SUCCESS.getCode());
			resultVo.setData(order);
			return resultVo;
		} catch (ParamException pe) {
			resultVo.setCode(ConstantCode.PARAM_EMPTY.getCode());
			resultVo.setMsg(ConstantCode.PARAM_EMPTY.getMsg());
			logger.error(ConstantCode.PARAM_EMPTY.printMsg() + "," + pe.getMessage());
			return resultVo;
		} catch (Exception e) {
			resultVo.setCode(ConstantCode.FAIL.getCode());
			resultVo.setMsg(ConstantCode.FAIL.getMsg());
			logger.error(ConstantCode.FAIL.printMsg() + "," + e.getMessage());
			return resultVo;
		}
	}

	// 确认订单
	@RequestMapping(value = "/orderConfirm.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo orderConfirm(HttpSession session, HttpServletResponse response, OrderDTO orderDTO) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			GoodsDTO goodsDTO = new GoodsDTO();
			goodsDTO.setGoodsid(1);
			goodsDTO.setNumber(1);
			List<GoodsDTO> goodsList = new ArrayList<>();
			goodsList.add(goodsDTO);
			orderDTO.setGoodsList(goodsList);
			//收货人地址详情，商品详情，
			// 判断参数
			/*if (orderDTO == null) {
				throw new ParamException(ConstantCode.PARAM_EMPTY);
			}*/
			// 从session中获取用户，
			User user = (User) session.getAttribute("user");
			// 返回订单号
			Map<String, Object> porder = orderService.orderConfirm(orderDTO, user.getId());
			resultVo.setCode(ConstantCode.SUCCESS.getCode());
			resultVo.setData(porder);
			return resultVo;
		} catch (ParamException pe) {
			resultVo.setCode(ConstantCode.PARAM_EMPTY.getCode());
			resultVo.setMsg(ConstantCode.PARAM_EMPTY.getMsg());
			logger.error(ConstantCode.PARAM_EMPTY.printMsg() + "," + pe.getMessage());
			return resultVo;
		} catch (StockExctption se) {
			resultVo.setCode(se.getConstantCode().getCode());
			resultVo.setMsg(se.getConstantCode().getMsg());
			logger.error(se.getConstantCode().printMsg());
			return resultVo;
		} catch (Exception e) {
			resultVo.setCode(ConstantCode.FAIL.getCode());
			resultVo.setMsg(ConstantCode.FAIL.getMsg());
			logger.error(ConstantCode.FAIL.printMsg() + "," + e.getMessage());
			return resultVo;
		}
	}
	
	
}
