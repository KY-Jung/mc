package kr.gainsys.mc.dto;

import kr.gainsys.mc.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class PostRequestDto {

	private String title;
	private String content;
	private String author;

	@Builder
	public PostRequestDto(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}

	public Post toEntity() {
		Post post = Post.builder()
				.title(title)
				.content(content)
				.author(author)
				.build();
		return post;
	}

}
