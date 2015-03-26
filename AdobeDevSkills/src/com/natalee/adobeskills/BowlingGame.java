package com.natalee.adobeskills;

/**
 * This class represents a game of bowling. It keeps track of the score, whether
 * or not a game is finished, and how many pins are knocked down by a ball.
 * 
 * Bowling scoring rules: 
 * 	If strike at frame[i], then score[i] = 10 + score[i+1]
 * 		+ score[i+2] (original score <= 10) -> next 2 rolls
 *  If 8th frame strike, add 9th frame and 1st bowl of 10th frame
 *  If 9th frame strike, add 1st 2 bowls of 10th frame
 * 
 * 	If spare at frame[i], then score[i] = 10
 * 		+ score[i+1] -> next roll
 *  If 10 pins in 10th frame, bowler allowed 3 balls for that frame,
 * 		for potential of 12 strikes in a game 
 *  If 9th frame spare, add 1st bowl of 10th frame
 * 
 * 	Else, player is awarded number of pins
 * 		contributing to the total score
 * 
 * @author Natalee Villa
 * @version March 2015
 * 
 */
public class BowlingGame {

	// keeps track of score per ball rolled,
	// so pins knocked down at frameX = mPinScores[2X] + mPinScores[2X+1]
	// this is 21 total potential rolls - 2*9 frames + 3 for the 10th frame
	private int[] mPinScores;

	// position to update roll array [0,20]
	private int mRollNumber;

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
	 * @param pins
	 *            The number of pins knocked down by a player in a throw.
	 *            Parameter must be in range [0,10].
	 * @throws IllegalArgumentException
	 */
	public void roll(int pins) throws IllegalArgumentException {
		// pins cannot be less than or greater than 10;
		// also 2 rolls cannot add up to be more than 10 except for 10th frame
		if (pinsAreInvalid(pins)) {
			throw new IllegalArgumentException("Invalid input.");
		}

		// don't let a bowler bowl if the game is done
		if (this.isFinished())
			return;

		mPinScores[mRollNumber] = pins;

		// if frame was strike and not 10th frame, advance pointer 2 to next bowling frame
		// else advance 1
		if (isBallOne() && pins == 10 && !isTenthFrame()) {
			mRollNumber++;
		}

		// advance pointer to rolls
		mRollNumber++;
	}

	// helper function for 'roll()' to ensure roll is valid
	private boolean pinsAreInvalid(int pins) {
		// pins cannot be less than or greater than 10;
		if (pins < 0 || pins > 10) {
			return true;
		}

		// also 2 rolls cannot add up to be more than 10 except for 10th frame
		if (!isBallOne()
				&& (pins + mPinScores[mRollNumber-1] > 10)
				&& !isTenthFrame()) {
			return true;
		}
		return false;
	}

	/**
	 * @return Returns the current score for a bowling game
	 */
	public int getScore() {
		int score = 0;
		for (int frameNum = 0; frameNum < 10; frameNum++) {
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
	 * @return Returns true if a bowling game is done; if a player has no more
	 *         turns left in the game
	 */
	public boolean isFinished() {
		// if mBallPosition < 20, turns are left since pointer advances after roll
		if (mRollNumber < 20)
			return false;

		// if bowler gets strike or spare in the 10th frame of 2 bowls,
		// a bonus roll is awarded
		if (mRollNumber == 20 && (frameIsStrike(9) || frameIsSpare(9)))
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
		int pins = mPinScores[frameIdx * 2]
				+ mPinScores[frameIdx * 2 + 1];
		if (frameIdx == 9)
			return pins + mPinScores[frameIdx * 2 + 2];

		return pins;
	}
	
	private boolean isBallOne() {
		return this.mRollNumber % 2 == 0;
	}
	
	private boolean isTenthFrame() {
		return this.mRollNumber >= 18;
	}

	/**
	 * @param frameIdx
	 *            The frame index
	 * @return Return true if 10 pins are struck in first roll of frameIdx
	 */
	private boolean frameIsStrike(int frameIdx) {
		if (mPinScores[frameIdx * 2] == 10) {
			return true;
		}
		return false;
	}

	/**
	 * @param frameIdx
	 *            The frame index
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
	 * @return The points for the frame if the frame was a strike.
	 */
	private int framePointsForStrike(int frameIdx) {
		// if frame is 8 or 9, get points for rolls of 10th frame
		// 10th frame consists of roll indices 18,19,20
		if (frameIdx == 8)
			return pinsForFrame(frameIdx) + mPinScores[18]
					+ mPinScores[19];
		if (frameIdx == 9)
			return pinsForFrame(frameIdx);
		// the next frame is strike, add next frame & first roll from frame after
		if (frameIsStrike(frameIdx+1)) 
			return pinsForFrame(frameIdx) + pinsForFrame(frameIdx+1) + mPinScores[(frameIdx+2)*2];
		// else next two rolls are next frame
		else
			return pinsForFrame(frameIdx) + pinsForFrame(frameIdx+1);
	}

	/**
	 * @param frameIdx
	 * @return The points for the frame if the frame was a spare.
	 */
	private int framePointsForSpare(int frameIdx) {
		// if frame is 9, get points for rolls of frame
		// else get points for frame & next roll
		if (frameIdx == 9)
			return pinsForFrame(frameIdx);
		else
			return pinsForFrame(frameIdx) + mPinScores[(frameIdx+1)*2];
	}

}
