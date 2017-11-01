package lgh.lib.data.jpa.test.repository;

import java.util.List;

import lgh.lib.data.jpa.repository.BaseRepository;
import lgh.lib.data.jpa.test.entity.Student;

public interface StudentRepository extends BaseRepository<Student, Long> {
	List<Student> findByFirstName(String firstName);
}
