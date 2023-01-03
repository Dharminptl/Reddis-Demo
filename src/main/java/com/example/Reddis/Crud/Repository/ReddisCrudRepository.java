package com.example.Reddis.Crud.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.Reddis.Crud.model.Student;

@Repository
public class ReddisCrudRepository{

	@Autowired
	private RedisTemplate<String,Object> redisTemplate;

	private static final String key = "Student";

	public Student saveStudent(Student student) {
		redisTemplate.opsForHash().putIfAbsent(key, student.getId(), student);
		return student;
	}

	public Student findStudentById(int id) {
		System.out.println("call in get student");
		Student student = (Student) redisTemplate.opsForHash().get(key, id);
		return student;
	}

	public List<Object> findAllStudents() {
		System.out.println("call in get All student");
		return redisTemplate.opsForHash().values(key);
	}

	public String deleteStudent(int id) {
		System.out.println("call in delete student");
		redisTemplate.opsForHash().delete(key, id);
		return "Student Deleted....!!";
	}

	public Student updateStudent(Student student) {
		System.out.println("call in update student");
		redisTemplate.opsForHash().put(key, student.getId(), student);
		return student;
	}
}
