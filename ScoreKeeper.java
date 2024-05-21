package PongV2;

public class ScoreKeeper {
	int p1_score = 0;
	int p2_score = 0;
	String score = p1_score + " " + p2_score;
	Ball ball;

	public ScoreKeeper(Ball ball) {
		this.ball = ball;
	}

	public void run() {
		while (true) {
			// Scoring
			if (ball.getX() >= 700) {
				p1_score++;
				ball.resetPosition();
			}
			if (ball.getX() <= 0) {
				p2_score++;
				ball.resetPosition();
			}

			// Update the score string (optional)
			score = p1_score + " " + p2_score;

			// Add a short sleep to prevent the loop from running too fast
			try {
				Thread.sleep(50); // 50 milliseconds
			} catch (InterruptedException e) {
				// Handle the exception
			}
		}
	}
}
