package com.natalee.adobeskills;

import static org.junit.Assert.*;

import org.junit.Test;

public class BowlingTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSampleData() {
		BowlingGame game = new BowlingGame();
		
		// test rolling perfect game
		for (int i=0; i < 12; i++) {
			game.roll(10);
		}
		assertEquals(300, game.getScore());
		
	}

}
