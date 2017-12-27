package com.offcn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offcn.domain.Student;
import com.offcn.mappers.PhoneMapper;
import com.offcn.service.PhoneService;

@Service("phoneService")
public class PhoneServiceImpl implements PhoneService {

	@Autowired
	private PhoneMapper phoneMapper;

	@Override
	public Student queryPhoneLocation(String phoneNumber) {
		
		return phoneMapper.queryPhoneLocation(phoneNumber.substring(0, 7));
	}

}
