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

import com.dayuan.constant.ConstantCode;
import com.dayuan.service.CategoryService;
import com.dayuan.vo.CategoryVo;
import com.dayuan.vo.ResultVo;

@Controller
@RequestMapping("/category")
public class CategoryController {
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Resource
	private CategoryService categoryService;

	/**
	 * 展示类目
	 * 
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/showCategory.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo showCategory(HttpSession session, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			List<CategoryVo> categoryVoList = categoryService.selectCategoryVo(0);
			resultVo.setCode(ConstantCode.SUCCESS.getCode());
			resultVo.setData(categoryVoList);
			return resultVo;
		} catch (Exception e) {
			resultVo.setCode(ConstantCode.FAIL.getCode());
			resultVo.setMsg(ConstantCode.FAIL.getMsg());
			logger.error(ConstantCode.FAIL.printMsg() + "," + e.getMessage());
			return resultVo;
		}
	}
}
