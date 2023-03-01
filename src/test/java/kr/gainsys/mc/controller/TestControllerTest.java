package kr.gainsys.mc.controller;

import com.google.gson.Gson;
import kr.gainsys.mc.dto.PostRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)		// 없어도 에러 안남
//@WebMvcTest	--> error : required a bean of type
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestControllerTest {

	////////////////////////////////////////////////////////////////////////////////
	@Value("${server.servlet.context-path}")
	private String CONTEXT_PATH;
	@LocalServerPort
	private int LOCALSERVER_PORT;
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TestRestTemplate testRestTemplate;
	////////////////////////////////////////////////////////////////////////////////

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
	public void doTestPost() throws Exception {
	//public Long doTestPost(@RequestBody PostRequestDto postRequestDto) {
		String url = "http://127.0.0.1:" + LOCALSERVER_PORT + CONTEXT_PATH + "/test_post";
		System.out.println("url:" + url);

		String title = "json title";
		String content = "json content";
		String author = "json author";
		PostRequestDto postRequestDto = PostRequestDto.builder()
				.title(title)
				.content(content)
				.author(author)
				.build();
		Gson gson = new Gson();
		String str_json = gson.toJson(postRequestDto);
		//PostRequestDto postRequestDto2 = gson.fromJson(str_json, PostRequestDto.class);
		//System.out.println("postRequestDto2:" + postRequestDto2);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<String>(str_json, httpHeaders);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(url, HttpMethod.POST,
				httpEntity, String.class);
		System.out.println("# result: " + responseEntity.getBody());

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	@Test
	public void doTestSubmit() throws Exception {
	//public String doTestSubmit(HttpServletRequest request) {
		String url = "http://127.0.0.1:" + LOCALSERVER_PORT + CONTEXT_PATH + "/test_submit";
		System.out.println("url:" + url);

		String title = "submit title";
		String content = "submit content";
		String author = "submit author";

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("title", title);
		params.add("content", content);
		params.add("author", author);

		HttpHeaders httpHeaders = new HttpHeaders();
		//httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, httpHeaders);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(url, HttpMethod.POST,
				httpEntity, String.class);
		System.out.println("# result: " + responseEntity.getBody());

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).contains("success");
	}
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	/*
	// mybatis 사용 안함
	@Test
	public void doTestMybatis() throws Exception {
	//public String doTestMybatis(HttpServletRequest request) {
		mockMvc.perform(get("/test_mybatis"))
				.andExpect(status().isOk());
	}
	*/
	////////////////////////////////////////////////////////////////////////////////

}
