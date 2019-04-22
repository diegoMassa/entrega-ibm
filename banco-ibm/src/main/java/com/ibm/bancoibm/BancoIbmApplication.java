package com.ibm.bancoibm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ibm.bancoibm.repository.JpaGenericRepositoryImpl;

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ibm.bancoibm.repository", repositoryBaseClass = JpaGenericRepositoryImpl.class)
public class BancoIbmApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoIbmApplication.class, args);
	}
}