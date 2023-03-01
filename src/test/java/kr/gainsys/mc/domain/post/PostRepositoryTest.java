package kr.gainsys.mc.domain.post;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)			// JPA 를 사용할때는 없으면 에러
//@ExtendWith(SpringExtension.class)	// error
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostRepositoryTest {

	////////////////////////////////////////////////////////////////////////////////
	@LocalServerPort
	private int LOCALSERVER_PORT;
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@Autowired
	PostRepository postRepository;
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@After
	public void cleanup() {
		//postRepository.deleteAll();
	}
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@Test
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
