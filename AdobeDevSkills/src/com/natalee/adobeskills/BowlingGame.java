package com.natalee.adobeskills;


/**
 * This class represents a game of bowling. It keeps track of 
 * the score, whether or not a game is finished, and how many pins
 * are knocked down by a ball.
 * 
 * Bowling scoring rules:
 * 	If strike at frame[i], then score[i] = 10 + score[i+1] + score[i+2] (original score <= 10)
 * 	If spare at frame[i], then score[i] = 10 + score[i+1]
 * 	If 10 pins in 10th frame, bowler allowed 3 balls for that frame, for potential
 * 		of 12 strikes in a game
 * 	Else, player is awarded number of pins contributing to the total score 
 * 
 * @author Natalee Villa
 * @version March 2015
 *
 */
public class BowlingGame {
	
	
	private int[] mPinScores;				// keeps track of score per ball rolled,
											// so pins knocked down at frameX = mPinScores[2X] + mPinScores[2X+1]
											// this is 21 total rolls - 2*9 frames + 3 for the 10th frame

	private int mRollNumber;				// position to update roll array
	
											// frameIdx in [0,9]
											// frameIdx = mRollNumber / 2, except for roll 20
	
	// constructor
	public BowlingGame() {
		super();
		this.mPinScores = new int[21];
		this.mRollNumber = 0;
	}
	

	/**
	 * 
	 * @param pins	The number of pins knocked down by a player in a throw. 
	 * 	Parameter must be in range [0,10].
	 * @throws IllegalArgumentException	
	 */
	public void roll(int pins) throws IllegalArgumentException  {
		// pins cannot be less than or greater than 10;
		// also 2 rolls cannot add up to be more than 10 except for 10th frame		
		if (pinsAreInvalid(pins)) {
			throw new IllegalArgumentException("Invalid input.");
		}
			
		// don't let a bowler bowl more than their turns
		if (this.mRollNumber > 20)
			return;
			
		this.mPinScores[mRollNumber] = pins;
		
		// if frame was strike, advance pointer 2 to next bowling frame, unless 10th frame
		// else advance 1
		if (this.mRollNumber % 2 == 0 && pins == 10 && this.mRollNumber < 18) {
			this.mRollNumber++;
		} 
		
		// advance pointer to rolls
		this.mRollNumber++;					
	}
	
	// helper function for 'roll()' to ensure roll is valid
	private boolean pinsAreInvalid(int pins) {
		// pins cannot be less than or greater than 10;
		if (pins < 0 || pins > 10) {
			return true;
		}
		
		// also 2 rolls cannot add up to be more than 10 except for 10th frame
		if (this.mRollNumber % 2 == 1 && (pins + this.mPinScores[mRollNumber-1] > 10) && this.mRollNumber < 18) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return Returns the current score for a bowling game
	 */
	public int getScore() {
		int score = 0;
		for (int frameNum=0; frameNum < 10; frameNum++) {
			// if strike, add next two frames
			// if spare, add next frame
			// else just add the number of pins knocked down
			if (frameIsStrike(frameNum)) {
				score += framePointsForStrike(frameNum);
			} else if (frameIsSpare(frameNum)) {
				score += framePointsForSpare(frameNum);
			} else {
				score += pinsForFrame(frameNum);
			}
		}
		return score;
	}
	
	/**
	 * @return Returns true if a bowling game is done; 
	 * 	if a player has no more turns left in the game
	 */
	public boolean isFinished() {		
		// if mBallPosition < 20, turns are left since pointer advances after roll
		if (this.mRollNumber < 20)
			return false;
		
		// if bowler gets strike or spare in the 10th frame of 2 bowls, 
		// a bonus roll is awarded 
		if (this.mRollNumber == 20 && (frameIsStrike(9) || frameIsSpare(9)))
			return false;
		
		return true;
	}
	
	
	/**
	 * @param frameIdx
	 * @return Return total pins knocked down in frame
	 */
	private int pinsForFrame(int frameIdx) {
		// if last frame, add score for 3 rolls
		// else add scores for 2 rolls
		int pins = this.mPinScores[frameIdx*2] + this.mPinScores[frameIdx*2 + 1];
		if (frameIdx == 9) 
			return pins + this.mPinScores[frameIdx*2 + 2];
		
		return pins;
	}
	
	/**
	 * @param frameIdx	The frame index
	 * @return Return true if 10 pins are struck in first roll of frameIdx 
	 */
	private boolean frameIsStrike(int frameIdx) {		
		if (this.mPinScores[frameIdx*2] == 10) {
			return true;
		} 
		return false;
	}
	
	/**
	 * @param frameIdx	The frame index
	 * @return Return true if 10 pins are struck in the frame and not a strike
	 */
	private boolean frameIsSpare(int frameIdx) {
		if (!frameIsStrike(frameIdx) && pinsForFrame(frameIdx) == 10) {
			return true;
		} 
		return false;
	}

	/**
	 * @param frameIdx
	 * @return	The points for the frame if the frame was a strike.
	 */
	private int framePointsForStrike(int frameIdx) {
		// if frame is 8 or 9, get points for rolls of 10th frame, not 10th frame itself
		// 10th frame consists of roll indices 18,19,20
		if (frameIdx == 7)
			return pinsForFrame(frameIdx) + pinsForFrame(frameIdx+1) + this.mPinScores[18];
		else if (frameIdx == 8)
			return pinsForFrame(frameIdx) + this.mPinScores[18] + this.mPinScores[19];
		else if (frameIdx == 9)
			return pinsForFrame(frameIdx);
		else
			return pinsForFrame(frameIdx) + pinsForFrame(frameIdx+1) + pinsForFrame(frameIdx+2);
	}
	
	/**
	 * @param frameIdx
	 * @return The points for the frame if the frame was a spare.
	 */
	private int framePointsForSpare(int frameIdx) {
		// if frame is 9, get points for rolls of 10th frame, not 10th frame itself
		// 10th frame consists of roll indices 18,19,20
		if (frameIdx == 8)
			return pinsForFrame(frameIdx) + this.mPinScores[18];
		else if (frameIdx == 9)
			return pinsForFrame(frameIdx);
		else
			return pinsForFrame(frameIdx) + pinsForFrame(frameIdx+1);
	}
	
}

