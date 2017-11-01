package lgh.lib.data.jdbc.template.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lgh.lib.data.jdbc.template.test.dao.StudentDao;
import lgh.lib.data.jdbc.template.test.entity.Student;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;

	public Student findOneByName(String name) {
		return studentDao.findOneByName(name);
	}
}
