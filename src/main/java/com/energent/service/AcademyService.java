package com.energent.service;

import java.util.List;

import com.energent.entity.Academy;

public interface AcademyService {
	
	//Read
	Academy findAcademyById(String id);
	List<Academy> findAllAcademies();
	
	//Other CRUDs
	void addOrUpdateAcademy(Academy academy) throws Exception;
	void removeAcademy(String id);
	
}