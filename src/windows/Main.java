package windows;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		new HomeWindow();
	}
	
	//restart method
	public static void restart() {
		SwingUtilities.invokeLater(() -> Main.main(new String [] {}));
	}
}