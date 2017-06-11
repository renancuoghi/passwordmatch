package br.com.netdeal.passwordmatch.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.netdeal.passwordmatch.model.Match;
import br.com.netdeal.passwordmatch.service.MatchPasswordService;

@RestController
@RequestMapping("/match")
public class MatchRestController {
	
	
	@Autowired
	private MatchPasswordService matchService;

	@RequestMapping(value="/check")
	public ResponseEntity<Match> check(@RequestParam("password") String password){
		
		
		Match m = this.matchService.matchPassword(password);
		
		return new ResponseEntity<Match>(m, HttpStatus.OK);
	}
	
}
