package kr.gainsys.mc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import kr.gainsys.mc.service.IdService;
import kr.gainsys.mc.vo.IdVo;



@Slf4j
@Controller
public class TestController {

	////////////////////////////////////////////////////////////////////////////////
	@Value("${server.servlet.context-path}")
	private String CONTEXT_PATH;
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@Autowired
	IdService idService;
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/hello")
	@ResponseBody
	public String doHello(HttpServletRequest request) {
		System.out.println("doHello");
		System.out.println("CONTEXT_PATH:" + CONTEXT_PATH);
		return "hi you";
	}

	@GetMapping("/test_thymeleaf")
	public ModelAndView doTestThymeleaf(HttpServletRequest request) {
		System.out.println("doTestThymeleaf");
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

	@GetMapping("/test_db")
	@ResponseBody
	public String doTestDb(HttpServletRequest request) {
		System.out.println("test_db");

		List<IdVo> list_idVo = idService.selectIdList();
		System.out.println("list_idVo:" + list_idVo.toString());

		return list_idVo.toString();
	}
	////////////////////////////////////////////////////////////////////////////////


}
