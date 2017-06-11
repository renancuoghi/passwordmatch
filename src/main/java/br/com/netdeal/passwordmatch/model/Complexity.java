package br.com.netdeal.passwordmatch.model;

public enum Complexity {
	MUITO_CURTO,MUITO_FRACO,FRACO,BOA,FORTE,MUITO_FORTE;
	
	public static Complexity getComplexityByScore(Integer score){
		if(score >= 80){
			return MUITO_FORTE;
		}else if(score >= 60 && score < 80){
			return FORTE;
		}else if(score >= 40 && score < 60){
			return BOA;
		}else if(score >= 20 && score < 40){
			return FRACO;
		}else{
			return MUITO_FRACO;
		}
	}
}
