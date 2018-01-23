package attendance21counter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class D extends JPanel {
	private Integer num = 0;
	private JCheckBox check[] = new JCheckBox[100];
	
	public D() {
		try {
			String dir = "data" + File.separator + "Courses" + File.separator + "Course_Nums.txt";
			InputStream tmp = new FileInputStream(new File(dir));
			Scanner scr = new Scanner(tmp);
			String in = scr.nextLine();
			num = Integer.parseInt(in);
			tmp.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error Occurred in D Day!", "Warning", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		
		Box vb = Box.createVerticalBox();
		vb.add(Box.createVerticalStrut(70));
		String name[] = new String[num + 5];
		
		try {
			String dir = "data" + File.separator + "Courses" + File.separator + "D_Course.txt";
			InputStream tmp = new FileInputStream(new File(dir));
			Scanner scr = new Scanner(tmp);
			String input;
			for(Integer j = 1; j <= num; j++) {
				String dirtmp = "data" + File.separator + UI.comboState + File.separator + "D" + File.separator + j.toString() + ".txt";
				File fileCheck = new File(dirtmp);
				if(!fileCheck.exists())
					continue;
				input = scr.nextLine();
				name[j] = input;
				check[j] = new JCheckBox(name[j]);
				check[j].setOpaque(false);
				check[j].setForeground(Color.WHITE);
				vb.add(check[j]);
				vb.add(Box.createVerticalStrut(20));
				
				check[j].addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent em) {
						
						for(Integer i = 1; i <= num; i++) {
							if(em.getSource() == check[i]) {
								Integer total = 0;
								boolean checker = true;
								String dir = "data" + File.separator + UI.comboState + File.separator + "D" + File.separator + i.toString() + ".txt";
								String dirTotal = "data" + File.separator + "Total" + File.separator + i.toString() + ".txt";
								try {
									InputStream finm = new FileInputStream(new File(dirTotal));
									Scanner scr = new Scanner(finm);
									String tot = scr.nextLine();
									total = Integer.parseInt(tot);
									finm.close();
							
									FileInputStream fin = new FileInputStream(new File(dir));
									int in = fin.read() - 48;
									fin.close();
									if(in == 1)
										checker = true;
									else
										checker = false;
									fin.close();
								}catch(IOException e) {
									e.printStackTrace();
								}
						
								if(check[i].isSelected() && checker == false) {
									total++;
									String st = "1";
									try {
										FileOutputStream fout = new FileOutputStream(new File(dir));
										fout.write(st.getBytes());
										fout.close();
									}
									catch(IOException e) {
										e.printStackTrace();
									}
								}
								else if(check[i].isSelected() == false && checker == true){
									total--;
									String st = "0";
									try {
										FileOutputStream fout = new FileOutputStream(new File(dir));
										fout.write(st.getBytes());
										fout.close();
									}
									catch(IOException e) {
										e.printStackTrace();
									}
								}
					
								try{						
									FileOutputStream fout = new FileOutputStream(new File(dirTotal));
									fout.write(total.toString().getBytes());
									fout.close();
								} catch(IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
				});
			}
			tmp.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error Occurred in D Day!", "Warning", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		
		add(vb);
	}
	
	public void set() {

		for(Integer i = 1; i <= num; i++) {
			String dir = "data" + File.separator + UI.comboState + File.separator + "D" + File.separator + i.toString() + ".txt";
			File file = new File(dir);
			if(!file.exists())
				continue;
			try {
				FileInputStream fin = new FileInputStream(new File(dir));
			
				int state;
				state = fin.read();
				if((char)state == '0')
					check[i].setSelected(false);
				else
					check[i].setSelected(true);
				fin.close();
			
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void paintComponent(Graphics g) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource("/resources/rsz_a.jpg"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}
	
}
