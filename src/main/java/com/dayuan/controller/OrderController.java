package com.dayuan.controller;

import java.util.List;

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
import com.dayuan.service.OrderService;
import com.dayuan.utils.ParamException;
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
				throw new ParamException("参数错误");
			}
			// 从session中获取用户，
			User user = (User) session.getAttribute("user");
			if (user == null) {
				resultVo.setCode(ConstantCode.FAIL.getValue());
				resultVo.setMsg("请重新登录");
				return resultVo;
			}
			List<Order> order = orderService.selectOrder(transactionStatus, user.getId());
			resultVo.setCode(ConstantCode.SUCCESS.getValue());
			resultVo.setData(order);
			return resultVo;
		} catch (ParamException e) {
			resultVo.setCode(ConstantCode.FAIL.getValue());
			resultVo.setMsg(e.getMessage());
			return resultVo;
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode(ConstantCode.FAIL.getValue());
			resultVo.setMsg("网络不稳定，请稍后再试");
			logger.error("订单列表查询：" + e.getMessage());
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
				throw new ParamException("参数错误");
			}
			// 从session中获取用户，

			User user = (User) session.getAttribute("user");
			if (user == null) {
				resultVo.setCode(ConstantCode.FAIL.getValue());
				resultVo.setMsg("请重新登录");
				return resultVo;
			}

			Order order = orderService.selectOrderById(id);

			order.setOrderDetails(orderService.selectOrderDetails(id));

			resultVo.setCode(ConstantCode.SUCCESS.getValue());
			resultVo.setData(order);
			return resultVo;
		} catch (ParamException e) {
			resultVo.setCode(ConstantCode.FAIL.getValue());
			resultVo.setMsg(e.getMessage());
			return resultVo;
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode(ConstantCode.FAIL.getValue());
			resultVo.setMsg("网络不稳定，请稍后再试");
			logger.error("订单列表查询：" + e.getMessage());
			return resultVo;
		}
	}

	// 确认订单
	@RequestMapping(value = "/orderConfirm.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo orderConfirm(HttpSession session, HttpServletResponse response, Order order, Long id,
			Integer number) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			// 判断参数
			if (id == null || number == null || order == null) {
				throw new ParamException("参数错误");
			}
			// 从session中获取用户，
			User user = (User) session.getAttribute("user");
			if (user == null) {
				resultVo.setCode(ConstantCode.FAIL.getValue());
				resultVo.setMsg("请重新登录");
				return resultVo;
			}
			// 返回订单号
			Long orderid = orderService.orderConfirm(id, number, order, user.getId());
			resultVo.setCode(ConstantCode.SUCCESS.getValue());
			resultVo.setData(orderid);
			return resultVo;
		} catch (ParamException e) {
			resultVo.setCode(ConstantCode.FAIL.getValue());
			resultVo.setMsg(e.getMessage());
			return resultVo;
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode(ConstantCode.FAIL.getValue());
			resultVo.setMsg("网络不稳定，请稍后再试");
			logger.error("订单计算：" + e.getMessage());
			return resultVo;
		}
	}
}
