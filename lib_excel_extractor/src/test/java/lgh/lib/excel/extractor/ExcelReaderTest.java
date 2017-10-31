package lgh.lib.excel.extractor;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;

public class ExcelReaderTest {
	@Test
	public void testReadExcelByPath() {
		String path = ExcelReaderTest.class.getResource("/student.xls").toExternalForm().replace("file:", "");
		List<Student> students = ExcelReader.getInstance().readExcelAsBean(path, Student.class);

		assertEquals("The length of student list should match.", 4, students.size());

		Student firstStudent = students.get(0);
		assertEquals("The first student name should match.", "张三丰", firstStudent.getName());
		assertEquals("The first student age should match.", 92, firstStudent.getAge().intValue());
	}

	@Test
	public void testReadExcelByStream() {
		InputStream stream = ExcelReaderTest.class.getResourceAsStream("/student.xlsx");
		List<Student> students = ExcelReader.getInstance().readExcelAsBean(stream, Student.class);

		assertEquals("The length of student list should match.", 4, students.size());

		Student firstStudent = students.get(0);
		assertEquals("The first student name should match.", "张三丰", firstStudent.getName());
		assertEquals("The first student age should match.", 92, firstStudent.getAge().intValue());
	}
}