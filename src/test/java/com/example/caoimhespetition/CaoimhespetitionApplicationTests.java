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
}
