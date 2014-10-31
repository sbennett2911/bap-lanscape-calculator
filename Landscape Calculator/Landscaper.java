/***********************************************************************
 Program Name: Landscaper.java
 Programmer's Name: Steven Bennett
 Program Description: Landscaper is a tabbed panel program that provides
 various calculators to assist with the calculation of volume, temperature,
 and length unit conversion.
 ***********************************************************************/ 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Landscaper extends JFrame{
	
	//instantiate JPanel objects
	JPanel general = new General();
	JPanel poolCalc = new PoolCalc();
	JPanel hotTubCalc = new HotTubCalc();
	JPanel tempCalc = new TempCalc();
	
	//constructor for Landscaper application
	public Landscaper(){
		//create JTabbedPane object for program to run in
		JTabbedPane jtp = new JTabbedPane();
		getContentPane().add(jtp);
		
		//add Tabs to JTabbedPane
		jtp.addTab("General", general);
		jtp.addTab("Pools", poolCalc);
		jtp.addTab("Hot Tubs", hotTubCalc);
		jtp.addTab("Temperature", tempCalc);
		
		//set properties for Landscaper object.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(875, 300, 400, 250);
		setTitle("Landscaper");
	}
	
	public static void main(String[] args) {
		Landscaper test = new Landscaper();
	}

}
