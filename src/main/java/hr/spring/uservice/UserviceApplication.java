package hr.spring.uservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import hr.spring.uservice.auth.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Slf4j
@SpringBootApplication
public class UserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserviceApplication.class, args);
	}

	@Component
	public class TestRunner implements CommandLineRunner {

		public void run(String... args) {

		    // TEST
			User user = new User();
			user.setUsername("mjun");
			user.setFirstName("Matko");
			user.setLastName("Jun");
			user.setEmail("matko.jun@gmail.com");
			user.setPassword("test123");
			user.save();

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = "";
            try {
                json = ow.writeValueAsString(user);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            log.info("### " + json);

			user.delete();

            try {
                json = ow.writeValueAsString(user);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            log.info("### " + json);

            user.save();

            try {
                json = ow.writeValueAsString(user);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            log.info("### " + json);

		}

	}
}
