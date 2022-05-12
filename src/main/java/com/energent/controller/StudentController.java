package com.energent.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.energent.entity.*;
import com.energent.service.*;

@Controller
@Transactional
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private AcademyService academyService;
		
	private String urlCode= null;
		
	@GetMapping("/students/{code}")
	public ModelAndView showAllStudentsByAcademy(@PathVariable String code) {
		
		List<Academy> academies = new ArrayList<>();
		
		ModelAndView mav = new ModelAndView("students");
		
		urlCode= code;
		academies.add(academyService.findAcademyById(code));
		
		//mav.addObject("urlCode", urlCode);
		mav.addObject("students", studentService.findStudentsByAcademies(academies));
		
		return mav;
	}
	
	@GetMapping("/student")
	public ModelAndView studentsForm() {return new ModelAndView("student", "student", new Student());}
	
	@PostMapping("/students")
	public String addOrUpdateStudent(@ModelAttribute("student") Student student) throws Exception {
		Academy academy = academyService.findAcademyById(urlCode);
		
		studentService.addOrUpdateStudent(student);
		academyService.addOrUpdateAcademy(academy);
		
		studentService.insertJoin(academy.getCode(), student.getFiscalCode());

		return "redirect:/academies";
	}
	
	@GetMapping("/students/update/{fiscalCode}")
	public ModelAndView showUpdatePage(@PathVariable String fiscalCode) {return new ModelAndView("student", "student", studentService.findStudentById(fiscalCode));}
	
	@GetMapping("/students/remove/{fiscalCode}")
	public String removeStudent(@PathVariable String fiscalCode) {
		studentService.deleteStudentByFiscalCode(fiscalCode, urlCode);
		
		return "redirect:/academies";
	}
	
	@GetMapping("/students/remove/{fiscalCode}/all")
	public String removeStudentAll(@PathVariable String fiscalCode) {
		studentService.removeStudent(fiscalCode, urlCode);
		
		return "redirect:/academies";
	}
	
	@GetMapping("/students/all")
	public ModelAndView showAllStudents() {return new ModelAndView("studentsAll", "students", studentService.findAllStudents());}
	
	@PostMapping("/relate/students")
	public String relateStudent(@ModelAttribute("student") Student student) throws Exception{
		List<Academy> academies = student.getAcademies();
		
//		student.getAcademies().clear();
		//student.getAcademies().addAll(academies);
		
		for (Academy a : student.getAcademies()) {
//			a.getStudents().remove(student);
//			student.getAcademies().remove(a);
			a.getStudents().add(student);
			academyService.addOrUpdateAcademy(a);
			
			
		}
//		student.getAcademies().addAll(academies);
		studentService.addOrUpdateStudent(student);
		
			
		//student.getAcademies().addAll(academies);
		for(Academy a : academyService.findAllAcademies())
			if(!student.getAcademies().contains(a))
				studentService.deleteStudentByFiscalCode(student.getFiscalCode(), a.getCode());
		
		student.getAcademies().clear();
		for(Academy a : academies)
			studentService.insertJoin(a.getCode(), student.getFiscalCode());
		/*List<Academy> academies = student.getAcademies();
		
		student.getAcademies().clear();
		
		for(Academy a : academyService.findAllAcademies())
			if(!academies.contains(a))
				studentService.deleteStudentByFiscalCode(student.getFiscalCode(), a.getCode());
		
		student.getAcademies().addAll(academies);
		studentService.addOrUpdateStudent(student);
		
		for(Academy a : student.getAcademies()) 
			academyService.addOrUpdateAcademy(a);
			
		for(Academy a : academyService.findAllAcademies())
			if(academies.contains(a))
				studentService.insertJoin(a.getCode(), student.getFiscalCode());*/
		
//		student.getAcademies().clear();
//		studentService.addOrUpdateStudent(student);
//		
////		for(Academy a : academies) {
////			a.getStudents().remove(student);
////			academyService.addOrUpdateAcademy(a);
////		}
//		
//		for(Academy a : academyService.findAllAcademies()) {
//			if(!academies.contains(a)) {
//				studentService.deleteStudentByFiscalCode(student.getFiscalCode(), a.getCode());
//				academyService.addOrUpdateAcademy(a);
//			}
//		}
//		student.getAcademies().addAll(academies);
//		studentService.addOrUpdateStudent(student);
//		
//		for(Academy a : academies) {
//			studentService.insertJoin(a.getCode(), student.getFiscalCode());
//			academyService.addOrUpdateAcademy(a);
//			
//		}
		
		
////		
////		for(Academy a : academies) {
////			a.getStudents().add(student);
////			academyService.addOrUpdateAcademy(a);
////			
////		}
		
//		
		
//		}
		
		//for(Academy a : academies)
		
		
		return "redirect:/academies";
	}
	
	@GetMapping("/relate")
	public ModelAndView relatePage() {
		ModelAndView mav = new ModelAndView("academyStudent");
		
		mav.addObject("student", new Student());
		mav.addObject("academies", academyService.findAllAcademies());
		
		return mav;
	}
}

/*	//List<Student> students = new ArrayList<>();
List<Academy> academies = new ArrayList<>();

//students.add(student);
academies.addAll(student.getAcademies());

/*
student.getAcademies().clear();
for(Academy a : academies) {
	studentService.deleteStudentByFiscalCode(a.getCode(), student.getFiscalCode());
	a.getStudents().clear();
}


student.getAcademies().addAll(academies);

for(Academy a : academies) {
	a.getStudents().addAll(students);
	academyService.addOrUpdateAcademy(a);
	
}
studentService.addOrUpdateStudent(student);
//student.getAcademies().clear();
for(Academy a : academies) {
	a.getStudents().clear();
	studentService.deleteStudentByFiscalCode(a.getCode(), student.getFiscalCode());
}

//student.getAcademies().clear();
student.getAcademies().addAll(academies);
for(Academy a : academies) {
	//studentService.deleteStudentByFiscalCode(a.getCode(), student.getFiscalCode());
	a.getStudents().add(student);
	academyService.addOrUpdateAcademy(a);
	
}
studentService.addOrUpdateStudent(student);*/