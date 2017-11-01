package lgh.lib.data.jdbc.template.test.dao;

import org.springframework.stereotype.Repository;

import lgh.lib.data.jdbc.template.dao.BaseDao;
import lgh.lib.data.jdbc.template.test.entity.Student;

@Repository
public class StudentDao extends BaseDao<Student> {

	public Student findOneByName(String name) {
		String sql = "select id, name from student where name=?";
		return findOne(sql, name);
	}
}