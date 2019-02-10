package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	@RequestMapping(path = "/get/all")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
}
