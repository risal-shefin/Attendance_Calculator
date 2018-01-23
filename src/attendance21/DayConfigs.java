package attendance21;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DayConfigs {
	public DayConfigs(String courseNames[], Integer num) {
		JButton button[] = new JButton[7];
		button[1] = new JButton("Configure A Day");
		button[2] = new JButton("Configure B Day");
		button[3] = new JButton("Configure C Day");
		button[4] = new JButton("Configure D Day");
		button[5] = new JButton("Configure E Day");
		
		button[1].setBounds(150, 50, 200, 50);
		button[2].setBounds(150, 150, 200, 50);
		button[3].setBounds(150, 250, 200, 50);
		button[4].setBounds(150, 350, 200, 50);
		button[5].setBounds(150, 450, 200, 50);
		
		JFrame frame = new JFrame("Configuration of days");
		JLabel pic = new JLabel(new ImageIcon(getClass().getResource("/resources/configDays.jpg")));
		frame.setContentPane(pic);
		for(int i = 1; i <= 5; i++) {
			frame.add(button[i]);
			
			button[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == button[1])
						new ConfigA(courseNames, num);
					else if(e.getSource() == button[2])
						new ConfigB(courseNames, num);
					else if(e.getSource() == button[3])
						new ConfigC(courseNames, num);
					else if(e.getSource() == button[4])
						new ConfigD(courseNames, num);
					else if(e.getSource() == button[5])
						new ConfigE(courseNames, num);
				}
			});
		}
		
		frame.setLayout(null);
		frame.setSize(500, 700);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
