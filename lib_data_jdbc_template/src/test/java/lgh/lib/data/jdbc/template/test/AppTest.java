package lgh.lib.data.jdbc.template.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lgh.lib.data.jdbc.template.test.app.TestApp;
import lgh.lib.data.jdbc.template.test.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApp.class)
@EnableAutoConfiguration
public class AppTest {
	//@Autowired
	//private StudentService studentService;

	@Test
	public void testStudentService() throws Exception {
		// insert data
		//studentService.
	}
}