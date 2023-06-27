package cuz.gpaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GpawebApplication {
	public static void main(String[] args) {
		SpringApplication.run(GpawebApplication.class, args);
	}

	@GetMapping("/")
	public String home() {
		return "GPAweb";
	}
}
