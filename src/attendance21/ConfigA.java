package attendance21;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class ConfigA {
	
	public ConfigA(String courseNames[], Integer num) {
		JCheckBox check[] = new JCheckBox[num + 5];
		JButton OK = new JButton("OK");
		Integer y = 30;
		JLabel label = new JLabel("A DAY");
		label.setBounds(185, 5, 100, 30);
		label.setFont(new Font("Pristina", Font.BOLD, 25));
		label.setForeground(Color.WHITE);
		JLabel instruct = new JLabel("(Please tick the A Day's classes)");
		instruct.setBounds(130, 40, 300, 20);
		instruct.setForeground(Color.WHITE);
		JFrame frame = new JFrame();
		JLabel pic = new JLabel(new ImageIcon(getClass().getResource("/resources/DaysConfig.jpg")));
		frame.setContentPane(pic);
		frame.add(label);
		frame.add(instruct);
		
		for(int i = 1; i <= num; i++) {
			y += 50;
			check[i] = new JCheckBox(courseNames[i]);
			check[i].setBounds(180, y, 100, 40);
			check[i].setOpaque(false);
			check[i].setForeground(Color.white);
			frame.add(check[i]);
		}
		
		OK.setBounds(198, y + 100, 60, 30);
		OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dir = "data" + File.separator + "Courses";
				File makeDir = new File(dir);
				makeDir.mkdirs();
				try {
					FileOutputStream foutCourse = new FileOutputStream(new File(dir + File.separator + "A_Course.txt"));
				
					for(Integer j = 1; j <= 14; j++) {
						String dirtmp = "data" + File.separator + j.toString() + File.separator + "A";
						File makeDirtmp = new File(dirtmp);
						makeDirtmp.mkdirs();
						for(Integer i = 1; i <= num; i++) {
					
							if(check[i].isSelected()) {
								if(j == 1) {
									foutCourse.write(courseNames[i].getBytes());
									foutCourse.write(System.lineSeparator().getBytes());
								}
								FileOutputStream fout = new FileOutputStream(new File(dirtmp + File.separator + i.toString() + ".txt"));
								Integer temp = 0;
								fout.write(temp.toString().getBytes());
								fout.close();
							}
						}
					}
					foutCourse.close();
				}  catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Data can't be saved successfully!");
					frame.dispose();
				}
				JOptionPane.showMessageDialog(null, "Data Saved Successfully.");
				frame.dispose();
			}
		});
		
		frame.add(OK);
		frame.setLayout(null);
		frame.setSize(500, y + 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
