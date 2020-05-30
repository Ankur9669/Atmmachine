import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
public class Verification extends JFrame
{
	JPanel JMainPanel, JVerificationPanel, JProceedPanel;
	JLabel JCustomerId, JCustomerPin;
	JButton JWelcomeButton, JSubmitButton, JCancelButton, JNext, JCancel;
	JTextField JFieldCustomerId;
	JPasswordField JFieldCustomerPin;
	
	public Verification()
	{
		this.setVisible(true);
		this.setBounds(100, 100, 1000, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMainPanel = new JPanel();
		JVerificationPanel = new JPanel();
		JProceedPanel = new JPanel();
		JMainPanel.setLayout(new GridLayout(3, 1));
		JVerificationPanel.setLayout(new GridLayout(2, 2));
		JProceedPanel.setLayout(new GridLayout(1, 2));
		
		JCustomerId = new JLabel("Customer ID: ");
		JCustomerPin = new JLabel("Customer Pin: ");
		JCustomerId.setFont(new Font("Calibri", Font.BOLD, 40));
		JCustomerPin.setFont(new Font("Calibri", Font.BOLD, 40));
		JCustomerId.setForeground(Color.green);
		JCustomerPin.setForeground(Color.green);
		
		JWelcomeButton = new JButton("Welcome User");
		JNext = new JButton("Next");
		JCancel = new JButton("Cancel");
		JWelcomeButton.setFont(new Font("Calibri", Font.BOLD, 80));
		JNext.setFont(new Font("Calibri", Font.BOLD, 40));
		JCancel.setFont(new Font("Calibri", Font.BOLD, 40));
		JWelcomeButton.setBackground(Color.black);
		JWelcomeButton.setForeground(Color.GREEN);
		JNext.setBackground(Color.black);
		JNext.setForeground(Color.green);
		JCancel.setBackground(Color.black);
		JCancel.setForeground(Color.green);
		JWelcomeButton.setBackground(Color.black);
		JWelcomeButton.setForeground(Color.green);
		JNext.addActionListener(e -> actionOnClickingNext(e));
		JCancel.addActionListener(e -> actionOnClickingCancel(e));
		
		JFieldCustomerId = new JTextField(20);
		JFieldCustomerId.setFont(new Font("Calibri", Font.BOLD, 40));
		
		JFieldCustomerPin = new JPasswordField(20);
		JFieldCustomerPin.setFont(new Font("Calibri", Font.BOLD, 40));
		
		JVerificationPanel.add(JCustomerId);
		JVerificationPanel.add(JFieldCustomerId);
		JVerificationPanel.add(JCustomerPin);
		JVerificationPanel.add(JFieldCustomerPin);
		JVerificationPanel.setBackground(Color.black);
		
		
		JProceedPanel.add(JNext);
		JProceedPanel.add(JCancel);
		
		JMainPanel.add(JWelcomeButton);
		JMainPanel.add(JVerificationPanel);
		JMainPanel.add(JProceedPanel);
		
		getContentPane().add(JMainPanel);
		this.setTitle("Verification");
	}
	
	public void actionOnClickingNext(ActionEvent e) 
	{
		boolean check1= false;
		boolean check2 = false;
		String customerId = JFieldCustomerId.getText();
		check1 = checkCustomerId(customerId);
		check2 = checkCustomerPin(customerId);
		if(check1 == true && check2 == true)
		{
			new ChoiceScreen(customerId);
			//JOptionPane.showMessageDialog(null, "Congrtulations");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Invalid Customerid or Pin");
			this.dispose();
		}
	}
	
	public boolean checkCustomerId(String customerid)
	{
		DatabaseConnection connect = new DatabaseConnection();
		boolean check1 = false;
		try
		{
			connect.connection();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.toString());
		}
		try
		{
			ResultSet rs1 = connect.executeAndReturn("select customerid from atmdatabase where customerid = '"+ customerid +"'");
			rs1.next();
			if(rs1.getString(1) == null)
			{
				JOptionPane.showMessageDialog(null, "Invalid Customerid or pin");
				this.dispose();
			}
			else
			{
				check1 = true;
				rs1.getString(1);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.toString());
		}
		return check1;
	}
	public boolean checkCustomerPin(String customerid)
	{
		DatabaseConnection connect = new DatabaseConnection();
		boolean check2 = false;
		try
		{
			connect.connection();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.toString() + "2");
		}
		try
		{
			ResultSet rs1 = connect.executeAndReturn("select pin from atmdatabase where customerid = '" + customerid + "'");
			rs1.next();
			if(rs1.getString(1) == "")
			{
				JOptionPane.showMessageDialog(null, "Invalid Customerid or pin");
				this.dispose();
			}
			else
			{
				if(rs1.getString(1).equals(JFieldCustomerPin.getText()))
				{
					check2 = true;	
				}
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.toString());
		}
		return check2;
	}
	public void actionOnClickingCancel(ActionEvent e)
	{
		JOptionPane.showMessageDialog(null, "Happy to help you(Bye Bye)");
		System.exit(0);
	}
}
