package lgh.lib.data.jpa.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "busi_student")
@Data
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "st_first_name")
	private String firstName;

	@Column(name = "st_last_name")
	private String lastName;

	private Integer age;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grade_id")
	private Grade grade;

	public Student() {
	}

	public Student(String firstName, String lastName, Integer age, Grade grade) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.grade = grade;
	}
}
