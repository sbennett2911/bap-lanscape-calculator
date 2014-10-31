/***********************************************************************
 Program Name: General.java
 Programmer's Name: Steven Bennett
 Program Description: Displays system date and provides exit button to 
 quit the program.
 ***********************************************************************/
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class General extends JPanel{
	//declare private instance variables
	private JButton exitButton;
	private JLabel dateLabel;
	private JTextField dateField;
	private Date today;
	private SimpleDateFormat systemDate;
	
	//class constructor
	public General(){
		
		//instantiate objects to handle system date and store formatted date in today.
		today = new Date();
		systemDate = new SimpleDateFormat("MM/dd/yyyy");
		systemDate.format(today);
				
		//instantiate button
		exitButton = new JButton("Exit");
		
		//instantiate label and field
		dateLabel = new JLabel("Today's date: ");
		dateField = new JTextField(10);
		
		//add components to container
		add(dateLabel);
		add(dateField);
		add(exitButton);
		
		//set properties for components
		exitButton.setBackground(Color.red);
		exitButton.setForeground(Color.white);
		dateField.setText(systemDate.format(today));
		dateField.setEditable(false);
		
		//set button mnemonic
		exitButton.setMnemonic('X');
		
		//add ActionListener and instantiate event handlers
		ExitButtonHandler ebHandler = new ExitButtonHandler();
		exitButton.addActionListener(ebHandler);
	}
	
	
	/*section to create event handler*/
	
	class ExitButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
}
