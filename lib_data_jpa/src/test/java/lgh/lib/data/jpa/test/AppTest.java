package lgh.lib.data.jpa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lgh.lib.data.jpa.test.app.TestApp;
import lgh.lib.data.jpa.test.entity.Student;
import lgh.lib.data.jpa.test.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApp.class)
@EnableAutoConfiguration
public class AppTest {
	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void testStudentRepository() {
		// insert data
		studentRepository.save(new Student("Johnson", "Lau", 22));
		studentRepository.save(new Student("Johnson", "Liu", 23));
		studentRepository.save(new Student("Anne", "Zhang", 21));

		// select all test
		List<Student> allStudents = studentRepository.findAll();
		assertEquals("The student count shoud be match", 3, allStudents.size());

		// select some test
		List<Student> johnsons = studentRepository.findByFirstName("Johnson");
		assertEquals("The student count shoud be match", 2, johnsons.size());
		assertEquals("Johnson", johnsons.get(0).getFirstName());
		assertEquals("Johnson", johnsons.get(1).getFirstName());

		// select one test
		Student student = studentRepository
				.findOne((root, query, cb) -> cb.and(cb.equal(root.get("firstName"), "Johnson"),
						cb.equal(root.get("lastName"), "Lau"), cb.lessThan(root.get("age"), 30)));
		assertNotNull(student);
		assertEquals("Johnson", student.getFirstName());
		assertEquals("Lau", student.getLastName());
		assertEquals(22, student.getAge().intValue());
	}
}
