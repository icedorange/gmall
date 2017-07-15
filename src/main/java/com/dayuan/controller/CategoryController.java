package com.dayuan.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
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
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 展示类目
	 * 
	 * @param session
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/showCategory.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo showCategory(HttpSession session, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			List<CategoryVo> categoryVoList = null;
			// Object obj =
			// redisTemplate.opsForList().rightPop("redis:category:list");
			// if (obj == null) {
			// categoryVoList = categoryService.selectCategoryVo(0);
			// redisTemplate.opsForList().leftPushAll("redis:category:list",
			// categoryVoList);
			// } else {
			// if (obj instanceof List) {
			// categoryVoList = (List<CategoryVo>) obj;
			// }
			// }
			Object obj = redisTemplate.opsForValue().get("redis_category_list");
			if (obj == null) {
				// 放入的类需要实现序列化接口
				categoryVoList = categoryService.selectCategoryVo(0);
				redisTemplate.opsForValue().set("redis_category_list", categoryVoList);
			} else {
				if (obj instanceof List) {
					categoryVoList = (List<CategoryVo>) obj;
				}
			}

			resultVo.setCode(ConstantCode.SUCCESS.getCode());
			resultVo.setData(categoryVoList);
			return resultVo;
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode(ConstantCode.FAIL.getCode());
			resultVo.setMsg(ConstantCode.FAIL.getMsg());
			logger.error(ConstantCode.FAIL.printMsg() + "," + e.getMessage());
			return resultVo;
		}
	}
}
