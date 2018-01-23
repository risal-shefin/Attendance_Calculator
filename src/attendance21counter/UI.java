package attendance21counter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

public class UI extends JFrame {
	String cycle[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
	JComboBox combo = new JComboBox(cycle);
	static JLabel label1 = new JLabel("Cycle                                 ");
	static JLabel label2 = new JLabel("Routine                                     ");
	JTabbedPane tab = new JTabbedPane();
	Box vertical = Box.createVerticalBox();
	Box horizon = Box.createHorizontalBox();
	public static String comboState = "1";
	static A aday = new A();
	static B bday = new B();
	static C cday = new C();
	static D dday = new D();
	static E eday = new E();
	
	public UI() {
		label1.setFont( new Font("Times New Roman", Font.BOLD, 16));
		label2.setFont(new Font("Pristina", Font.BOLD, 27));
		combo.setSize(50, 20);
		Result result = new Result();
		tab.addTab("A Day", aday);
		tab.addTab("B Day", bday);
		tab.addTab("C Day", cday);
		tab.addTab("D Day", dday);
		tab.addTab("E Day", eday);
		tab.addTab("Percentage", result);
		
		horizon.add(label1);
		horizon.createHorizontalStrut(500);
		horizon.add(label2);
		add(combo);
		vertical.add(horizon);
		vertical.createVerticalStrut(50);
		vertical.add(tab);
		add(vertical);
		setTitle("Developed By @Shefin");
		
		aday.set();
		bday.set();
		cday.set();
		dday.set();
		eday.set();
		eday.set();
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboState = combo.getSelectedItem().toString();
				aday.set();
				bday.set();
				cday.set();
				dday.set();
				eday.set();
				eday.set();
			}
		});
	}
	
}
