package com.energent.service;

import java.time.LocalDate;
import java.time.Period;
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
	public void addOrUpdateStudent(Student student) throws Exception {
		String id = student.getFiscalCode();
		Student studentToUpdate = null;	
		
		if (!student.getFiscalCode().toUpperCase().matches("^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$")) {
			throw new Exception("Verificare di aver digitato il codice fiscale correttamente");
		}

		if (!student.getFirstName().matches("[a-zA-Z]+")) {
			throw new Exception("Per favore inserire un vero nome");
		}

		if (!student.getLastName().matches("[a-zA-Z]+")) {
			throw new Exception("Per favore inserire un vero cognome");
		}

		if (ageCalculator(student) < 18) {
			throw new Exception("Età inferiore ai 18 anni, impossibile completare la registrazione!");
		}
		if(!studentRepository.existsById(id))
			studentRepository.save(student);
		
		else {
			studentToUpdate = findStudentById(id);
			
			studentToUpdate.setFirstName(student.getFirstName());
			studentToUpdate.setLastName(student.getLastName());
			studentToUpdate.setBirthDate(student.getBirthDate());
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
	public void relateStudentAcademy(Academy academy, Student student) throws Exception{
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
	
	@Override
	public int ageCalculator(Student student) {
		LocalDate birthDate = student.getBirthDate().toLocalDate();
		LocalDate currentDate = LocalDate.now();
		return Period.between(birthDate, currentDate).getYears();
	}
}