package attendance21counter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Result extends JPanel {
	JButton button1 = new JButton("Percentage");
	Box vert = Box.createVerticalBox();
	
	public  Result() {
		vert.add(Box.createVerticalStrut(150));
		vert.add(button1);
		add(vert);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Percentage");
				
				try {
					String dir = "data" + File.separator + "Courses" + File.separator + "Course_Nums.txt";
					InputStream tmp = new FileInputStream(new File(dir));
					Scanner scr = new Scanner(tmp);
					String in = scr.nextLine();
					Integer num = Integer.parseInt(in);
					tmp.close();
					
					JLabel Label[] = new JLabel[num + 5];;
					Double s[] = new Double[num + 5];
					String dirName = "data" + File.separator + "Courses" + File.separator + "Course_Names.txt";
					InputStream courseName = new FileInputStream(dirName);
					Scanner scrName = new Scanner(courseName);
					for(Integer i = 1; i <= num; i++) {
						s[i] = 0.00;
						String dir1 = "data" + File.separator + "Total" + File.separator + i.toString() + ".txt";
						String dir2 = "data" + File.separator + "Courses" + File.separator + i.toString() + ".txt";
						InputStream fin = new FileInputStream(new File(dir1));
						InputStream fin2 = new FileInputStream(new File(dir2));
						scr = new Scanner(fin);
						s[i] = Double.parseDouble(scr.nextLine());
						scr = new Scanner(fin2);
						Double total = Double.parseDouble(scr.nextLine());
						s[i] = s[i] / total * 100.0;
						fin.close();
						fin2.close();
						Label[i] = new JLabel(scrName.nextLine());
					}
					
					Box v = Box.createVerticalBox();
					Box horizon[] = new Box[10];
					Box temp = Box.createHorizontalBox();
					
					JLabel lbl1 = new JLabel("Course No.");
					lbl1.setFont(new Font("Pristina", Font.BOLD, 25));
					Font font = lbl1.getFont();
					Map attributes = font.getAttributes();
					attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
					lbl1.setFont(font.deriveFont(attributes));
					
					JLabel lbl2 = new JLabel("Percentage");
					lbl2.setFont(new Font("Pristina", Font.BOLD, 25));
					font = lbl2.getFont();
					attributes = font.getAttributes();
					attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
					lbl2.setFont(font.deriveFont(attributes));
					
					temp.add(lbl1);
					lbl1.setForeground(Color.WHITE);
					temp.add(Box.createHorizontalStrut(50));
					temp.add(lbl2);
					lbl2.setForeground(Color.WHITE);
					v.add(Box.createVerticalStrut(20));
					v.add(temp);
					for(int i = 1; i <= num; i++) {
						horizon[i] = Box.createHorizontalBox();
						Label[i].setFont(new Font("Calibri", Font.BOLD, 14));
						Label[i].setForeground(Color.WHITE);
						horizon[i].add(Label[i]);
						horizon[i].add(Box.createHorizontalStrut(115));
						JLabel tmp2 = new JLabel(String.format("%.2f", s[i]) + " %");
						tmp2.setFont(new Font("Calibri", Font.BOLD, 14));
						tmp2.setForeground(Color.WHITE);
						horizon[i].add(tmp2);
						v.add(horizon[i]);
						v.add(Box.createVerticalStrut(20));
						if(i == 6)
							v.add(Box.createVerticalStrut(20));
					}
					
					JLabel pic = new JLabel(new ImageIcon(getClass().getResource("/resources/R.jpg")));
					frame.setContentPane(pic);
					frame.setLayout(new GridBagLayout());
					frame.add(v);
					frame.setVisible(true);
					frame.setSize(600, 500);
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
					
				} catch(IOException e2) {
					e2.printStackTrace();
				}
				
				
			}
		});
	
	}
	
	protected void paintComponent(Graphics g) {
		BufferedImage image = null;
		try{
			image = ImageIO.read(getClass().getResource("/resources/percentage.jpg"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}
}
