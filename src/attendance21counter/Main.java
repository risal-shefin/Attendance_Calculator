package attendance21counter;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		UI frame = new UI();
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
	}

}
