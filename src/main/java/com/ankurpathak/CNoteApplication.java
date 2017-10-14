package com.ankurpathak;

import com.ankurpathak.server.domain.repository.IClientRepository;
import com.ankurpathak.server.domain.repository.ICountryRepository;
import com.ankurpathak.server.domain.repository.IOrganizationRepository;
import com.ankurpathak.server.domain.repository.IUserRepository;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.Filter;

@SpringBootApplication
public class CNoteApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(CNoteApplication.class, args);
		ConfigurableListableBeanFactory factory = context.getBeanFactory();
		BeanDefinitionRegistry registory = (BeanDefinitionRegistry) factory;




	}


}

//@Component
class CNoteApplicationCommandLineRunner implements CommandLineRunner{

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private ICountryRepository countryRepository;


	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IOrganizationRepository organizationRepository;

	@Autowired
	private IClientRepository clientRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Autowired
	private MongoClient mongoClient;


	@Autowired
	private Filter[] defaultSecurityFilterChains;



	@Override
	public void run(String... strings) throws Exception {

		/*





		mongoTemplate.dropCollection("countries");
		mongoTemplate.dropCollection("users");
		mongoTemplate.dropCollection("organizations");
		mongoTemplate.dropCollection("divisions");
		mongoTemplate.dropCollection("subDivisions");
		mongoTemplate.dropCollection("products");
		mongoTemplate.dropCollection("clients");
		mongoTemplate.dropCollection("customers");
		mongoTemplate.dropCollection("enquiries");





		Country country = new Country();
		country.setName("india");
		country.setCode("91");
		country.setIso3("ind");

		countryRepository.save(country);

		Location location = new Location();
		location.setAddress("Pathare Vasti, Lohegaon");
		location.setCity("Pune");
		location.setRegion("Maharashtra");
		location.setPostalCode("411047");
		location.setCountry(country);



		Organization organization = new Organization();
		organization.setName("Ankur Pathak Inc");
		organization.setRef("ankur_pathak");
		organization.setLocation(location);
		organizationRepository.save(organization);



		User user = new User();
		user.setFirstName("Admin");
		user.setLastName("Admin");
		user.setEmail("ankur.pathak@pharmerz.com");
		user.setUsername("adminsquare");
		user.setPassword("Password@123");
		user.setRoles(EnumSet.allOf(User.Role.class));
		user.setLocation(location);
		user.setContact("+917385500660");
		user.setImgUrl("https://www.pharmerz.com");
		user.setOrganization(organization);
		userRepository.save(user);


		user = new User();
		user.setFirstName("User");
		user.setLastName("User");
		user.setEmail("ankurpathak@hotmail.com");
		user.setUsername("usersquare");
		user.setPassword("Password@123");
		user.setRoles(EnumSet.of(User.Role.ROLE_USER));
		location = new Location();
		location.setAddress("Pathare Vasti, Lohegaon");
		location.setCity("Pune");
		location.setRegion("Maharashtra");
		location.setPostalCode("411047");
		location.setCountry(country);
		user.setContact("+917588334408");
		user.setLocation(location);
		user.setImgUrl("https://www.pharmerz.com");
		user.setOrganization(organization);
		userRepository.save(user);








		Client client = new Client();
		client.setClientId("client");
		client.setClientSecret("clientpassword");
		client.setScopes(StringUtils.commaDelimitedListToSet("ACCESS"));
		//client.setRegisteredRedirectUris(StringUtils.commaDelimitedListToSet("http://localhost/response"));
		//client.setAuthorizedGrantTypes(StringUtils.commaDelimitedListToSet("authorization_code,refresh_token,password,implicit,client_credentials"));
		client.setAuthorizedGrantTypes(Arrays.asList(Client.AuthorizedGrantType.password, Client.AuthorizedGrantType.refresh_token, Client.AuthorizedGrantType.client_credentials).stream().collect(Collectors.toSet()));
		client.setAcccessTokenValiditySeconds(2 * 60);
		client.setRefreshTokenValiditySeconds(5 * 60);
		client.setOrganization(organization);
		clientRepository.save(client); */


		System.out.println();




	}
}
