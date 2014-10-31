/***********************************************************************
 Program Name: PoolCalc.java
 Programmer's Name: Steven Bennett
 Program Description: PoolCalc accepts length, width, and depth input
 then calculates the volume of a pool.  Input units are in feet.  Output
 units are in cubic feet.  Class creates a Panel to be used with a 
 JTabbedPane Application.
 ***********************************************************************/ 
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;

public class PoolCalc extends JPanel{
	
	//declare private instance variables
	private JButton calculateButton;
	private JButton exitButton;
	private JLabel lengthLabel;
	private JLabel widthLabel;
	private JLabel depthLabel;
	private JLabel volumeLabel;
	private JTextField lengthField;
	private JTextField widthField;
	private JTextField depthField;
	private JTextField volumeField;
	
	//class constructor
	public PoolCalc(){
		//instantiate Buttons
		calculateButton = new JButton("Calculate Volume");
		exitButton = new JButton("Exit");
		
		//instantiate Labels
		lengthLabel = new JLabel("  Pool Length (ft):  ");
		widthLabel = new JLabel("  Pool Width (ft):   ");
		depthLabel = new JLabel("   Pool Depth (ft):   ");
		volumeLabel = new JLabel("                   Pool Volume (ft^3):     ");
	
		//instantiate TextFields
		lengthField = new JTextField(15);
		widthField = new JTextField(15);
		depthField = new JTextField(15);
		volumeField = new JTextField(15);
		
		//add components to container
		add(lengthLabel);
		add(lengthField);
		add(widthLabel);
		add(widthField);
		add(depthLabel);
		add(depthField);
		add(calculateButton);
		add(exitButton);
		add(volumeLabel);
		add(volumeField);
		
		//set properties for components
		calculateButton.setBackground(Color.green);
		exitButton.setBackground(Color.red);
		exitButton.setForeground(Color.white);
		volumeField.setEditable(false);
		
		//set Button mnemonics
		calculateButton.setMnemonic('C');
		exitButton.setMnemonic('X');
		
		//add ActionListener and instantiate event handlers
		CalculateButtonHandler cbHandler = new CalculateButtonHandler();
		calculateButton.addActionListener(cbHandler);
		
		ExitButtonHandler ebHandler = new ExitButtonHandler();
		exitButton.addActionListener(ebHandler);
		
		FocusHandler fhandler = new FocusHandler();
		lengthField.addFocusListener(fhandler);
		widthField.addFocusListener(fhandler);
		depthField.addFocusListener(fhandler);
		
	}//end class constructor
	
	
	/*section to create event handlers*/
	
	class CalculateButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			DecimalFormat num = new DecimalFormat(",###.##");
			double length = 0.0;
			double width = 0.0;
			double depth = 0.0;
			double volume = 0.0;
			String instring;
			
			/*test length input set to 0 if field is empty.*/
				instring = lengthField.getText();
				if(instring.equals(""))
				{
					instring = "0";
					lengthField.setText("0");
				}
			
			try
			{	
				length = Double.parseDouble(instring);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Enter positive numeric value for length.", "INPUT ERROR!", JOptionPane.ERROR_MESSAGE);
				lengthField.setText("");
				length = 0;
			}
			
			
			/*test width set to 0 if field is empty.*/ 
				instring = widthField.getText();
				if(instring.equals(""))
				{
					instring = "0";
					widthField.setText("0");
				}
				
			try
			{
				width = Double.parseDouble(instring);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Enter positive numeric value for width.", "INPUT ERROR!", JOptionPane.ERROR_MESSAGE);
				widthField.setText("");
				width = 0;
			}
			
			/*test depth set to 0 if field is empty.*/
				instring = depthField.getText();
				if(instring.equals(""))
				{
					instring = "0";
					depthField.setText("0");
				}
			
			try
			{
				depth = Double.parseDouble(instring);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Enter positive numeric value for depth.", "INPUT ERROR!", JOptionPane.ERROR_MESSAGE);
				depthField.setText("");
				depth = 0;
			}
			
			//calculate volume and display formatted result in volumeField.
			volume = length * width * depth;
			volumeField.setText(num.format(volume));
		}
	}
	
	
	class ExitButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	
	
	class FocusHandler implements FocusListener{
		public void focusGained(FocusEvent e){
			if(e.getSource() == lengthField || e.getSource() == widthField || e.getSource() == depthField)
			{
				volumeField.setText("");
			}
			else if(e.getSource() == volumeField)
			{
				calculateButton.requestFocus();
			}
		}
		
		public void focusLost(FocusEvent e){
			if(e.getSource() == depthField)
				calculateButton.requestFocus();
		}
	}
}
