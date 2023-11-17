package com.example.caoimhespetition;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class CaoimhespetitionApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	// Makes sure '/' is working and returns index.html
	@Test
	void homeEndpointShouldReturnIndex() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("index"));
	}

	// Makes sure '/createpetitions' is working and returns createpetitions.html
	@Test
	void createPetitionsEndpointShouldReturnCreatePetitions() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/createpetitions"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("createpetitions"));
	}

	// Makes sure '/viewpetitions' is working and returns viewpetitions.html
	@Test
	void viewPetitionsEndpointShouldReturnViewPetitions() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/viewpetitions"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("viewpetitions"));
	}

	// Makes sure '/searchpetition' is working and returns searchpetition.html
	@Test
	void searchPetitionsEndpointShouldReturnSearchPetitions() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/searchpetitions"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("searchpetitions"));
	}

	// Makes sure '/petitionDetails/{title}' is working with a petition and returns petitionDetails.html
	@Test
	void petitionDetailsEndpointShouldReturnPetitionDetails() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/petitionDetails/{title}", "Wild Cats"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("petitionDetails"));
	}

	// Passes petition to make sure '/submitpetition' is working and redirects to /viewpetitions
	@Test
	void submitPetitionEndpointShouldRedirectToViewPetitions() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/submitpetition")
						.param("title", "Test Petition")
						.param("author", "Test Test")
						.param("description", "Test petition")
				)
				.andExpect(MockMvcResultMatchers.redirectedUrl("/viewpetitions"));
	}

	// Passes signature to make sure '/signpetition' is working for a select petition and redirects to /viewpetitions
	@Test
	void signPetitionEndpointShouldRedirectToViewPetitions() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/signpetition/{title}", "Wild Cats")
						.param("name", "Test Test")
						.param("email", "test@test.com")
				)
				.andExpect(MockMvcResultMatchers.redirectedUrl("/viewpetitions"));
	}

	// Passes a search to make sure '/search' is working, the petition is in the Hashmap and the result is returned on
	// with searchresults.html
	@Test
	void searchPetitionsEndpointShouldReturnSearchResults() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/search")
						.param("searchTerm", "Wild"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("searchresults"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("petitions"));
	}
}
