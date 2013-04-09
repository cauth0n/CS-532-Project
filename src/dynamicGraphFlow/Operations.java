package dynamicGraphFlow;

import java.util.Random;

public abstract class Operations {
	protected final int maxFlow = 100;

	public int getRandomFlow() {
		Random rand = new Random();
		return rand.nextInt(maxFlow);
	}

}
