package hr.spring.uservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.querydsl.core.types.dsl.BooleanExpression;
import hr.spring.uservice.auth.model.QUser;
import hr.spring.uservice.auth.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@SpringBootApplication
public class UserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserviceApplication.class, args);
	}

	@Component
	public class TestRunner implements CommandLineRunner {

        @Transactional
		public void run(String... args) {

		    // TEST
			User user = new User();
			user.setUsername("mjun");
			user.setFirstName("Matko");
			user.setLastName("Jun");
			user.setEmail("matko.jun@gmail.com");
			user.setPassword("test123");
			user.setEnabled(true);
			user.save();


            User user2 = new User();
            user2.setUsername("user2");
            user2.setFirstName("User");
            user2.setLastName("Name 2");
            user2.setEmail("test@example.com");
            user2.setPassword("test123");
            user2.setEnabled(true);
            user2.save();


            User user3 = new User();
            user3.setUsername("user3");
            user3.setFirstName("User");
            user3.setLastName("Name 3");
            user3.setEmail("test2@example.com");
            user3.setPassword("test123");
            user3.setEnabled(false);
            user3.save();

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

            log.info("----------> findAll");
            for (User u : User.getRepository().findAll()) {
                try {
                    log.info(ow.writeValueAsString(u));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            // You can use BooleanExpression/BooleanBuilder to create the predicate passed to findAll / findOne
            // OR you can just make plain ol' interface methods or @Query annotated methods in repository interface
            BooleanExpression enabledUsers = QUser.user.enabled.isTrue();

            log.info("----------> queryDsl");
            for (User u : User.getRepository().findAll(enabledUsers)) {
                try {
                    log.info(ow.writeValueAsString(u));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }


		}

	}
}
