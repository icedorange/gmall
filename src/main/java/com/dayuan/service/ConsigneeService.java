package com.dayuan.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dayuan.bean.Consignee;
import com.dayuan.bean.UserCart;
import com.dayuan.constant.ConstantCode;
import com.dayuan.dao.ConsigneeMapper;
import com.dayuan.dao.UserCartMapper;
import com.dayuan.exception.ParamException;

@Service
@Transactional
public class ConsigneeService {
	@Resource
	private ConsigneeMapper consigneeMapper;
	@Resource
	private UserCartMapper userCartMapper;

	// 查询用户收货人地址
	public List<Consignee> selectConsignee(Long uid) throws Exception {
		UserCart userCart = userCartMapper.selectUserCart(uid);
		if (userCart == null) {
			throw new ParamException(ConstantCode.PARAM_EMPTY.getMsg());
		}
		Consignee consignee = new Consignee();
		consignee.setUid(uid);
		List<Consignee> consigneeList = consigneeMapper.selectConsignee(consignee);
		return consigneeList;
	}

	// 增加用户收货人地址
	public void addConsignee(Long uid, Consignee consignee) throws Exception {
		// 验证用户
		UserCart userCart = userCartMapper.selectUserCart(uid);
		if (userCart == null) {
			throw new ParamException(ConstantCode.PARAM_EMPTY.getMsg());
		}
		consignee.setUid(uid);
		consigneeMapper.insertConsignee(consignee);
	}

	// 删除用户收货人地址
	public void deleteConsignee(Long uid, Consignee consignee) throws Exception {
		// 验证用户
		UserCart userCart = userCartMapper.selectUserCart(uid);
		List<Consignee> consigneeList = consigneeMapper.selectConsignee(consignee);
		if (userCart == null || consigneeList == null) {
			throw new ParamException(ConstantCode.PARAM_EMPTY.getMsg());
		}
		consignee.setUid(uid);
		consigneeMapper.deleteConsignee(consignee);

	}

	// 修改用户收货人地址
	public void updateConsignee(Long uid, Consignee consignee) throws Exception {
		// 验证用户
		// 默认状态修改
		UserCart userCart = userCartMapper.selectUserCart(uid);
		List<Consignee> consigneeList = consigneeMapper.selectConsignee(consignee);
		if (userCart == null || consigneeList == null) {
			throw new ParamException(ConstantCode.PARAM_EMPTY.getMsg());
		}
		consignee.setUid(uid);
		consigneeMapper.updateConsignee(consignee);
	}
}
