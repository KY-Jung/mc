package kr.gainsys.mc.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
// @WebMvcTest	--> error : required a bean of type
@SpringBootTest
@AutoConfigureMockMvc
class TestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	////////////////////////////////////////////////////////////////////////////////
	@Test
	public void doHello() throws Exception {
	//public String doHello(HttpServletRequest request) {
		MvcResult mvcResult = mockMvc.perform(get("/hello"))
				.andExpect(status().isOk())
				.andExpect(content().string("hi you"))
				.andReturn();
		String ret = mvcResult.getResponse().getContentAsString();
		System.out.println("# doHello: " + ret);
	}

	@Test
	public void doTestThymeleaf() throws Exception {
	//public ModelAndView doTestThymeleaf(HttpServletRequest request) {
		mockMvc.perform(get("/test_thymeleaf"))
				.andExpect(status().isOk());
	}

	@Test
	public void doTestDb() throws Exception {
	//public String doTestDb(HttpServletRequest request) {
		mockMvc.perform(get("/test_db"))
				.andExpect(status().isOk());
	}
	////////////////////////////////////////////////////////////////////////////////

}