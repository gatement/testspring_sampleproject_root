package lgh.lib.data.mybatis.test.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import lgh.lib.data.mybatis.test.dto.Student;

@Mapper
public interface StudentMapper {
	@Insert("insert into student(name, age) values(#{name}, #{age})")
	void insert(@Param("name") String name, @Param("age") int age);

	@Select("select * from student where name = #{name}")
	Student findByName(@Param("name") String name);
}
