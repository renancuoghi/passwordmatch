package br.com.netdeal.passwordmatch.model;

public enum Complexity {
	TOO_SHORT,VERY_WEAK,WEAK,GOOD,STRONG,VERY_STRONG;
	
	public static Complexity getComplexityByScore(Integer score){
		if(score >= 80){
			return VERY_STRONG;
		}else if(score >= 60 && score < 80){
			return STRONG;
		}else if(score >= 40 && score < 60){
			return GOOD;
		}else if(score >= 20 && score < 40){
			return WEAK;
		}else{
			return VERY_WEAK;
		}
	}
}
