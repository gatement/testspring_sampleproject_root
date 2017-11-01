package lgh.lib.data.jdbc.template.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lgh.lib.data.jdbc.template.test.app.TestApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApp.class)
@EnableAutoConfiguration
public class AppTest {
	@Test
	public void testStudentService() {
	}
}