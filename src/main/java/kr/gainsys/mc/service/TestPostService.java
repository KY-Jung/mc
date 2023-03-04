package kr.gainsys.mc.service;

import jakarta.transaction.Transactional;
import kr.gainsys.mc.domain.test.TestPost;
import kr.gainsys.mc.domain.test.TestPostRepository;
import kr.gainsys.mc.dto.TestPostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor	// final 일 경우 Autowired 처리해줌
@Service
public class TestPostService {

	private final TestPostRepository testPostRepository;

	@Transactional
	public Long insert(TestPostRequestDto testPostRequestDto) {
		TestPost testPost = testPostRepository.save(testPostRequestDto.toEntity());

		return testPost.getId();
	}

}
