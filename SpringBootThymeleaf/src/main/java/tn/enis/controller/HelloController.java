package tn.enis.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello")
public class HelloController {
	//http://localhost:8080/hello/test1
	@GetMapping(value = "/test1")
	public String test1() {
		return "hello";
	}
	//http://localhost:8080/hello/test2
	@GetMapping(value = "/test2")
	public ModelAndView test2() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("time", LocalDateTime.now());
		modelAndView.setViewName("hello"); // va à la page hello.html
		return modelAndView;
	}
	//http://localhost:8080/hello/test3
	@GetMapping(value = "/test3")
	public ModelAndView test3() {
		ModelAndView modelAndView = new ModelAndView();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String serverTime = LocalDateTime.now().format(formatter);
		modelAndView.addObject("time", serverTime);
		modelAndView.setViewName("hello"); // va à la page hello.html
		return modelAndView;
	}

}
