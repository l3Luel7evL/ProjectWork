package com.energent.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Academy {

	@Id
	private String code;
	
	@Column(length = 75, nullable = false)
	private String title, location;
	
	@Column(nullable = false)
	private Date startDate, endDate;
	
	@ManyToMany(mappedBy = "academies", fetch = FetchType.EAGER)
	private List<Student> students = new ArrayList<>();
	
	public Academy() {}
	public Academy(String code, String title, String location, Date startDate, Date endDate) {
		this.code = code;
		this.title = title;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		
	}
	
	public String getCode() {return code;}
	public void setCode(String code) {
		this.code = code;
		
	}
	public String getTitle() {return title;}
	public void setTitle(String title) {
		this.title = title;
		
	}
	public String getLocation() {return location;}
	public void setLocation(String location) {
		this.location = location;
		
	}
	public Date getStartDate() {return startDate;}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		
	}
	public Date getEndDate() {return endDate;}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		
	}
	public List<Student> getStudents() {return students;}
	public void setStudents(List<Student> students) {
		this.students = students;
		
	}
	
	@Override
	public String toString() {
		return "Academy [code=" + code + ", title=" + title + ", location=" + location + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", students=" + students + "]";
	}
	
	
}