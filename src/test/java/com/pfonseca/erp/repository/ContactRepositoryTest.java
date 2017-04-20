package com.pfonseca.erp.repository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.javafaker.Faker;
import com.pfonseca.erp.OAuthHelper;
import com.pfonseca.erp.domain.Contact;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ContactRepositoryTest {

	@Autowired
	private WebApplicationContext webapp;

	@Autowired
	private OAuthHelper authHelper;

	private MockMvc restMvc;

	@Autowired
	private ContactRepository contactRepository;

	@Before
	@Transactional
	public void setup() {
		Faker faker = new Faker();

		Contact person = new Contact();
		person.setName(faker.name().fullName());

		contactRepository.save(person);

		restMvc = MockMvcBuilders.webAppContextSetup(webapp).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@Test
	public void resourceLoads() throws Exception {

		RequestPostProcessor bearerToken = authHelper.addBearerToken("test", "ROLE_USER");
		ResultActions resultActions = restMvc.perform(get("/contacts/").with(bearerToken)).andDo(print());

		resultActions.andExpect(status().isOk()).andExpect(content().string("hello"));

	}

}
