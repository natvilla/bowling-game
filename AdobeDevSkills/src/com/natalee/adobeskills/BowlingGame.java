package com.natalee.adobeskills;


/**
 * This class represents a game of bowling. It keeps track of 
 * the score, whether or not a game is finished, and how many pins
 * are knocked down by a ball.
 * 
 * Note to self, Keep in mind: Possible 12 frames if strikes, else 10
 * 
 * @author Natalee Villa
 * @version March 2015
 *
 */
public class BowlingGame {
	
	// keeps track of the total number of pins knocked down
	// keep array of frames instead...
	private int totNumPins;

	// constructor
	public BowlingGame() {
		super();
		this.totNumPins = 0;
	}
	

	/**
	 * 
	 * @param pins
	 * @throws IllegalArgumentException
	 */
	public void roll(int pins) throws IllegalArgumentException  {
		if (pins > 10 || pins < 0) {
			throw new IllegalArgumentException("Invalid input.");
		}
		
		this.totNumPins += pins;
	
	}
	
	
	// need to update previous scores if strikes on next 2 turns,
	// same with spares
	
	


	
	
}

