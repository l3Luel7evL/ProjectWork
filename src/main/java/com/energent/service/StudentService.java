package com.energent.service;

import java.util.List;

import com.energent.entity.*;

public interface StudentService {

	Student findStudentById(String id);
	void addOrUpdateStudent(Student student);
	void removeStudent(String id, String academyCode);
	List<Student> findAllStudents();
	List<Student> findStudentsByAcademies(List<Academy> academies);
	void relateStudentAcademy(Academy academy, Student student);
	void insertJoin(String academy_id, String student_id) ;
	
}