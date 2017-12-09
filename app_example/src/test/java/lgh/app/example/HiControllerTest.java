package lgh.app.example;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import lgh.app.example.dto.SampleRequestBody;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HiControllerTest {

	@Autowired
	private MockMvc mvc;

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void test1() throws Exception {
		String content = mapper.writeValueAsString(new SampleRequestBody("Johnson", 20));
		mvc.perform(MockMvcRequestBuilders.post("/hi/test").content(content).header("Authorization", "123456")
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(content().string(Matchers.containsString("Johnson(123456)")));
	}

	@Test
	public void testJsonParseException() throws Exception {
		String content = "abc";
		mvc.perform(MockMvcRequestBuilders.post("/hi/test").content(content).header("Authorization", "123456")
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is5xxServerError()).andExpect(content().string(Matchers.containsString("\"code\":1000")));
	}
}