package attendance21;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class ConfigMain {
	
	private static Integer num = 0;
	
	private ConfigMain() throws Exception {
		String input = JOptionPane.showInputDialog("Total number of Courses: ");
		try {
			num = Integer.parseInt(input);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Please fill up with valid integer!", "Warning", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		File direx = new File("data");
		if(direx.exists())
			JOptionPane.showMessageDialog(null, "It will delete all of your previous data!");
		String courseNames[] = new String[num + 5];
		String classNum[] = new String[num + 5];
		JFrame frame = new JFrame("Configuration");
		JLabel pic = new JLabel(new ImageIcon(getClass().getResource("/resources/configMain.jpg")));
		frame.setContentPane(pic);
		JTextField courses[] = new JTextField[num + 5];
		JTextField classnum[] = new JTextField[num + 5];
		JLabel head1 = new JLabel("Course Names");
		head1.setForeground(Color.WHITE);
		JLabel head2 = new JLabel("Number of classes");
		head2.setForeground(Color.WHITE);
		head1.setBounds(100, 15, 200, 30);
		head2.setBounds(300, 15, 200, 30);
		head1.setFont(new Font("Times New Roman", Font.BOLD, 21));
		head2.setFont(new Font("Times New Roman", Font.BOLD, 21));
		frame.add(head1);
		frame.add(head2);
		int y = 20;
		for(int i = 1; i <= num; i++) {
			y += 50;
			courses[i] = new JTextField();
			courses[i].setBounds(70, y, 200, 30);
			frame.add(courses[i]);
			classnum[i] = new JTextField();
			classnum[i].setBounds(350, y, 50, 30);
			frame.add(classnum[i]);
		}
		
		JButton OK = new JButton("OK");
		OK.setBounds(250, y + 100, 60, 30);
		OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean mark = true;
				for(int i = 1; i <= num; i++) {
					courseNames[i] = courses[i].getText();
					classNum[i] = classnum[i].getText();
					try {
						Integer.parseInt(classNum[i]);
					} catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Please fill up with valid data!", "Warning", JOptionPane.WARNING_MESSAGE);
						mark = false;
						break;
					}
					if(courseNames[i].length() == 0 || classNum[i].length() == 0) {
						JOptionPane.showMessageDialog(null, "Please fill up all the fields !", "Warning", JOptionPane.WARNING_MESSAGE);
						mark = false;
						break;
					}
				}
				
				if(mark) {
					if((new File("Data") ).exists()) {
						try {
							new ClearData().clear(new File("Data"));
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "File or Folders can't be deleted properly!", "Warning", JOptionPane.WARNING_MESSAGE);
							e1.printStackTrace();
						}
					}
					String dir = "data" + File.separator + "Courses";
					String dir2 = "data" + File.separator + "Total";
					try {
						File makeDir = new File(dir) ;
						File makeDir2 = new File(dir2);
						makeDir.mkdirs();
						makeDir2.mkdirs();
						FileOutputStream fout = new FileOutputStream(new File(dir + File.separator + "Course_Names.txt"));
						FileOutputStream foutNum = new FileOutputStream(new File(dir + File.separator + "Course_Nums.txt"));
						foutNum.write(num.toString().getBytes());
						foutNum.close();
						for(Integer i = 1; i <= num; i++) {
							fout.write(courseNames[i].getBytes());
							if(i != num)
								fout.write(System.lineSeparator().getBytes());
							
							FileOutputStream ftmp = new FileOutputStream(new File(dir + File.separator + i.toString() + ".txt") );
							FileOutputStream ftmp2 = new FileOutputStream(new File(dir2 + File.separator + i.toString() + ".txt") );
							ftmp.write(classNum[i].getBytes());
							Integer temp = 0;
							ftmp2.write(temp.toString().getBytes());
							ftmp.close();
							ftmp2.close();
						}
						fout.close();
						JOptionPane.showMessageDialog(null, "Courses are added successfully!");
						new DayConfigs(courseNames, num);
						frame.dispose();
					} catch(Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Course names aren't found");
					}
				}
			}
		});
		frame.add(OK);
		frame.setLayout(null);
		frame.setSize(500, y + 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)  throws Exception {
		new ConfigMain();
	}

}
