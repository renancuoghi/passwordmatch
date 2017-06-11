package br.com.netdeal.passwordmatch.service.helper;

public class MatchSymbolNumericLowerUpperCase {

	private Boolean debug = false;
	private String password;
	
	private Integer nConsecCharType = 0;
	private Integer midChar = 0;
	
	private Integer minPasswordLength = 8; 
	private final Integer MULT_LENGTH = 4;
	// helper Uppercase
	private Integer nTmpAlpchaUC = 0;
	private Integer alphaUC = 0;
	private Integer nConsecAlphaUC = 0;
	private final Integer MULT_CONSEC_ALPHA_UC = 2;
	// helper lowercase
	private Integer nTmpAlpchaLC = 0;
	private Integer alphaLC = 0;
	private Integer nConsecAlphaLC = 0;
	private final Integer MULT_CONSEC_ALPHA_LC = 2;
	// helper number
	private Integer nTmpNumber = 0;
	private Integer number = 0;
	private Integer nConsecNumber = 0;
	private final Integer MULT_CONSEC_NUMBER = 2;
	private final Integer MULT_SEQ_NUMBER = 3;
	// helper Symbol
	private Integer nTmpSymbol = 0;
	private Integer symbol = 0;
	private Integer nConsecSymbol = 0;
	private final Integer MULT_SEQ_SYMBOL = 3;
	// helper rept
	private Double nRepInc = 0D;
	private Integer nRepChar = 0; 
	private Integer nUnqChar = 0;
	
	// helper Seq Alpha
	private Integer nSeqAlpha = 0;
	private Integer nSeqNumber = 0;
	private Integer nSeqSymbol = 0;
	private Integer nSeqChar = 0;
	private final Integer MULT_SEQ_ALPHA = 3;
	
	// helper requirements
	private Integer nReqChar = 0;
	private Integer nMinReqChars = 3;
	
	private Integer score = 0;
	private Integer maxScore = 100;
	private Integer minScore = 0;
	
	private final String ALPHAS = "abcdefghijklmnopqrstuvwxyz";
	private final String NUMERICS = "01234567890";
	private final String SYMBOLS = ")!@#$%^&*()";
	
	private final Integer MULT_NUMBER = 4;
	private final Integer MULT_SYMBOL = 6;
	private final Integer MULT_CHAR = 2;
	
	public MatchSymbolNumericLowerUpperCase(String password){
		this.password = password;	
	}
	
	public Integer generateScore(){
		this.matchLength();
		String[] arrPwd = this.password.replaceAll("\\s+", "").split("\\s*");
		
		for(int i=0;i<arrPwd.length;i++){
			this.matchUpperCase(arrPwd, i);
			this.matchLowerCase(arrPwd, i);
			this.matchNumber(arrPwd, i);
			this.matchSymbol(arrPwd, i);
			this.matchRepeatCharacters(arrPwd, i);
		}
		
		this.nSeqAlpha = this.matchSequential(this.ALPHAS);
		this.nSeqNumber = this.matchSequential(this.NUMERICS);
		this.nSeqSymbol = this.matchSequential(this.SYMBOLS);
	
		this.matchRequirements();
	
		this.calculateScore();
		return this.score;
	}
	
	private void calculateScore(){
		
		
		
		this.score += this.calculaAlphaUpperCaseScore();
		this.score += this.calculaAlphaLowerCaseScore();
		this.score += this.calculaNumberScore();		
		this.score += this.calculaSymbolScore();
		this.score += this.calculaMiddleCharScore();
		this.score += this.calculaRequirementsScore();
		this.score -= this.calculaOnlyLettersScore();
		this.score -= this.calculaOnlyNumbersScore();
		this.score -= this.calculaRepCharsScore();
		this.score -= this.calculaAlphaUpperConsecutivoScore();
		this.score -= this.calculaAlphaLowerConsecutivoScore();
		this.score -= this.calculaNumberConsecutivoScore();
		this.score -= this.calculaSequencialAlphaScore();
		this.score -= this.calculaSequencialNumberScore();
		this.score -= this.calculaSequencialSymbolScore();
		
						

		if(this.score > this.maxScore){
			this.score = this.maxScore;
		}
		
		if(this.score < this.minScore){
			this.score = this.minScore;
		}
		
		
	}

	public Integer calculaAlphaUpperCaseScore(){
		Integer passTam = this.password.length();
		Integer subScore = 0;
		if(this.alphaUC > 0 && this.alphaUC < passTam){
			if(this.debug == true)
				System.out.println("UC: " + (passTam - this.alphaUC) * 2);
			subScore = (passTam - this.alphaUC) * 2;
		}
		return subScore;
	}
	
	public Integer calculaAlphaLowerCaseScore(){
		Integer passTam = this.password.length();
		Integer subScore = 0;
		if (this.alphaLC > 0 && this.alphaLC < passTam) {
			if(this.debug == true)
				System.out.println("LC: " + (passTam - this.alphaLC) * 2);
			subScore = (passTam - this.alphaLC) * 2; 
		}
		return subScore;
	}
	
	public Integer calculaNumberScore(){
		Integer passTam = this.password.length();
		Integer subScore = 0;
		if (this.number > 0 && this.number < passTam) {

			if(this.debug == true)
				System.out.println("NUMBER: " + this.number * this.MULT_NUMBER);
			subScore = this.number * this.MULT_NUMBER;
		}
		return subScore;
	}
	
	public Integer calculaSymbolScore(){
		Integer subScore = 0;
		if (this.symbol > 0) {
			if(this.debug == true)
				System.out.println("SYMBOL: " + this.symbol * this.MULT_SYMBOL);
			subScore = this.symbol * this.MULT_SYMBOL;
		}
		return subScore;
	}
	
	public Integer calculaMiddleCharScore(){
		Integer subScore = 0;
		if(this.midChar > 0){
			if(this.debug == true)
				System.out.println("MID: " + this.midChar * this.MULT_CHAR );
			subScore = this.midChar * this.MULT_CHAR;
		}
		return subScore;
	}
	
	public Integer calculaOnlyLettersScore(){
		Integer passTam = this.password.length();
		Integer subScore = 0;
		if((this.alphaLC >0 || this.alphaUC >0) && this.symbol == 0 && this.number == 0){ // somente letras
			if(this.debug == true)
				System.out.println("ONLY LETTERS: -" + (this.score - passTam));
			subScore = passTam;
		}
		return subScore;
	}
	
	
	public Integer calculaOnlyNumbersScore(){
		Integer passTam = this.password.length();
		Integer subScore = 0;
		if(this.alphaLC == 0 && this.alphaUC == 0 && this.symbol == 0 && this.number > 0){ // somente numeros
			if(this.debug == true)
				System.out.println("ONLY NUMBERS: -" + (this.score - (this.score - passTam)));
			subScore = passTam;
		}
		return subScore;
	}
	
	
	public Integer calculaRepCharsScore(){
		Integer subScore = 0;
		// caracteres reptidos
		if(this.nRepInc > 0){
			if(this.debug == true)
				System.out.println("Rep Case Insentive: -" + this.nRepInc.intValue());
			subScore = this.nRepInc.intValue();
		}
		return subScore;
	}
	
	
	public Integer calculaAlphaUpperConsecutivoScore(){
		Integer subScore = 0;
		// caracteres reptidos
		if(this.nConsecAlphaUC > 0){
			if(this.debug == true)
				System.out.println("Consec UpperCase: -" + (this.nConsecAlphaUC * this.MULT_CONSEC_ALPHA_UC));
			subScore = (this.nConsecAlphaUC * this.MULT_CONSEC_ALPHA_UC);
		}
		return subScore;
	}	
	
	public Integer calculaAlphaLowerConsecutivoScore(){
		Integer subScore = 0;
		// caracteres reptidos
		if(this.nConsecAlphaLC > 0){
			if(this.debug == true)
				System.out.println("LowerCase UpperCase: -" + (this.nConsecAlphaLC * this.MULT_CONSEC_ALPHA_LC));
			subScore = this.nConsecAlphaLC * this.MULT_CONSEC_ALPHA_LC;
		}
		return subScore;
	}
	
	public Integer calculaNumberConsecutivoScore(){
		Integer subScore = 0;
		if(this.nConsecNumber > 0){		
			if(this.debug == true)
				System.out.println("CONSEC NUMBER: -" + (this.nConsecNumber * this.MULT_CONSEC_NUMBER));
			subScore = this.nConsecNumber * this.MULT_CONSEC_NUMBER;
		}
		return subScore;
	}
	
	public Integer calculaSequencialAlphaScore(){
		Integer subScore = 0;
		if(this.nSeqAlpha > 0){
			if(this.debug == true)
				System.out.println("SEQ ALPHA: -" + (this.nSeqAlpha * this.MULT_SEQ_ALPHA));
			subScore = this.nSeqAlpha * this.MULT_SEQ_ALPHA;
		}
		return subScore;
	}
		
	public Integer calculaSequencialNumberScore(){
		Integer subScore = 0;
		if(this.nSeqNumber > 0){
			if(this.debug == true)
				System.out.println("SEQ NUMBER: -" + ( this.nSeqNumber * this.MULT_SEQ_NUMBER));
			subScore = this.nSeqNumber * this.MULT_SEQ_NUMBER;
		}
		return subScore;
	}		
			
	public Integer calculaSequencialSymbolScore(){
		Integer subScore = 0;
		if(this.nSeqSymbol > 0){
			if(this.debug == true)
				System.out.println("SEQ SYMBOL: -" + ( this.nSeqSymbol * this.MULT_SEQ_SYMBOL));
			subScore = this.nSeqSymbol * this.MULT_SEQ_SYMBOL;
		}
		return subScore;
	}			
			
	public Integer calculaRequirementsScore(){
		Integer subScore = 0;
		if(this.nReqChar > this.nMinReqChars){
			if(this.debug == true)
				System.out.println("REQUIREMENTS: +" + ( this.nReqChar * 2));
			subScore = nReqChar * 2;
		}
		return subScore;
	}			
			
			
			
	
			
	
	private void matchLength(){
		if(this.debug == true)
			System.out.println("Length: " +  this.password.length() * this.MULT_LENGTH);
		this.score += this.password.length() * this.MULT_LENGTH;
		
	}
	
	private void matchUpperCase(String[] arrPwd,Integer pos){
		if(arrPwd[pos].matches("[A-Z]")){
			
			if ((this.nTmpAlpchaUC + 1) == pos) { 
				this.nConsecAlphaUC++; 
				this.nConsecCharType++; 
			} 
			
			this.nTmpAlpchaUC = pos;
			this.alphaUC++;
		}
	}
	
	
	private void matchLowerCase(String[] arrPwd,Integer pos){
		if(arrPwd[pos].matches("[a-z]")){
			
			if ((this.nTmpAlpchaLC + 1) == pos) { 
				this.nConsecAlphaLC++; 
				this.nConsecCharType++; 
			} 
			
			this.nTmpAlpchaLC = pos;
			this.alphaLC++;
		}
	}
	
	
	private void matchNumber(String[] arrPwd,Integer pos){
		if(arrPwd[pos].matches("[0-9]")){
			if(pos > 0 && pos < (arrPwd.length - 1)){
				this.midChar++;
			}
			 
			if ((this.nTmpNumber + 1) == pos) { 
				this.nConsecNumber++; 
				this.nConsecCharType++; 
			} 
			
			
			this.nTmpNumber = pos;
			this.number++;
		}
		
	}
	
	private void matchSymbol(String[] arrPwd,Integer pos){
		if(arrPwd[pos].matches("[^a-zA-Z0-9_]")){
			if(pos > 0 && pos < (arrPwd.length - 1)){
				this.midChar++;
			}
			
			if ((this.nTmpSymbol + 1) == pos) { 
				this.nConsecSymbol++; 
				this.nConsecCharType++; 
			} 
			
			this.nTmpSymbol = pos;
			this.symbol++;
		}
	}
	
	private void matchRepeatCharacters(String[] arrPwd,Integer pos){
		Boolean charExists = false;
		for (Integer b=0; b < arrPwd.length; b++) {
			if(arrPwd[pos].equals(arrPwd[b]) && b != pos){
				charExists = true;
				/**
				 * Calcula de incremento baseado na proximidade de caracteres identicos
				 */
				Double bPos = (double)(b-pos);
				Double a = ((double)arrPwd.length/bPos);
				this.nRepInc += Math.abs(a);
			}
		}
		if (charExists){		
			this.nRepChar++;
			this.nUnqChar =  arrPwd.length - this.nRepChar;
			this.nRepInc = ((this.nUnqChar > 0) ? Math.ceil(this.nRepInc/this.nUnqChar) : Math.ceil(this.nRepInc));
		}
	}
	
	private Integer matchSequential(String sequencial){
		Integer countSeq = 0;
		for(int s = 0;s < sequencial.length();s++){
			String sFwd = "";
			if(s+3 < sequencial.length()){
				sFwd = sequencial.substring(s, s+3);
			}else{
				sFwd = sequencial.substring(s);
			}
			String sRev = new StringBuilder(sFwd).reverse().toString();
			if(this.password.toLowerCase().indexOf(sFwd) != -1 || this.password.toLowerCase().indexOf(sRev) != -1){
				countSeq++;
				this.nSeqChar++;
			}
		}
		return countSeq;
	}
	
	private void matchRequirements(){
		Integer[] reqValues = {this.password.length(),this.alphaUC,this.alphaLC,this.number,this.symbol};
		String[] reqKeys = {"length","alphauc","alphalc","number","symbol"};
		for(int c = 0; c < reqValues.length;c++){
			Integer minVal = 0;
			
			if(reqKeys[c].equals("length")){
				minVal = this.minPasswordLength -1;
			}
			if(reqValues[c] >= (minVal + 1)){
				this.nReqChar++;
			}

			
		}	
		this.nMinReqChars = (this.password.length() >= this.minPasswordLength) ? 3 : 4; 		
	}
	
	/**
	 * Numero minimo de caracteres de um password
	 * DEFAULT = 8	
	 * @param length
	 */
	public void setMinPasswordLength(Integer length){
		this.minPasswordLength = length;
	}
	/**
	 * Ativa modo debug printando os resultados
	 * @param debug
	 */
	public void isDebug(Boolean debug){
		this.debug = debug;
	}
	/**
	 * Um score pode set > 100, este metodo seta o valor maximo
	 * @param maxScore
	 */
	public void setMaxScoreValue(Integer maxScore){
		this.maxScore = maxScore;
	}
	/**
	 * Um score pode ser negativo, este metodo seta o valor minimo
	 * DEFAULT = 0
	 * @param Integer minScore
	 */
	public void setMinScoreValue(Integer minScore){
		this.minScore = minScore;
	}
}
