package com.mydeveloperplanet.myliquibaseplanet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Testcontainers
class MyliquibasePlanetApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Container
	static PostgreSQLContainer<?> postgreSQL = new PostgreSQLContainer<>();

	@DynamicPropertySource
	static void postgreSQLProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgreSQL::getJdbcUrl);
		registry.add("spring.datasource.username", postgreSQL::getUsername);
		registry.add("spring.datasource.password", postgreSQL::getPassword);
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void getAllEmployees() throws Exception {
		this.mockMvc.perform(get("/getAllEmployees")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("[{\"id\":1,\"firstName\":\"Foo\",\"lastName\":\"Bar\",\"country\":\"Sweden\"}]"));
	}

}
