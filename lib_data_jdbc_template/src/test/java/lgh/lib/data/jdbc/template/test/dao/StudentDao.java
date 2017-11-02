package lgh.lib.data.jdbc.template.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import lgh.lib.data.jdbc.template.dao.BaseDao;
import lgh.lib.data.jdbc.template.test.entity.Student;

@Repository
public class StudentDao extends BaseDao<Student> {
	public void create(String name, int age) {
		String sql = "insert into student(name, age) values(?, ?)";
		update(sql, name, age);
	}

	public void delete(int id) {
		String sql = "delete from student where id=?";
		update(sql, id);
	}

	public void updateName(int id, String name) {
		String sql = "update student set name = ? where id = ?";
		update(sql, name, id);
	}
	
	public List<Student> findAll() {
		String sql = "select id, name, age from student";
		return findAll(sql);
	}

	public List<Student> findAllByName(String name) {
		String sql = "select id, name, age from student where name = ?";
		return findAll(sql, name);
	}
}