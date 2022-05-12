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
		
		for(Academy a : academyService.findAllAcademies())
			if(a.getStudents().contains(student) || (!student.getAcademies().contains(a)))
				studentService.deleteStudentByFiscalCode(student.getFiscalCode(), a.getCode());
		
		for(Academy a : academies)
			studentService.insertJoin(a.getCode(), student.getFiscalCode());
		
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