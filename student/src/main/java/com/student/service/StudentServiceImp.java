package com.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.student.dao.StudentRepository;
import com.student.entity.Student;
@Service
public class StudentServiceImp implements StudentService{
	 @Autowired
	 StudentRepository studentRep;
	 
	 @Override
		public List<Student> allStudents() {
			List<Student> students=studentRep.findAll();
			return students;
		} 

	@Override
	public String add(Student student) {
		Integer id=student.getRollNumber();
		Optional<Student> op=studentRep.findById(id);
		
		if (op.isEmpty()) {
	    studentRep.save(student);
		return "student added successfully.";
		}
		
		
		else {
			return "student added failed!  The roll number already exist.";
		}
		
		
	}
	public Student findStudent(Integer rollNumber) {
		
		Optional <Student> op=studentRep.findById(rollNumber);
		if(!op.isEmpty()) {
			return  op.get();
		}else {
		return new Student();
		}
	}

	@Override
	public String update(Student student) {
		Integer id = student.getRollNumber();
		Optional<Student> op=studentRep.findById(id);
		if(!op.isEmpty()) {
			Student s=op.get();
			s.setName(student.getName());
			s.setStandard(student.getStandard());
			s.setAge(student.getAge());
			s.setEmail(student.getEmail());
			s.setPassword(student.getPassword());
			studentRep.save(s);
			return "student details updated successfully.";
		}else 
			{
				return "update failed!   given roll number not exist.";
		}
	}
	@Override
	public String delete(Integer rollNumber, String email, String password) {
		Optional<Student> op=studentRep.findById(rollNumber);
		if(!op.isEmpty()) {
			Student s=op.get();
			if(s.getEmail().equals(email) && s.getPassword().equals(password)) {
				studentRep.delete(s);
				return "student deleted successfully.";
			}else {
				return "student email or password incorrect.";
			}
		}else {
		     return "given roll number not exist.";
		}
	}
	

}
	
