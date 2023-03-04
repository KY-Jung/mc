package kr.gainsys.mc.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class TestMybatisVo {

	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private String title;
	private String content;
	private String author;

}
