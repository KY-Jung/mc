package kr.gainsys.mc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kr.gainsys.mc.dto.TestPostRequestDto;
import kr.gainsys.mc.service.TestPostService;
import kr.gainsys.mc.vo.TestMybatisVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import kr.gainsys.mc.service.TestMybatisService;


@Slf4j
@Controller
public class TestController {

	////////////////////////////////////////////////////////////////////////////////
	@Value("${server.servlet.context-path}")
	private String CONTEXT_PATH;
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@Autowired
	TestMybatisService testMybatisService;

	@Autowired
	TestPostService testPostService;
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/hello")
	@ResponseBody
	public String doHello(HttpServletRequest request) {
		log.info("doTestThymeleaf");

		System.out.println("doHello");
		System.out.println("CONTEXT_PATH:" + CONTEXT_PATH);
		return "hi you";
	}

	@GetMapping("/test_thymeleaf")
	public ModelAndView doTestThymeleaf(HttpServletRequest request) {
		log.info("doTestThymeleaf");

		ModelAndView mav = new ModelAndView();
		List<String> list_result = new ArrayList<String>();

		list_result.add("AAA");
		list_result.add("BBB");
		list_result.add("CCC");
		list_result.add("DDD");
		list_result.add("EEE");
		list_result.add("FFF");

		mav.addObject("list_result",list_result);
		mav.setViewName("content/home");

		System.out.println("en : " + messageSourceAccessor.getMessage("common.hello", new String[] { "Jung" }, Locale.US));
		System.out.println("ko : " + messageSourceAccessor.getMessage("common.hello", new String[] { "정" }, Locale.KOREA));
		System.out.println("클라이언트 언어 : " + messageSourceAccessor.getMessage("common.hello", new String[] { "정" }));

		return mav;
	}

	@PostMapping("/test_post")
	@ResponseBody
	public String doTestPost(@RequestBody TestPostRequestDto testPostRequestDto) {
		log.info("doTestPost");

		System.out.println("doTestPost");
		System.out.println("postRequestDto: " + testPostRequestDto.toString());

		Long id = testPostService.insert(testPostRequestDto);

		return id + "";
		//return "success";
	}

	@PostMapping("/test_submit")
	@ResponseBody
	public String doTestSubmit(HttpServletRequest request) {
		log.info("doTestSubmit");

		System.out.println("doTestSubmit");
		System.out.println("title: " + request.getParameter("title"));

		return "success";
	}
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	// mybatis
	@GetMapping("/test_mybatis")
	@ResponseBody
	public String doTestMybatis(HttpServletRequest request) {
		log.info("doTestMybatis");

		List<TestMybatisVo> list_test_mybatis = testMybatisService.selectListAll();
		System.out.println("list_test_mybatis: " + list_test_mybatis.toString());

		return list_test_mybatis.toString();
	}

	////////////////////////////////////////////////////////////////////////////////

}
