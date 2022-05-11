package com.energent.service;

import java.util.List;

import com.energent.entity.*;

public interface StudentService {

	Student findStudentById(String id);
	void addOrUpdateStudent(Student student) throws Exception;
	void removeStudent(String id, String academyCode);
	List<Student> findAllStudents();
	List<Student> findStudentsByAcademies(List<Academy> academies);
	void relateStudentAcademy(Academy academy, Student student) throws Exception;
	void insertJoin(String academy_id, String student_id) ;
	int ageCalculator(Student student);
	void deleteStudentByFiscalCode(String fiscalCode, String urlCode);
	
}