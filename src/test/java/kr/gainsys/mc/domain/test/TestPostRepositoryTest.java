package kr.gainsys.mc.domain.test;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)			// JPA 를 사용할때는 없으면 에러
//@ExtendWith(SpringExtension.class)	// error
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestPostRepositoryTest {

	////////////////////////////////////////////////////////////////////////////////
	static {
		// 이미 run 되어 있을 경우에도 테스트 가능
		System.setProperty("tomcat.ajp.port", "7007");
	}
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@LocalServerPort
	private int LOCALSERVER_PORT;
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Autowired
	TestPostRepository testPostRepository;
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@After
	public void cleanup() {
		//postRepository.deleteAll();
	}
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@Test
	@WithMockUser(roles = "USER")
	public void testSave() throws Exception {
		LocalDateTime cur = LocalDateTime.now();
		String str_time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(cur);

		String title = "PostRepositoryTest 게시물 " + str_time;
		String content = "PostRepositoryTest 내용 " + str_time;
		String author = "ky@gainsys.kr";
		TestPost testPost = testPostRepository.save(TestPost.builder()
				.title(title)
				.content(content)
				.author(author)
				.build());

		Optional<TestPost> optional_posts = testPostRepository.findById(testPost.getId());
		if (!optional_posts.isPresent()) {
			throw new Exception ("DB insert error");
		}
		TestPost ret_testPost = optional_posts.get();
		System.out.println(ret_testPost);

		assertThat(ret_testPost.getTitle()).isEqualTo(title);
		assertThat(ret_testPost.getContent()).isEqualTo(content);
	}
	////////////////////////////////////////////////////////////////////////////////

}
