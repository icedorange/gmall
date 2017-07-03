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

import com.dayuan.bean.Consignee;
import com.dayuan.bean.User;
import com.dayuan.constant.ConstantCode;
import com.dayuan.exception.ParamException;
import com.dayuan.service.ConsigneeService;
import com.dayuan.vo.ConsigneeVo;
import com.dayuan.vo.ResultVo;

@Controller
@RequestMapping("/consignee")
public class ConsigneeController {
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Resource
	private ConsigneeService consigneeService;

	// 收货人信息查询接口
	@RequestMapping(value = "/showConsignee.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo showConsignee(HttpSession session, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			User user = (User) session.getAttribute("user");
			List<Consignee> consignee = consigneeService.selectConsignee(user.getId());
			ConsigneeVo consigneeVo = new ConsigneeVo();
			consigneeVo.setConsigneeVoList(consignee);
			resultVo.setCode(ConstantCode.SUCCESS.getCode());
			resultVo.setData(consigneeVo);
			return resultVo;
		} catch (Exception e) {
			resultVo.setCode(ConstantCode.FAIL.getCode());
			resultVo.setMsg(ConstantCode.FAIL.getMsg());
			logger.error(ConstantCode.FAIL.printMsg() + "," + e.getMessage());
			return resultVo;
		}
	}

	// 添加
	@RequestMapping(value = "/addConsignee.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo addConsignee(HttpSession session, HttpServletResponse response, Consignee consignee) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			User user = (User) session.getAttribute("user");
			consigneeService.addConsignee(user.getId(), consignee);
			resultVo.setCode(ConstantCode.SUCCESS.getCode());
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

	// 更新
	@RequestMapping(value = "/updateConsignee.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo updateConsignee(HttpSession session, HttpServletResponse response, Consignee consignee) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			User user = (User) session.getAttribute("user");
			consigneeService.updateConsignee(user.getId(), consignee);
			resultVo.setCode(ConstantCode.SUCCESS.getCode());
			return resultVo;
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode(ConstantCode.FAIL.getCode());
			resultVo.setMsg(ConstantCode.FAIL.getMsg());
			logger.error(ConstantCode.FAIL.printMsg() + "," + e.getMessage());
			return resultVo;
		}
	}

	// 删除
	@RequestMapping(value = "/deleteConsignee.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo deleteConsignee(HttpSession session, HttpServletResponse response, Consignee consignee) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			User user = (User) session.getAttribute("user");
			consigneeService.deleteConsignee(user.getId(), consignee);
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
