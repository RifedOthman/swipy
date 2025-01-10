package com.rifed.swipe_network;

import com.rifed.swipe_network.role.Role;
import com.rifed.swipe_network.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class SwipeNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwipeNetworkApplication.class, args);


	}

	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository){
		return args -> {
			if (roleRepository.findByName("USER").isEmpty()){
				roleRepository.save(
						Role.builder()
								.name("USER")
								.createdDate(LocalDateTime.now())
								.build()
				);
			}
		};
	}

}
