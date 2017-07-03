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

import com.dayuan.bean.GoodsInfo;
import com.dayuan.constant.ConstantCode;
import com.dayuan.exception.ParamException;
import com.dayuan.service.GoodsListService;
import com.dayuan.vo.ResultVo;

@Controller
@RequestMapping("/goods")
public class GoodsListController {
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Resource
	private GoodsListService goodsListService;
	/**
	 * 按照二级类目分类展示三级类目
	 * @param session
	 * @param response
	 * @param goodsInfo
	 * @return
	 */
	@RequestMapping(value = "/goodsList.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo goodsList(HttpSession session, HttpServletResponse response, GoodsInfo goodsInfo) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			goodsInfo.setCid(Long.parseLong("3"));
			//查询出商品表
			List<GoodsInfo> goods = goodsListService.selectGoods(goodsInfo);		
			resultVo.setCode(ConstantCode.SUCCESS.getCode());
			resultVo.setData(goods);
			return resultVo;
		} catch (Exception e) {
			resultVo.setCode(ConstantCode.FAIL.getCode());
			resultVo.setMsg(ConstantCode.FAIL.getMsg());
			logger.error(ConstantCode.FAIL.printMsg() + "," + e.getMessage());
			return resultVo;
		}
	}
	/**
	 * 商品详情
	 * @param session
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/goodsDetails.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo goodsDetails(HttpSession session, HttpServletResponse response, Long id) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			if (id == null || "".equals(id)) {
				//修改使用自定义异常
				throw new ParamException(ConstantCode.PARAM_EMPTY);
			}
			//查询出商品详情
			GoodsInfo goods = goodsListService.selectGoodsDetails(id);
			resultVo.setCode(ConstantCode.SUCCESS.getCode());
			resultVo.setData(goods);
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
}
