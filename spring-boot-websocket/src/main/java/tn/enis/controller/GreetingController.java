package tn.enis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import tn.enis.dto.Greeting;
import tn.enis.dto.HelloMessage;

@Controller
public class GreetingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);
	private final SimpMessagingTemplate messagingTemplate;

	public GreetingController(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	@MessageMapping("/hello") // + config.setApplicationDestinationPrefixes("/app"); de Config
	public void greeting(HelloMessage message) throws Exception {
		LOGGER.info("Thread : {} Received greeting message: {}", Thread.currentThread(), message);
		Thread.sleep(500); // simulated delay

		messagingTemplate.convertAndSend("/topic/greetings",
				new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!"), message1 -> {
					LOGGER.info("Thread : {} message1: {}", Thread.currentThread(), message1);
					return message1;
				});
	}

}
