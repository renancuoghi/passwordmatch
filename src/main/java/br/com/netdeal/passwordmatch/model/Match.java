package br.com.netdeal.passwordmatch.model;

public class Match {

	private Integer score;
	private Complexity complexity;
	
	
	public Match(){
		
	}
	
	public Match(Integer score,Complexity complexity){
		this.score = score;
		this.complexity = complexity;
	}
	
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}

	public Complexity getComplexity() {
		return complexity;
	}

	public void setComplexity(Complexity complexity) {
		this.complexity = complexity;
	}
	
	
	
	
}
