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
import lgh.lib.data.jpa.test.entity.Grade;
import lgh.lib.data.jpa.test.entity.Student;
import lgh.lib.data.jpa.test.repository.GradeRepository;
import lgh.lib.data.jpa.test.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApp.class)
@EnableAutoConfiguration
public class AppTest {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private GradeRepository gradeRepository;

	@Test
	public void testStudentRepository() {
		// insert data
		gradeRepository.save(new Grade("Grade 1"));
		gradeRepository.save(new Grade("Grade 2"));
		gradeRepository.save(new Grade("Grade 3"));
		Grade grade1 = gradeRepository.findOne((root, query, cb) -> cb.equal(root.get("name"), "Grade 1"));

		studentRepository.save(new Student("Johnson", "Lau", 22, grade1));
		studentRepository.save(new Student("Johnson", "Liu", 23, grade1));
		studentRepository.save(new Student("Anne", "Zhang", 21, grade1));

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