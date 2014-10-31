/***********************************************************************
 Program Name: TempCalc.java
 Programmer's Name: Steven Bennett
 Program Description: TempCalc allows user to select temperature units
 and enter a temperature. The conversion will be opposite of input units
 from user, either Celsius or Farenheit.
 ***********************************************************************/ 
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;


public class TempCalc extends JPanel{
	//declare private instance variables
	private JComboBox tempUnitList;
	private JButton convertButton;
	private JButton exitButton;
	private JLabel tempInputLabel;
	private JLabel resultLabel;
	private JTextField tempInputField;
	private JTextField convertToField;
	private JTextField resultField;
	
	//class constructor
	public TempCalc(){
		
		//instantiate combo box
		tempUnitList = new JComboBox();
		tempUnitList.addItem("F");
		tempUnitList.addItem("C");
		
		//instantiate buttons
		convertButton = new JButton("Convert");
		exitButton = new JButton("Exit");
		
		//instantiate labels
		tempInputLabel = new JLabel(" Enter Temperature:      ");
		resultLabel = new JLabel("                            Results:              ");
		
		//instantiate text fields
		tempInputField = new JTextField(15);
		convertToField = new JTextField(1);
		resultField = new JTextField(15);
		
		//add components to container
		add(tempInputLabel);
		add(tempInputField);
		add(tempUnitList);
		add(resultLabel);
		add(resultField);
		add(convertToField);
		add(convertButton);
		add(exitButton);
		
		//set properties for components
		tempUnitList.setEditable(false);
		convertButton.setBackground(Color.green);
		exitButton.setBackground(Color.red);
		exitButton.setForeground(Color.white);
		convertToField.setEditable(false);
		convertToField.setBorder(null);
		convertToField.setText("C");//set default unit to convert to.
		resultField.setEditable(false);
		
		//set button mnemonics
		convertButton.setMnemonic('C');
		exitButton.setMnemonic('X');
		
		//add action listeners and instantiate event handlers
		ConvertButtonHandler cbHandler = new ConvertButtonHandler();
		convertButton.addActionListener(cbHandler);
		
		FocusHandler fHandler = new FocusHandler();
		tempInputField.addFocusListener(fHandler);
		
		TempUnitListHandler tulHandler = new TempUnitListHandler();
		tempUnitList.addActionListener(tulHandler);
		
		ExitButtonHandler ebHandler = new ExitButtonHandler();
		exitButton.addActionListener(ebHandler);
	}
	
	
	/*section to create event handlers*/
	class TempUnitListHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(tempUnitList.getSelectedItem() == "F")
			{
				convertToField.setText("C");
			}
			else
			{
				convertToField.setText("F");
			}
			
		}
	}
	
	class FocusHandler implements FocusListener{
		public void focusGained(FocusEvent e){
			if(e.getSource() == tempInputField)
			{
				resultField.setText("");
			}
			else if(e.getSource() == resultField)
			{
				convertButton.requestFocus();
			}
		}
		
		public void focusLost(FocusEvent e){
			if(e.getSource() == resultField)
				convertButton.requestFocus();
		}
	}
	class ConvertButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			DecimalFormat num = new DecimalFormat();
			
			double fahrenheit = 0.0;
			double celsius = 0.0;
			double result = 0.0;
			boolean validInput = true;
			String instring = "";
			
			
			if(tempUnitList.getSelectedItem() == "F")
			{
				instring = tempInputField.getText();
				if(instring.equals(""))
				{
					instring = "32";
					tempInputField.setText("32"); //defaults to freezing point if no input from user.
				}
				
				try
				{
					validInput = true;
					fahrenheit = Double.parseDouble(instring);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Enter a numeric value.", "INPUT ERROR!", JOptionPane.ERROR_MESSAGE);
					tempInputField.setText("");
					instring = "";
					fahrenheit = 0;
					validInput = false;
				}
				
				if(validInput == true)
					result = (fahrenheit - 32) * 5/9;
			}
			
			if(tempUnitList.getSelectedItem() == "C")
			{
				instring = tempInputField.getText();
				if(instring.equals(""))
				{
					instring = "0";
					tempInputField.setText("0"); //set default to freezing point if no user input.
				}
				
				try
				{
					validInput = true;
					celsius = Double.parseDouble(instring);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Enter a numeric value.", "INPUT ERROR!", JOptionPane.ERROR_MESSAGE);
					tempInputField.setText("");
					instring = "";
					celsius = 0;
					validInput = false;
				}
				
				if(validInput == true)
					result = (celsius * 9/5) + 32;
			}
			
			if(validInput == true)
				resultField.setText(num.format(result));
		}
	}
	
	
	class ExitButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
}
