package com.open.shop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.lang.NonNull;
import org.springframework.r2dbc.core.DatabaseClient;

import com.open.shop.repository.BrandRepository;

import io.r2dbc.spi.ConnectionFactory;
import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ShopApplication {

	@Autowired
	@NonNull
	ApplicationContext context;

	@Autowired
	@NonNull
	BrandRepository brandRepository;

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);

	}

	@PostConstruct
	public void init() {
		ConnectionFactory connectionFactory = context.getBean(ConnectionFactory.class);
		System.out.println("Connection factory: " + connectionFactory);

		R2dbcEntityTemplate template = new R2dbcEntityTemplate(connectionFactory);

		DatabaseClient dbClient = template.getDatabaseClient();

		String[] createTablesSqls = {
				"""
									CREATE TABLE IF NOT EXISTS brand (
										id INT AUTO_INCREMENT NOT NULL,
										name VARCHAR(255) NOT NULL,
										description VARCHAR(255),
										PRIMARY KEY(id));
						""",
				"""
									CREATE TABLE IF NOT EXISTS category (
										id INT AUTO_INCREMENT NOT NULL,
										name VARCHAR(255) NOT NULL,
										description VARCHAR(255),
										parent_category_id INT,
										PRIMARY KEY(id),
										CONSTRAINT fk_parent_category FOREIGN KEY (parent_category_id) REFERENCES category(id)
											ON DELETE CASCADE
											ON UPDATE CASCADE);
						""",
				"""
								CREATE TABLE IF NOT EXISTS product (
									id INT AUTO_INCREMENT NOT NULL,
									name VARCHAR(255) NOT NULL,
									price DOUBLE NOT NULL,
									description VARCHAR(255) NOT NULL,
									image_url VARCHAR(255),
									category_id INT NOT NULL,
									brand_id INT NOT NULL,
									color VARCHAR(255),
									size VARCHAR(255),
									weight VARCHAR(255),
									material VARCHAR(255),
									created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
									updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
									PRIMARY KEY(id),
									CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category(id)
										ON DELETE CASCADE
										ON UPDATE CASCADE,
									CONSTRAINT fk_brand FOREIGN KEY (brand_id) REFERENCES brand(id)
										ON DELETE CASCADE
										ON UPDATE CASCADE);
						"""
		};

		List<Mono<Long>> createTables = Arrays.asList(createTablesSqls).stream()
				.map(sql -> dbClient.sql(sql).fetch().rowsUpdated())
				.collect(Collectors.toList());

		Mono.when(createTables).subscribe(a -> {
			System.out.println("Tables created successfully!");
		});

		// brandRepository.count().flatMap(count -> {
		// System.out.println("Brand count: " + count);

		// return Mono.empty();
		// }).subscribe(count -> {
		// System.out.println("Brand count: " + count);
		// });
	}

}
