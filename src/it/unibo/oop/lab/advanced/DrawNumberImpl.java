package it.unibo.oop.lab.advanced;

import java.util.Random;

/**
 *
 */
public final class DrawNumberImpl implements DrawNumber {

    private int choice;
    private final int min;
    private final int max;
    private final int attempts;
    private int remainingAttempts;
    private final Random random = new Random();

    /**
     * @param configuration
     */
    public DrawNumberImpl(final Configuration configuration) {
        if (!configuration.isConsistent()) {
            throw new IllegalArgumentException("the game requires a valid configuration!");
        }
        this.min = configuration.getMin();
        this.max = configuration.getMax();
        this.attempts = configuration.getAttempts();
        this.reset();
    }

    @Override
    public void reset() {
        this.remainingAttempts = this.attempts;
        this.choice = this.min + random.nextInt(this.max - this.min + 1);
    }


    @Override
    public DrawResult attempt(final int n) throws AttemptsLimitReachedException {
        if (this.remainingAttempts <= 0) {
            throw new AttemptsLimitReachedException();
        }
        if (n < this.min || n > this.max) {
            throw new IllegalArgumentException("The number is outside boundaries");
        }
        remainingAttempts--;
        if (n > this.choice) {
            return DrawResult.YOURS_HIGH;
        }
        if (n < this.choice) {
            return DrawResult.YOURS_LOW;
        }
        return DrawResult.YOU_WON;
    }

}
