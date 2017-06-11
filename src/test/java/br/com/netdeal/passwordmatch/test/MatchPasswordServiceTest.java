package br.com.netdeal.passwordmatch.test;


import junit.framework.TestCase;

import org.junit.Test;


import br.com.netdeal.passwordmatch.model.Match;
import br.com.netdeal.passwordmatch.service.MatchPasswordService;


public class MatchPasswordServiceTest extends TestCase{

	private MatchPasswordService service;
	private Boolean debugAtivo = false;
	@Override
    protected void setUp() throws Exception
    {
        super.setUp();
        this.service = new MatchPasswordService();
        
    }
	
	@Test
	public void testOnlyNumber() {
		System.out.println("testOnlyNumber");
		
		
		Match m = this.service.matchPassword("12345678",this.debugAtivo);
		
		System.out.println("Score: " + m.getScore());
		System.out.println("------------------------------------------");
		
		assertTrue(m.getScore() == 4);
		
	}
	

	@Test
	public void testOnlyAlpha() {
		System.out.println("testOnlyAlpha");
	
		Match m = this.service.matchPassword("testando",this.debugAtivo);
		
		System.out.println("Score: " + m.getScore());
		System.out.println("------------------------------------------");
		
		assertTrue(m.getScore() == 9);
		
	}
	
	@Test
	public void testLettersAndNumber() {
		System.out.println("testLettersAndNumber");
	
		Match m = this.service.matchPassword("teste123",this.debugAtivo);
		
		System.out.println("Score: " + m.getScore());
		System.out.println("------------------------------------------");
		assertTrue(m.getScore() == 38);
		
	}
	
	@Test
	public void testLettersNumbersMiddle() {
		System.out.println("testLettersNumbersMiddle");
		
		Match m = this.service.matchPassword("tes333te123",this.debugAtivo);
		
		System.out.println("Score: " + m.getScore());
		System.out.println("------------------------------------------");
		assertTrue(m.getScore() == 70);
		
	}
	
	@Test
	public void testLettersNumbersMiddleAndSpecialChars() {
		System.out.println("testLettersNumbersMiddleAndSpecialChars");
		
		Match m = this.service.matchPassword("tes#333*te123@",this.debugAtivo);
		
		System.out.println("Score: " + m.getScore());
		System.out.println("------------------------------------------");
		assertTrue(m.getScore() == 100);
		
	}
	
	
	@Test
	public void testLettersUpperLowerNumbersMiddleAndSpecialChars() {
		System.out.println("testLettersUpperLowerNumbersMiddleAndSpecialChars");
	
		Match m = this.service.matchPassword("teRs#333*teB123@",this.debugAtivo);
		
		System.out.println("Score: " + m.getScore());
		System.out.println("------------------------------------------");
		assertTrue(m.getScore() == 100);
		
	}
	
	
	@Test
	public void testStrongPass() {
		System.out.println("testStrongPass");

		Match m = this.service.matchPassword("cha_P0EU**10",this.debugAtivo);
		
		System.out.println("Score: " + m.getScore());
		System.out.println("------------------------------------------");
		assertTrue(m.getScore() == 100);
		
	}

	
	@Test
	public void testVeryWeakPass() {
		System.out.println("testVeryWeakPass");

		Match m = this.service.matchPassword("a",this.debugAtivo);
		
		System.out.println("Score: " + m.getScore());
		System.out.println("------------------------------------------");
		assertTrue(m.getScore() == 3);
	}
	
	@Test
	public void testVeryWeaZerokPass() {
		System.out.println("testVeryWeaZerokPass");
	
		Match m = this.service.matchPassword("aaa",this.debugAtivo);
		
		System.out.println("Score: " + m.getScore());
		System.out.println("------------------------------------------");
		assertTrue(m.getScore() == 0);
		
	}
	
	
	@Test
	public void testMixPass() {
		System.out.println("testMixPass");

		Match m = this.service.matchPassword("aaa123",this.debugAtivo);
		
		System.out.println("Score: " + m.getScore());
		System.out.println("------------------------------------------");
		assertTrue(m.getScore() == 30);
		
	}
	
	@Test
	public void testComplextLongPass() {
		System.out.println("testComplextLongPass");

		Match m = this.service.matchPassword("aaad@asd@$$#14LXoplvcxxz",true);
		
		System.out.println("Score: " + m.getScore());
		System.out.println("------------------------------------------");
		assertTrue(m.getScore() == 100);
		
	}
	
}
