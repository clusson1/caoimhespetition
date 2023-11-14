package com.example.caoimhespetition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Controller
public class CaoimhespetitionApplication {

	//Hashmap where all petitions will be stored
	private final Map<String, Petition> petitions = new HashMap<>();

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/createpetitions")
	public String createPetitions() {
		return "createpetitions";
	}

	@RequestMapping("/viewpetitions")
	public String viewPetitions(Model model) {
		model.addAttribute("petitions", petitions.values());
		return "viewpetitions";
	}

	//Redirects /submitPetition to viewpetitions to see all petitions
	@PostMapping("/submitpetition")
	public String submitPetition(Petition petition) {
		petitions.put(petition.getTitle(), petition);
		return "redirect:/viewpetitions";
	}

	public static void main(String[] args) {
		SpringApplication.run(CaoimhespetitionApplication.class, args);
	}

	public static class Petition {
		private String title;
		private String author;
		private String description;

		// Getters and setters

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}
}

