package br.com.netdeal.passwordmatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.netdeal.passwordmatch.model.Match;
import br.com.netdeal.passwordmatch.service.MatchPasswordService;

@RestController
@RequestMapping("/match")
public class MatchController {
	
	
	@Autowired
	private MatchPasswordService matchService;

	@RequestMapping(value="/check/{password}")
	public ResponseEntity<Match> check(@PathVariable("password") String password){
		
		System.out.println("pass:" + password);
		Match m = this.matchService.matchPassword(password);
		
		return new ResponseEntity<Match>(m, HttpStatus.OK);
	}
	
}
