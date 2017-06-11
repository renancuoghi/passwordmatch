package br.com.netdeal.passwordmatch.service;

import org.springframework.stereotype.Service;

import br.com.netdeal.passwordmatch.model.Complexity;
import br.com.netdeal.passwordmatch.model.Match;
import br.com.netdeal.passwordmatch.service.helper.MatchSymbolNumericLowerUpperCase;

@Service
public class MatchPasswordService {
	private Integer minPasswordLength = 8;
	private String password;
	private Integer score = 0;
	
	
	
	

	
	
	
	
	private MatchSymbolNumericLowerUpperCase msnlu;	
	
	public Match matchPassword(String password){
		
		this.password = password;
		
		this.msnlu = new MatchSymbolNumericLowerUpperCase(this.password);		
		this.msnlu.setMinPasswordLength(this.minPasswordLength);
		this.score = this.msnlu.generateScore();
		Match match = new Match();
		match.setScore(this.score);
		match.setComplexity(Complexity.GOOD);
		
		return match;
		
	}
	
	
	public Match matchPassword(String password,Boolean debug){
		
		this.password = password;
		
		this.msnlu = new MatchSymbolNumericLowerUpperCase(this.password);
		this.msnlu.isDebug(debug);
		this.msnlu.setMinPasswordLength(this.minPasswordLength);
		this.score = this.msnlu.generateScore();
		Match match = new Match();
		match.setScore(this.score);
		match.setComplexity(Complexity.GOOD);
		
		return match;
		
	}
	
	
	
	public void setMinPasswordLength(Integer length){
		this.minPasswordLength = length;
	}

}
