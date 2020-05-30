import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.*;

public class DepositMoney extends JFrame
{
	JPanel JMainPanel, JProceedPanel, JDepositMoneyPanel;
	JLabel JAmount;
	JTextField JFieldAmount;
	JButton JWelcomeButton, JNext, JCancel;
	public DepositMoney(String customerId, String customerName)
	{
		this.setVisible(true);
		this.setBounds(100, 100, 1000, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMainPanel = new JPanel();
		JDepositMoneyPanel = new JPanel();
		JProceedPanel = new JPanel();
		JMainPanel.setLayout(new GridLayout(3, 1));
		JDepositMoneyPanel.setLayout(new GridLayout(1, 2));
		JProceedPanel.setLayout(new GridLayout(1, 2));
		
		JWelcomeButton = new JButton("Welcome: " + customerName);
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
		JNext.addActionListener(e -> actionOnClickingNext(e, customerId));
		JCancel.addActionListener(e -> actionOnClickingCancel(e));
		
		JAmount = new JLabel("Amount: ");
		JFieldAmount = new JTextField(20);
		JAmount.setFont(new Font("Calibri", Font.BOLD, 40));
		//JAmount.setBackground(Color.black);
		JAmount.setForeground(Color.GREEN);
		JFieldAmount.setFont(new Font("Calibri", Font.BOLD, 40));
		//JFieldAmount.setBackground(Color.black);
		//JFieldAmount.setForeground(Color.GREEN);
		
		
		JDepositMoneyPanel.add(JAmount);
		JDepositMoneyPanel.add(JFieldAmount);
		JDepositMoneyPanel.setBackground(Color.black);
		
		JProceedPanel.add(JNext);
		JProceedPanel.add(JCancel);
		
		JMainPanel.add(JWelcomeButton);
		JMainPanel.add(JDepositMoneyPanel);
		JMainPanel.add(JProceedPanel);
		
		getContentPane().add(JMainPanel);
		this.setTitle("Deposit Screen");
	}
	public void actionOnClickingNext(ActionEvent e, String customerId)
	{
		DatabaseConnection connect = new DatabaseConnection();
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
			int amount = Integer.parseInt(JFieldAmount.getText());
			ResultSet rs = connect.executeAndReturn("select balance from atmdatabase where customerid = '" 
			+ customerId + "'");
			rs.next();
			amount = amount + Integer.parseInt(rs.getString(1));
			//String amt = Integer.toString(amount);
			connect.executeQuery("update atmdatabase set balance = '" + amount + "'"
					+ "where customerid = '" + customerId + "'");
			JOptionPane.showMessageDialog(null, "Your amount was "
					+ "deposited your new Balance is: " + amount);
			this.dispose();
			new Verification();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.toString());
		}
	}
	public void actionOnClickingCancel(ActionEvent e)
	{
		JOptionPane.showMessageDialog(null, "Happy to help you(Bye Bye)");
		System.exit(0);
	}
}
