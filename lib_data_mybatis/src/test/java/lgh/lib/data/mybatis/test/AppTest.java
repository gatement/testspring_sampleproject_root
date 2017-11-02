package lgh.lib.data.mybatis.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lgh.lib.data.mybatis.test.dto.Student;
import lgh.lib.data.mybatis.test.mapper.StudentMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class AppTest {
	@Autowired
	private StudentMapper studentMapper;

	@Test
	public void testStudentMapper() {
		// insert
		studentMapper.insert("Johnson", 22);

		// select
		Student student = studentMapper.findByName("Johnson");
		assertNotNull(student);
		assertEquals("Johnson", student.getName());
		assertEquals(22, student.getAge().intValue());
	}
}