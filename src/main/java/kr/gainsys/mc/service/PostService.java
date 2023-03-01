package kr.gainsys.mc.service;

import jakarta.transaction.Transactional;
import kr.gainsys.mc.domain.post.Post;
import kr.gainsys.mc.domain.post.PostRepository;
import kr.gainsys.mc.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor	// final 일 경우 Autowired 처리해줌
@Service
public class PostService {

	private final PostRepository postRepository;

	@Transactional
	public Long insert(PostRequestDto postRequestDto) {
		Post post = postRepository.save(postRequestDto.toEntity());

		return post.getId();
	}


}
