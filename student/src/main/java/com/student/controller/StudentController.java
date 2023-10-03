package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.student.entity.Student;
import com.student.service.StudentService;
import com.student.service.StudentServiceImp;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		List<Student> students =studentService.allStudents();
		mv.addObject("students", students);
		return mv;
	}
	@GetMapping("/addStudent")
	public ModelAndView addStudent() {
		ModelAndView mv = new ModelAndView("addStudent");
		return mv;
		
	}
	@PostMapping("/add")
	public ModelAndView add(Student student){
		
		ModelAndView mv = new ModelAndView("addMassage");
		String add=studentService.add(student);
		mv.addObject("result", add);
		return mv;
		
	}
	@GetMapping("/RollNumberToUpdate")
	public ModelAndView selectRollNumber() {
		ModelAndView mv = new ModelAndView("rollNumberToUpdate");
		return mv;
	}
	@GetMapping("/updateStudent")
	public ModelAndView updateStudent(@RequestParam Integer rollNumber) {
		ModelAndView mv = new ModelAndView();
		Student student=studentService.findStudent(rollNumber);
		if(student.getRollNumber()!=null) {
		mv.addObject("student",student);
		mv.setViewName("updatePage");
		return mv;
		}else {
			mv.setViewName("invalid");
			return mv;
		}
		
		
	}
	@PostMapping("/update")
	public ModelAndView update(Student student) {
		ModelAndView mv = new ModelAndView("updateMessage");
		String update=studentService.update(student);
		mv.addObject("updateMassage",update);
		return mv;
		
	}
	@GetMapping("/deleteStudent")
	public ModelAndView deleteStudent() {
		ModelAndView mv = new ModelAndView("deletePage");
		return mv;
	}
	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam Integer rollNumber,@RequestParam String email,@RequestParam String password) {
		ModelAndView mv = new ModelAndView("deleteMassage");
		String result=(studentService.delete(rollNumber,email,password));
		mv.addObject("result", result);
		return mv;
	}
	@ExceptionHandler(value=Exception.class)
	public ModelAndView notValidException() {
		ModelAndView mv = new ModelAndView("invalid");
		return mv;
		}
	//@GetMapping("studentDetails")
	

}
