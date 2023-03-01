package kr.gainsys.mc.domain.post;

import jakarta.persistence.*;
import kr.gainsys.mc.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 500, nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String author;

	@Builder
	public Post(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}

	public void update(String title, String content, String author) {
		if (title != null)		this.title = title;
		if (content != null)	this.content = content;
		if (author != null)		this.author = author;
	}

}
