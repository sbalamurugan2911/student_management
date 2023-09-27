package com.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.student.dao.StudentRepository;
import com.student.entity.Student;


public interface StudentService {
	
	
	String add(Student student);
	String update(Student student);
	Student findStudent(Integer rollNumber);
	String delete(Integer rollNumber, String email, String password);
	List<Student> allStudents();

}
