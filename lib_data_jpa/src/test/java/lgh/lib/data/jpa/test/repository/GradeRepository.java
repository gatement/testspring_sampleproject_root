package lgh.lib.data.jpa.test.repository;

import java.util.List;

import lgh.lib.data.jpa.repository.BaseRepository;
import lgh.lib.data.jpa.test.entity.Grade;

public interface GradeRepository extends BaseRepository<Grade, Long> {
	List<Grade> findByName(String name);
}
