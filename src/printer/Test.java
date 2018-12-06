package printer;

import java.io.FileNotFoundException;

public class Test {
	public static void main(String[] args) throws FileNotFoundException {
		FiFo fiFo = new FiFo();
		fiFo.simulate();
	}
}
