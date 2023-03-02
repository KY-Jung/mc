package kr.gainsys.mc.domain.post;

//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
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
import org.springframework.test.web.servlet.MockMvcBuilder;
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
public class PostRepositoryTest {

	////////////////////////////////////////////////////////////////////////////////
	@LocalServerPort
	private int LOCALSERVER_PORT;
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Autowired
	PostRepository postRepository;
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
		Post post = postRepository.save(Post.builder()
				.title(title)
				.content(content)
				.author(author)
				.build());

		Optional<Post> optional_posts = postRepository.findById(post.getId());
		if (!optional_posts.isPresent()) {
			throw new Exception ("DB insert error");
		}
		Post ret_post = optional_posts.get();
		System.out.println(ret_post);

		assertThat(ret_post.getTitle()).isEqualTo(title);
		assertThat(ret_post.getContent()).isEqualTo(content);
	}
	////////////////////////////////////////////////////////////////////////////////

}
