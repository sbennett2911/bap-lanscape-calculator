/***********************************************************************
 Program Name: HotTubCalc.java
 Programmer's Name: Steven Bennett
 Program Description: HotTubCalc allows user to select if hot tub is round
 or oval.  The appropriate inputs are made available based on selection.
 The volume of the hot tub is then calculated.  Input units are in feet.
 Output units are in cubic feet.
 ***********************************************************************/ 
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;

public class HotTubCalc extends JPanel{
	
	//declare private instance variables
	private JRadioButton roundTub;
	private JRadioButton ovalTub;
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
	public HotTubCalc(){
		
		//instantiate radio buttons
		roundTub = new JRadioButton("Round Tub             ");
		ovalTub = new JRadioButton("Oval Tub               ");
		
		//instantiate buttons
		calculateButton = new JButton("Calculate Volume");
		exitButton = new JButton("Exit");
		
		//instantiate labels
		lengthLabel = new JLabel("     Hot Tub Length (ft): ");
		widthLabel = new JLabel("     Hot Tub Width (ft): ");
		depthLabel = new JLabel("     Hot Tub Depth (ft): ");
		volumeLabel = new JLabel("                Hot Tub Volume (ft^3):    ");
		
		//instantiate text fields
		lengthField = new JTextField(15);
		widthField = new JTextField(15);
		depthField = new JTextField(15);
		volumeField = new JTextField(15);
		
		//group radio buttons and add to container
		ButtonGroup group = new ButtonGroup();
		group.add(roundTub);
		group.add(ovalTub);
		
		//add components to container
		add(roundTub);
		add(ovalTub);
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
		roundTub.setSelected(true);
		widthField.setEditable(false);
		calculateButton.setBackground(Color.green);
		exitButton.setBackground(Color.red);
		exitButton.setForeground(Color.white);
		volumeField.setEditable(false);
		
		//set button mnemonics
		roundTub.setMnemonic('R');
		ovalTub.setMnemonic('O');
		calculateButton.setMnemonic('C');
		exitButton.setMnemonic('X');
		
		//addActionListener and instantiate event handlers
		RadioButtonHandler rbHandler = new RadioButtonHandler();
		roundTub.addActionListener(rbHandler);
		ovalTub.addActionListener(rbHandler);
		
		CalculateButtonHandler cbHandler = new CalculateButtonHandler();
		calculateButton.addActionListener(cbHandler);
		
		ExitButtonHandler ebHandler = new ExitButtonHandler();
		exitButton.addActionListener(ebHandler);
		
		FocusHandler fhandler = new FocusHandler();
		lengthField.addFocusListener(fhandler);
		widthField.addFocusListener(fhandler);
		depthField.addFocusListener(fhandler);
		
	}//end class constructor.
	
	
	/*section to create event handlers*/
	class RadioButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(roundTub.isSelected() == true)
			{
				widthField.setEditable(false);
				widthField.setText(""); //ensures widthField is cleared when switching back from ovalTub.
			}
			else
				widthField.setEditable(true);
		}
	}
	
	class CalculateButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			DecimalFormat num = new DecimalFormat(",###.##");
			
			//variables used in event handler
			double length;
			double width;
			double depth;
			double radius;
			double volume = 0.0;
			String instring;
			
			if(roundTub.isSelected() == true)
			{
				width = 0.0; //reset width to zero when switching back from ovalTub.

				/*Test input of length field*/
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
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Enter positive numeric value for length.", "INPUT ERROR!", JOptionPane.ERROR_MESSAGE);
					lengthField.setText("");
					length = 0;
				}
				
				/*Test Input for depth*/
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
				
				/*Calculate Volume of round tub.*/
				radius = length / 2;
				volume = Math.PI * Math.pow(radius, 2) * depth;
			}
			
			if(ovalTub.isSelected() == true)
			{
				/*Test input of length field*/
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
				
				/*Test input of width*/
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
					JOptionPane.showMessageDialog(null, "Enter positve numeric value for width.", "INPUT ERROR!", JOptionPane.ERROR_MESSAGE);
					widthField.setText("");
					width = 0;
				}
				
				/*Test input of depth*/
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
				
				/*Calculate volume of oval tub*/
				volume = Math.PI * (length / 2) * (width / 2) * depth;
			}
			
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
