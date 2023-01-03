package com.example.Reddis.Crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Reddis.Crud.Repository.ReddisCrudRepository;
import com.example.Reddis.Crud.model.Student;

@RestController
public class ReddisCrudController {

	@Autowired
	private ReddisCrudRepository repository;

	@RequestMapping("/saveStudent")
	@CachePut(key = "#student.id", value = "Student")
	public Student saveStudent(@RequestBody Student student) {
		return repository.saveStudent(student);
	}

	@RequestMapping("/getStudent/{id}")
	@Cacheable(key = "#id", value = "Student",unless="#Student == null")
	public Student findStudentById(@PathVariable int id) {
		Student student = repository.findStudentById(id);
		System.out.println(""+student);
		return student;
	}

	@RequestMapping("/findAllStudents")
	public List<Object> findAllStudents() {
		return repository.findAllStudents();
	}

	@RequestMapping("/deleteStudent/{id}")
	@CacheEvict(key = "#id", value = "Student")
	public String deleteStudent(@PathVariable int id) {
		return repository.deleteStudent(id);
	}

	@RequestMapping("/updateStudent")
	@CachePut(key = "#student.id", value = "Student")
	public Student updateStudent(@RequestBody Student student) {
		return repository.updateStudent(student);
	}

}
