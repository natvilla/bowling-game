package com.natalee.adobeskills;

import static org.junit.Assert.*;

import org.junit.Test;

public class BowlingTest {

	
	@Test
	public void testSampleData1() {
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		for (int i=0; i < 20; i++) {
			game.roll(0);
		}
		assertEquals(0, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testSampleData2() {
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		for (int i=0; i < 20; i++) {
			game.roll(4);
		}
		assertEquals(80, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testSampleData3() {
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		game.roll(9);
		game.roll(1);
		game.roll(9);
		for (int i=3; i < 20; i++) {
			game.roll(0);
		}
		
		assertEquals(28, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testSampleData4() {
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		game.roll(10);
		game.roll(9);
		for (int i=2; i < 19; i++) {
			game.roll(0);
		}
		
		assertEquals(28, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testSampleData5() {
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		game.roll(9);
		game.roll(1);
		game.roll(10);
		game.roll(9);
		for (int i=4; i < 19; i++) {
			game.roll(0);
		}
		
		assertEquals(48, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testSampleData6() {
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		game.roll(10);
		game.roll(9);
		game.roll(1);
		game.roll(9);
		for (int i=4; i < 19; i++) {
			game.roll(0);
		}
		
		assertEquals(48, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testSampleData7() { 
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		game.roll(10);
		game.roll(10);
		game.roll(9);
		for (int i=3; i < 18; i++) {
			game.roll(0);
		}
		
		assertEquals(57, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testSampleData8() { 
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		game.roll(10);
		game.roll(10);
		game.roll(10);
		for (int i=3; i < 17; i++) {
			game.roll(0);
		}
		
		assertEquals(60, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testSampleData9() { 
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		for (int i=0; i < 18; i++) {
			game.roll(0);
		}
		game.roll(9);
		game.roll(1);
		game.roll(9);
		
		assertEquals(19, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testSampleData10() { 
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		for (int i=0; i < 18; i++) {
			game.roll(0);
		}
		game.roll(10);
		game.roll(8);
		game.roll(1);
		
		assertEquals(19, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testSampleData11() { 
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		for (int i=0; i < 18; i++) {
			game.roll(0);
		}
		game.roll(10);
		game.roll(9);
		game.roll(1);
		
		assertEquals(20, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testSampleData12() { 
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		for (int i=0; i < 18; i++) {
			game.roll(0);
		}
		game.roll(9);
		game.roll(1);
		game.roll(10);
		
		assertEquals(20, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testPerfectGame() {
		// test rolling perfect game
		// test initial & final states 
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		for (int i=0; i < 12; i++) {
			game.roll(10);
		}
		assertEquals(300, game.getScore());
		assertEquals(true, game.isFinished());
	}
	
	@Test
	public void testGameNotDone() { 
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		for (int i=0; i < 18; i++) {
			game.roll(0);
		}
		assertEquals(false, game.isFinished());
		
		game.roll(9);
		game.roll(1);
		game.roll(10);
		
		assertEquals(20, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testGetBonusBallStrike() { 
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		for (int i=0; i < 18; i++) {
			game.roll(0);
		}

		game.roll(10);
		assertEquals(false, game.isFinished());
		game.roll(3);
		assertEquals(false, game.isFinished());
		game.roll(2);
		
		assertEquals(15, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testGetBonusBallSpare() { 
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		for (int i=0; i < 18; i++) {
			game.roll(0);
		}

		game.roll(2);
		assertEquals(false, game.isFinished());
		game.roll(8);
		assertEquals(false, game.isFinished());
		game.roll(2);
		
		assertEquals(12, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testThrowAfterDone() { 
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		for (int i=0; i < 50; i++) {
			game.roll(10);
		}
		assertEquals(300, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testThrowAfterDone2() { 
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		for (int i=0; i < 21; i++) {
			game.roll(4);
		}
		assertEquals(80, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test
	public void testLastFramesStrike() { 
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		
		int[] rolls = new int[]{10,7,3,9,0,10,0,8,8,2,0,6,10,10,10,8,1};
		
		for (int i=0; i < rolls.length; i++) {
			game.roll(rolls[i]);
		}
		assertEquals(167, game.getScore());
		assertEquals(true, game.isFinished());	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIncorrectPins1() { 
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
	
		game.roll(20);
	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIncorrectPins2() { 
		BowlingGame game = new BowlingGame();
		assertEquals(0, game.getScore());
		assertEquals(false, game.isFinished());
		game.roll(9);
		game.roll(9);
	
	}

}
