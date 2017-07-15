package com.dayuan.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuan.constant.ConstantCode;
import com.dayuan.vo.ResultVo;
import com.dayuanit.pay.domain.PayType;
import com.dayuanit.pay.service.PayService;

@Controller
@RequestMapping("/pay")
public class PayController implements InitializingBean {
	@Resource
	private PayService payService;
	@Resource
	private Handler handler;

	@RequestMapping(value = "/payType.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo payType(HttpSession session, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();

			List<PayType> listPayType = payService.listPayType();
			resultVo.setCode(ConstantCode.SUCCESS.getCode());
			resultVo.setData(listPayType);
			return resultVo;
		} catch (Exception e) {
			return resultVo;
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Thread thr = new Thread(new Runnable() {
			@Override
			public void run() {
				handler.getOrder();
			}
		});
		thr.start();
		
		
	}
	
}
