package br.com.netdeal.passwordmatch.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.com.netdeal.passwordmatch.controller.MatchController;
import br.com.netdeal.passwordmatch.service.MatchPasswordService;



@SpringBootApplication
@ComponentScan(basePackageClasses = {MatchController.class,MatchPasswordService.class})
public class Starter {
	
	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}
}
