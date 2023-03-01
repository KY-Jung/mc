package kr.gainsys.mc.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)	// error
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
@AutoConfigureMockMvc
public class PostRepositoryTest {

	@Autowired
	PostsRepository postsRepository;

	@After
	public void cleanup() {
		//postsRepository.deleteAll();
	}

	@Test
	public void testSave() throws Exception {
		LocalDateTime cur = LocalDateTime.now();
		String str_time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(cur);

		String title = "test 게시물 " + str_time;
		String content = "test 내용 " + str_time;
		String author = "ky@gainsys.kr";
		Posts posts = postsRepository.save(Posts.builder()
				.title(title)
				.content(content)
				.author(author)
				.build());

		Optional<Posts> optional_posts = postsRepository.findById(posts.getId());
		if (!optional_posts.isPresent()) {
			throw new Exception ("DB insert error");
		}
		Posts post = optional_posts.get();
		System.out.println(post);

		assertThat(post.getTitle()).isEqualTo(title);
		assertThat(post.getContent()).isEqualTo(content);

	}

}
