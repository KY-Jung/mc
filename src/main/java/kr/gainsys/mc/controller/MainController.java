package kr.gainsys.mc.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	////////////////////////////////////////////////////////////////////////////////
	@Value("${server.servlet.context-path}")
	private String CONTEXT_PATH;
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/")
	public String doMain(HttpServletRequest request) {
		System.out.println("# " + CONTEXT_PATH + "/");
		return "redirect:/thymeleaf";		// CONTEXT_PATH + "/thymeleaf" 로 이동
	}
	////////////////////////////////////////////////////////////////////////////////


}
