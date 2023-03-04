package kr.gainsys.mc.dto;

import kr.gainsys.mc.domain.test.TestPost;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class TestPostRequestDto {

	private String title;
	private String content;
	private String author;

	@Builder
	public TestPostRequestDto(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}

	public TestPost toEntity() {
		TestPost testPost = TestPost.builder()
				.title(title)
				.content(content)
				.author(author)
				.build();
		return testPost;
	}

}
