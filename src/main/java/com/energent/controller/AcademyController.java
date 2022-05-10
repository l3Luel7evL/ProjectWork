package com.energent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.energent.entity.Academy;
import com.energent.service.AcademyService;

@Controller
public class AcademyController {
	
	@Autowired
	private AcademyService academyService;
	
	@GetMapping("/home")
	public ModelAndView home() {return new ModelAndView("academy", "academy", new Academy());}
	
	@GetMapping("/academies")
	public ModelAndView showAllAcademies() {
		return new ModelAndView("academies", "academies", academyService.findAllAcademies());		
	}
	
	@PostMapping("/academies")
	public String addOrUpdateAcademy(@ModelAttribute("academy") Academy academy) throws Exception {			
		academyService.addOrUpdateAcademy(academy);

		return "redirect:/academies";
	}
	
	@GetMapping("/academies/update/{code}")
	public ModelAndView showUpdatePage(@PathVariable String code) {
		return new ModelAndView("academy", "academy", academyService.findAcademyById(code));
	}
	
	@GetMapping("/academies/remove/{code}")
	public String removeAcademy(@PathVariable String code) {
		academyService.removeAcademy(code);
		
		return "redirect:/academies";
	}
}
