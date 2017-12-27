package com.offcn.mappers;

import com.offcn.domain.Student;

public interface PhoneMapper {
	
	Student queryPhoneLocation(String phoneNumber);
}
