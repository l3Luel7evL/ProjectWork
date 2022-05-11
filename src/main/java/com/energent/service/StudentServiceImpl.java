package com.energent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.energent.entity.*;
import com.energent.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AcademyService academyService;
	
	@Override
	public Student findStudentById(String id) {return studentRepository.findById(id).get();}

	@Override
	public void addOrUpdateStudent(Student student) {
		String id = student.getFiscalCode();
		Student studentToUpdate = null;	
		
		if(!studentRepository.existsById(id))
			studentRepository.save(student);
		
		else {
			studentToUpdate = findStudentById(id);
			
			studentToUpdate.setFirstName(student.getFirstName());
			studentToUpdate.setLastName(student.getLastName());
			studentToUpdate.setAge(student.getAge());
			studentToUpdate.getAcademies().addAll(student.getAcademies());
			
			studentRepository.save(studentToUpdate);
		}
	}

	@Override
	public void removeStudent(String id, String academyCode) {
		Academy academy = academyService.findAcademyById(academyCode);
		Student student = findStudentById(id);
		
		List<Student> students = academy.getStudents();
		List<Academy> academies = student.getAcademies();
		
		if(student.getAcademies()!= null && academy.getStudents()!= null) {
			academies.remove(academy);
			students.remove(student);
			
			academy.getStudents().clear();
			student.getAcademies().clear();
		}
		
		studentRepository.deleteById(id);
		
		academy.getStudents().addAll(students);
		student.getAcademies().addAll(academies);
	}

	@Override
	public List<Student> findAllStudents() {return studentRepository.findAll();}

	@Override
	public List<Student> findStudentsByAcademies(List<Academy> academies) {
		return studentRepository.findAllStudentsByAcademiesIn(academies);
	}
	
	@Override
	public void relateStudentAcademy(Academy academy, Student student){
		academyService.addOrUpdateAcademy(academy);
		addOrUpdateStudent(student);
		
	}

	@Override
	public void insertJoin(String academy_id, String student_id) {
		Student student = findStudentById(student_id);
		Academy academy = academyService.findAcademyById(academy_id);
		
		if(student.getAcademies().contains(academy))
			student.getAcademies().remove(academy);
			
		studentRepository.insertJoin(academy_id, student_id);
	}
}