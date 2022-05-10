package com.energent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.energent.entity.Academy;
import com.energent.entity.Student;

public interface StudentRepository extends JpaRepository<Student, String>{

	List<Student> findAllStudentsByAcademiesIn(List<Academy> academies);
	
	@Modifying
	@Query(value = "INSERT INTO academy_student (academy_code, student_fiscal_code) VALUES (?1, ?2)", nativeQuery = true)
	void insertJoin(String academyCode, String studentFiscalCode);
	
}
