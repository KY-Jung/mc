package kr.gainsys.mc.controller;

import com.google.gson.Gson;
import kr.gainsys.mc.dto.TestPostRequestDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)				// JPA 를 사용할때는 없으면 에러
//@ExtendWith(SpringExtension.class)		// 없어도 에러 안남
//@WebMvcTest	--> error : required a bean of type
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestControllerTest {

	////////////////////////////////////////////////////////////////////////////////
	static {
		// 이미 run 되어 있을 경우에도 테스트 가능
		System.setProperty("tomcat.ajp.port", "7007");
	}
	////////////////////////////////////////////////////////////////////////////////

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
	@Value("${server.name}")
	private String SERVER_NAME;
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@Test
	@WithMockUser(roles = "USER")
	public void doHello() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/hello"))
				.andExpect(status().isOk())
				.andExpect(content().string("hi you"))
				.andReturn();
		String ret = mvcResult.getResponse().getContentAsString();
		System.out.println("# doHello: " + ret);
	}

	@Test
	@WithMockUser(roles = "USER")
	public void doTestThymeleaf() throws Exception {
		mockMvc.perform(get("/test_thymeleaf"))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(roles = "USER")
	public void doTestPost() throws Exception {
		String url = "http://127.0.0.1:" + LOCALSERVER_PORT + CONTEXT_PATH + "/test_post";
		System.out.println("url:" + url);

		String title = "json title";
		String content = "json content";
		String author = "json author";
		TestPostRequestDto testPostRequestDto = TestPostRequestDto.builder()
				.title(title)
				.content(content)
				.author(author)
				.build();
		String str_json = new Gson().toJson(testPostRequestDto);
		//str_json = new ObjectMapper().writeValueAsString(postRequestDto);
		//PostRequestDto postRequestDto2 = new Gson().fromJson(str_json, PostRequestDto.class);
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
	@WithMockUser(roles = "USER")
	public void doTestSubmit() throws Exception {
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
	// mybatis
	@Test
	public void doTestMybatis() throws Exception {
		mockMvc.perform(get("/test_mybatis"))
				.andExpect(status().isOk());
	}
	////////////////////////////////////////////////////////////////////////////////

}
