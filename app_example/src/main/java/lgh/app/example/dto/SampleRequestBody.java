package lgh.app.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SampleRequestBody {
	private String name;
	private Integer age;
}
