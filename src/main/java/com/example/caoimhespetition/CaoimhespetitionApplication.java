package com.example.caoimhespetition;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Controller
public class CaoimhespetitionApplication implements CommandLineRunner {
	// Hashmap where all petitions will be stored
	private final Map<String, Petition> petitions = new HashMap<>();

	// Dummy data
	@Override
	public void run(String... args) {
		// First dummy petition
		Petition dummyPetition1 = new Petition();
		dummyPetition1.setTitle("Wild Cats");
		dummyPetition1.setAuthor("Joe Bloggs");
		dummyPetition1.setDescription("The wild cats are in need. Sign this to support them.");
		Signature johnDoe = new Signature();
		johnDoe.setName("John Doe");
		johnDoe.setEmail("john@hotmail.com");
		Signature janeDoe = new Signature();
		janeDoe.setName("Jane Doe");
		janeDoe.setEmail("jane@gmail.com");
		dummyPetition1.getSignatures().add(johnDoe);
		dummyPetition1.getSignatures().add(janeDoe);
		petitions.put(dummyPetition1.getTitle(), dummyPetition1);

		// Second dummy petition
		Petition dummyPetition2 = new Petition();
		dummyPetition2.setTitle("Palestine");
		dummyPetition2.setAuthor("Helda Dimples");
		dummyPetition2.setDescription("Sign this to support Palestinian refugees.");
		Signature joeBloggs = new Signature();
		joeBloggs.setName("Joe Bloggs");
		joeBloggs.setEmail("joe@mailosaur.com");
		Signature kenCarey = new Signature();
		kenCarey.setName("Ken Carey");
		kenCarey.setEmail("ken@yahoo.com");
		dummyPetition2.getSignatures().add(joeBloggs);
		dummyPetition2.getSignatures().add(johnDoe);
		dummyPetition2.getSignatures().add(kenCarey);
		petitions.put(dummyPetition2.getTitle(), dummyPetition2);

		// Third dummy petition
		Petition dummyPetition3 = new Petition();
		dummyPetition3.setTitle("Global Warming");
		dummyPetition3.setAuthor("Ken Carey");
		dummyPetition3.setDescription("We should do more for the planet. Sign here to contribute!");
		Signature johnnyNoggins = new Signature();
		johnnyNoggins.setName("Johnny Noggins");
		johnnyNoggins.setEmail("johnny@gmail.com");
		Signature jennyGail = new Signature();
		jennyGail.setName("Jenny Gail");
		jennyGail.setEmail("jen@nuigalway.ie");
		dummyPetition3.getSignatures().add(joeBloggs);
		dummyPetition3.getSignatures().add(jennyGail);
		dummyPetition3.getSignatures().add(johnnyNoggins);
		dummyPetition3.getSignatures().add(janeDoe);
		petitions.put(dummyPetition3.getTitle(), dummyPetition3);
	}

	// Mapping for /
	@RequestMapping("/")
	public String home() {
		return "index";
	}

	// Mapping for /createpetitions
	@RequestMapping("/createpetitions")
	public String createPetitions() {
		return "createpetitions";
	}

	// Mapping for /viewpetitions
	@RequestMapping("/viewpetitions")
	public String viewPetitions(Model model) {
		model.addAttribute("petitions", petitions.values());
		return "viewpetitions";
	}

	// Mapping for /searchpetitions
	@RequestMapping("/searchpetitions")
	public String searchPetitions(Model model) {
		model.addAttribute("petitions", petitions.values());
		return "searchpetitions";
	}

	// Mapping for individual /petitiondetails
	@RequestMapping("/petitionDetails/{title}")
	public String petitionDetails(@PathVariable String title, Model model) {
		Petition petition = petitions.get(title);
		model.addAttribute("petition", petition);
		return "petitionDetails";
	}

	//Redirects /submitpetition to viewpetitions to see all petitions
	@PostMapping("/submitpetition")
	public String submitPetition(Petition petition) {
		petitions.put(petition.getTitle(), petition);
		return "redirect:/viewpetitions";
	}

	// Mapping for /signpetitions
	@PostMapping("/signpetition/{title}")
	public String signPetition(@PathVariable String title, @ModelAttribute Signature signature) {
		Petition petition = petitions.get(title);
		if (petition != null) {
			petition.getSignatures().add(signature);
		}
		return "redirect:/viewpetitions";
	}

	// Mapping for /search
	@RequestMapping("/search")
	public String searchPetitions(HttpServletRequest request, Model model) {
		String searchTerm = request.getParameter("searchTerm");
		List<Petition> searchResults = new ArrayList<>();
		for (Petition petition : petitions.values()) {
			if (petition.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
				searchResults.add(petition);
			}
		}
		model.addAttribute("petitions", searchResults);
		return "searchresults";
	}
	// Runs the Application
	public static void main(String[] args) {
		SpringApplication.run(CaoimhespetitionApplication.class, args);
	}

	// Petition Class
	public static class Petition {
		// Variables
		private String title;
		private String author;
		private String description;
		private List<Signature> signatures = new ArrayList<>();

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

		public List<Signature> getSignatures() {
			return signatures;
		}
	}

	// Signature Class
	public static class Signature {
		// Variables
		private String name;
		private String email;

		// Constructors
		public Signature(String name, String email) {
			this.name = name;
			this.email = email;
		}

		public Signature() {}

		// Getters and setters
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}
}

