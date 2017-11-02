package lgh.lib.data.jdbc.template.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lgh.lib.data.jdbc.template.test.dao.StudentDao;
import lgh.lib.data.jdbc.template.test.entity.Student;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;

	public void create(String name, int age) {
		studentDao.create(name, age);
	}

	public void delete(int id) {
		studentDao.delete(id);
	}

	public void updateName(int id, String name) {
		studentDao.updateName(id, name);
	}
	
	public List<Student> findAll() {
		return studentDao.findAll();
	}

	public List<Student> findAllByName(String name) {
		return studentDao.findAllByName(name);
	}
}
