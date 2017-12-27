package com.offcn.mappers;

public interface IPMapper {
	
	Integer findIPAccessCount(String ipAddress);

	void insert(String ipAddress);
}
