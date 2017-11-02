package lgh.lib.data.jdbc.template.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lgh.lib.data.jdbc.template.test.entity.Student;
import lgh.lib.data.jdbc.template.test.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class AppTest {
	@Autowired
	private StudentService studentService;

	@Test
	public void testStudentService() {
		// insert 
		studentService.create("Johnson", 22);
		studentService.create("Anne", 20);
		
		// select all
		List<Student> students = studentService.findAll();
		assertEquals(2, students.size());
		assertEquals("Johnson", students.get(0).getName());
		assertEquals(22, students.get(0).getAge().intValue());
		
		// update
		int id = students.get(0).getId();
		studentService.updateName(id, "Johnson2");
		List<Student> students2 = studentService.findAllByName("Johnson2");
		assertEquals(1, students2.size());
		assertEquals("Johnson2", students2.get(0).getName());
		assertEquals(22, students2.get(0).getAge().intValue());
		
		// delete
		studentService.delete(id);
		List<Student> students3 = studentService.findAllByName("Johnson2");
		assertEquals(0, students3.size());
	}
}