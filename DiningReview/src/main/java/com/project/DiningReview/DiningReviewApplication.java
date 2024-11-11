package com.project.DiningReview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.project.DiningReview.entities")
@EnableJpaRepositories(basePackages = "com.project.DiningReview.repositories")
public class DiningReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiningReviewApplication.class, args);
	}

}
