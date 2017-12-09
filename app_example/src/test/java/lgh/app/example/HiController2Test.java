package lgh.app.example;

import static org.junit.Assert.assertThat;

import java.net.URL;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import lgh.app.example.dto.SampleRequestBody;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HiController2Test {

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate template;

	private final ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/hi/test");
	}

	@Test
	public void getHello() throws Exception {
		String content = mapper.writeValueAsString(new SampleRequestBody("Johnson", 20));
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
		headers.set(HttpHeaders.AUTHORIZATION, "123456");
		HttpEntity<String> request = new HttpEntity<>(content, headers);
		ResponseEntity<String> response = template.postForEntity(base.toString(), request, String.class);
		assertThat(response.getBody(), Matchers.containsString("Johnson(123456)"));
	}
}