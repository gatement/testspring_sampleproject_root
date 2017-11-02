package lgh.lib.data.jdbc.template.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class BaseDao<T> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	private Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
			.getActualTypeArguments()[0];

	private RowMapper<T> rowMapper = new BeanPropertyRowMapper<T>(entityClass);

	public List<T> findAll(String sql) {
		return jdbcTemplate.query(sql, rowMapper);
	}

	public List<T> findAll(String sql, Object... args) {
		return jdbcTemplate.query(sql, args, rowMapper);
	}

	public T findOne(String sql, Object... args) {
		return jdbcTemplate.queryForObject(sql, args, rowMapper);
	}

	public int update(String sql) {
		return jdbcTemplate.update(sql);
	}

	public int update(String sql, Object... args) {
		return jdbcTemplate.update(sql, args);
	}
}