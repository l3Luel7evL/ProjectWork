package com.energent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.energent.entity.Academy;
import com.energent.entity.Student;
import com.energent.repository.AcademyRepository;
import com.energent.repository.StudentRepository;

@Service
public class AcademyServiceImpl implements AcademyService{

	@Autowired
	private AcademyRepository academyRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Academy findAcademyById(String id) {return academyRepository.findById(id).get();}

	@Override
	public void addOrUpdateAcademy(Academy academy) throws Exception{
		
		String id = academy.getCode();
		Academy academyToUpdate = null;
		
		if(academy.getEndDate().toLocalDate().isBefore(academy.getStartDate().toLocalDate())) {
			throw new Exception("Errore, la data di fine non puo' essere prima di quella di partenza!");
		}
		
		if(!academyRepository.existsById(id))
			academyRepository.save(academy);
		
		else {
			academyToUpdate = findAcademyById(id);
			
			academyToUpdate.setTitle(academy.getTitle());
			academyToUpdate.setLocation(academy.getLocation());
			academyToUpdate.setStartDate(academy.getStartDate());
			academyToUpdate.setEndDate(academy.getEndDate());
			academyToUpdate.getStudents().addAll(academy.getStudents());

			academyRepository.save(academyToUpdate);
		}		
	}

	@Override
	public void removeAcademy(String id) {
		Academy academy = findAcademyById(id);

		List<Student> Allstudents = studentRepository.findAll();
		
		for(Student st : Allstudents)
			if(st.getAcademies().contains(academy)&& st.getAcademies() != null) {
				st.getAcademies().remove(academy);
				
				if(academy.getStudents().contains(st))
					academy.getStudents().remove(st);
			}
		
		academyRepository.deleteById(id);	
	}

	@Override
	public List<Academy> findAllAcademies() {return academyRepository.findAll();}
}