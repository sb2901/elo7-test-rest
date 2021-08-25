package br.com.elo7.test.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import br.com.elo7.test.manage.NASAMarsController;

@WebMvcTest
public class ProbeControllersTest {

	public static final MediaType APPLICATION_TEXT_PLAIN = new MediaType(MediaType.TEXT_PLAIN.getType(), MediaType.TEXT_PLAIN.getSubtype(), Charset.forName("utf8"));
	
	String data = "5 5\n" 
			+ "1 2 N\n" 
			+ "LMLMLMLMM\n" 
			+ "3 3 E\n" 
			+ "MMRMMRMRRM";
	
	String dataWrongPattern = "5 \n" 
			+ "1 2 N";
	
	String dataResult = "1 3 N\n" 
						+ "5 1 E";

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnSucesstMessage() throws Exception {
		ResultActions resultActions= this.mockMvc.perform(
				post("/mars/probe/execute")
				.contentType(APPLICATION_TEXT_PLAIN)
				.content(data));
		resultActions
			.andExpect(status().isOk())
			.andExpect(content().string(containsString(dataResult)));
	}

	@Test
	public void shouldReturnWrongPattern() throws Exception {
		ResultActions resultActions= this.mockMvc.perform(
				post("/mars/probe/execute")
				.contentType(APPLICATION_TEXT_PLAIN)
				.content(dataWrongPattern));
		resultActions
			.andExpect(status().isOk())
			.andExpect(content().string(containsString(NASAMarsController.WRONG_PATTERN)));
	}

}
