package com.example.caoimhespetition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class CaoimhespetitionApplication {
	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/createpetitions")
	public String createPetitions() {
		return "createPetitions";
	}

	@RequestMapping("/viewpetitions")
	public String viewPetitions() {
		return "viewPetitions";
	}

	@RequestMapping("/searchpetitions")
	public String searchPetitions() {
		return "searchPetitions";
	}

	public static void main(String[] args) {
		SpringApplication.run(CaoimhespetitionApplication.class, args);
	}
}

