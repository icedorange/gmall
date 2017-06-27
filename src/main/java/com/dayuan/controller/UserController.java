package com.dayuan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuan.bean.User;
import com.dayuan.constant.ConstantCode;
import com.dayuan.service.UserService;
import com.dayuan.utils.CreateCode;
import com.dayuan.utils.ParamException;
import com.dayuan.utils.PatternUtils;
import com.dayuan.vo.ResultVo;

@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UserService userService;

	/**
	 * 登出
	 */
	@RequestMapping(value = "/logout.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo logout(HttpSession session, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			User user = (User) session.getAttribute("user");
			if (user == null) {

			} else {
				session.removeAttribute("user");
			}
			resultVo.setCode(ConstantCode.SUCCESS.getValue());
			return resultVo;
		} catch (Exception e) {
			resultVo = new ResultVo();
			resultVo.setCode(ConstantCode.FAIL.getValue());
			resultVo.setMsg("网络不稳定，请稍后再试");
			logger.error("用户注册失败：" + e.getMessage());
			return resultVo;
		}
	}

	/**
	 * 登录状态
	 * 
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/loginStatus.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo loginStatus(HttpSession session, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			User loginUser = (User) session.getAttribute("user");
			if (loginUser == null) {
				resultVo.setData(null);
			} else {
				resultVo.setData(loginUser);
			}
			resultVo.setCode(ConstantCode.SUCCESS.getValue());
			return resultVo;
		} catch (Exception e) {
			resultVo.setCode(ConstantCode.FAIL.getValue());
			resultVo.setMsg("网络不稳定，请稍后再试");
			logger.error("用户注册失败：" + e.getMessage());
			return resultVo;
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param session
	 * @param response
	 * @param loginName
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo login(HttpSession session, HttpServletResponse response,
			@RequestParam("loginName") String loginName, @RequestParam("password") String password) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			if (loginName == null || password == null || "".equals(loginName) || "".equals(password)) {
				throw new ParamException("参数错误");
			}
			// 判断密码长度
			if (!PatternUtils.isPassword(password)) {
				resultVo.setCode(ConstantCode.FAIL.getValue());
				resultVo.setMsg("密码长度6-20位");
				return resultVo;
			}
			User user = new User();
			if (PatternUtils.isMobile(loginName)) {
				// 登录名符合手机号格式
				user.setMobile(loginName);
			} else if (PatternUtils.isEmail(loginName)) {
				// 登录名符合邮箱格式
				user.setEmail(loginName);
			} else {
				user.setUsername(loginName);
			}
			// 数据库查询
			User loginUser = userService.selectUserByParam(user);
			if (loginUser == null) {
				resultVo.setCode(ConstantCode.FAIL.getValue());
				resultVo.setMsg("请注册后登录");
				return resultVo;
			}
			// 登录成功，对象放入session
			session.setAttribute("user", loginUser);
			resultVo.setCode(ConstantCode.SUCCESS.getValue());
			resultVo.setData(loginUser);
			resultVo.setMsg("登录成功");
			return resultVo;
		} catch (ParamException e) {
			resultVo.setCode(ConstantCode.FAIL.getValue());
			resultVo.setMsg(e.getMessage());
			return resultVo;
		} catch (Exception e) {
			resultVo.setCode(ConstantCode.FAIL.getValue());
			resultVo.setMsg("网络不稳定，请稍后再试");
			logger.error("用户登陆失败：" + e.getMessage());
			return resultVo;
		}

	}

	/**
	 * 邮箱注册
	 * 
	 * @param session
	 * @param response
	 * @param user
	 * @param passwordRepeat
	 * @return
	 */
	@RequestMapping(value = "/registerByEmail.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo registerByEmail(HttpSession session, HttpServletResponse response, User user,
			@RequestParam("passwordRepeat") String passwordRepeat) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			// 验证邮箱账号格式
			if (!PatternUtils.isEmail(user.getEmail())) {
				resultVo.setCode(ConstantCode.FAIL.getValue());
				resultVo.setMsg("请输入有效邮箱账号");
				return resultVo;
			}
			// 判断密码长度
			if (!PatternUtils.isPassword(user.getPassword()) || !PatternUtils.isPassword(passwordRepeat)) {
				resultVo.setCode(ConstantCode.FAIL.getValue());
				resultVo.setMsg("密码长度6-20位");
				return resultVo;
			}
			// 判断密码是否相等
			if (!user.getPassword().equals(passwordRepeat)) {
				resultVo.setCode(ConstantCode.FAIL.getValue());
				resultVo.setMsg("两次密码不相同，请重新输入");
				return resultVo;
			}
			// 查询数据库中是否存在
			if (userService.selectUserByParam(user) != null) {
				resultVo.setCode(ConstantCode.FAIL.getValue());
				resultVo.setMsg("此账号已被注册");
				return resultVo;
			}
			// 通过邮箱注册，数据库添加用户数据
			userService.addUserByEmail(user);
			resultVo.setCode(ConstantCode.SUCCESS.getValue());
			resultVo.setMsg("注册成功");
			return resultVo;
		} catch (Exception e) {
			resultVo.setCode(ConstantCode.FAIL.getValue());
			resultVo.setMsg("网络不稳定，请稍后再试");
			logger.error("用户注册失败：" + e.getMessage());
			return resultVo;
		}
	}

	/**
	 * 手机号注册
	 * 
	 * @param session
	 * @param response
	 * @param user
	 * @param code
	 * @param passwordRepeat
	 * @return
	 */
	@RequestMapping(value = "/registerByMobile.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo registerByMobile(HttpSession session, HttpServletResponse response, User user,
			@RequestParam("code") String code, @RequestParam("passwordRepeat") String passwordRepeat) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		try {
			resultVo = new ResultVo();
			// 验证码是否存在
			if (code == null || "".equals(code)) {
				throw new ParamException("参数错误");
			}
			// 验证手机号格式
			if (!PatternUtils.isMobile(user.getMobile())) {
				resultVo.setCode(ConstantCode.FAIL.getValue());
				resultVo.setMsg("请输入有效手机号码");
				return resultVo;
			}
			// 判断密码长度
			if (!PatternUtils.isPassword(user.getPassword()) || !PatternUtils.isPassword(passwordRepeat)) {
				resultVo.setCode(ConstantCode.FAIL.getValue());
				resultVo.setMsg("密码长度6-20位");
				return resultVo;
			}
			// 判断密码是否相等
			if (!user.getPassword().equals(passwordRepeat)) {
				resultVo = new ResultVo();
				resultVo.setCode(ConstantCode.FAIL.getValue());
				resultVo.setMsg("两次密码不相同，请重新输入");
				return resultVo;
			}

			// session中code是否存在
			@SuppressWarnings("rawtypes")
			Map codeMap = (Map) session.getAttribute("code");
			if (!codeMap.get("mobile").equals(user.getMobile())) {
				resultVo = new ResultVo();
				resultVo.setCode(ConstantCode.CODE_ERROR.getValue());
				resultVo.setMsg("验证码错误,请重新获取");
				return resultVo;
			}
			long mistiming = System.currentTimeMillis() - (long) codeMap.get("codeTime");
			if ((mistiming / 1000) > 120) {
				resultVo.setCode(ConstantCode.FAIL.getValue());
				resultVo.setMsg("验证码超时,请重新获取");
				session.removeAttribute("code");
				return resultVo;
			}

			// 查询数据库中是否存在
			if (userService.selectUserByParam(user) != null) {
				resultVo.setCode(ConstantCode.FAIL.getValue());
				resultVo.setMsg("此手机号已被注册");
				return resultVo;
			}
			// 通过手机号注册，数据库添加用户数据
			userService.addUserByMobile(user);
			resultVo.setCode(ConstantCode.SUCCESS.getValue());
			resultVo.setMsg("注册成功");
			return resultVo;
		} catch (ParamException e) {
			resultVo = new ResultVo();
			resultVo.setCode(ConstantCode.FAIL.getValue());
			resultVo.setMsg(e.getMessage());
			return resultVo;
		} catch (Exception e) {
			resultVo = new ResultVo();
			resultVo.setCode(ConstantCode.FAIL.getValue());
			resultVo.setMsg("网络不稳定，请稍后再试");
			logger.error("用户注册失败：" + e.getMessage());
			return resultVo;
		}
	}

	/**
	 * 生成验证码
	 * 
	 * @param session
	 * @param response
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/createCode.shtml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultVo createCode(HttpSession session, HttpServletResponse response,
			@RequestParam("phone") String mobile) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultVo resultVo = null;
		Map<String, Object> codeMap = new HashMap<String, Object>();
		try {
			resultVo = new ResultVo();
			// 验证手机号格式
			if (!PatternUtils.isMobile(mobile)) {
				resultVo.setCode(ConstantCode.FAIL.getValue());
				resultVo.setMsg("请输入有效号码");
				return resultVo;
			}
			// 生成code
			int code = CreateCode.code();
			System.out.println(code);
			long codeTime = System.currentTimeMillis();
			// 手机号和生成时间存入Map
			codeMap.put("codeTime", codeTime);
			codeMap.put("mobile", mobile);
			session.setAttribute("code", codeMap);
			resultVo.setCode(ConstantCode.SUCCESS.getValue());
			resultVo.setMsg("短信发送成功");
			return resultVo;
		} catch (Exception e) {
			resultVo.setCode(ConstantCode.FAIL.getValue());
			resultVo.setMsg("网络不稳定，请稍后再试");
			logger.error("用户注册失败：" + e.getMessage());
			return resultVo;
		}
	}
}
