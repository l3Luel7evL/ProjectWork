package com.energent.service;

import java.util.List;

import com.energent.entity.Academy;

public interface AcademyService {

	Academy findAcademyById(String id);
	void addOrUpdateAcademy(Academy academy);
	void removeAcademy(String id);
	List<Academy> findAllAcademies();
	
}
