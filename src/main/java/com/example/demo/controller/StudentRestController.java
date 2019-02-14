package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;

@RestController
@RequestMapping(path = "/student")
public class StudentRestController {

	@Autowired
	private StudentRepository studentRepository;
	
	@RequestMapping(path = "/get/all")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
}
