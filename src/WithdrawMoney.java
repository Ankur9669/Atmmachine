import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;



import java.awt.*;
public class WithdrawMoney extends JFrame
{
	JPanel JMainPanel, JWithdrawPanel, JProceedPanel;
	JButton JWelcomeButton, JNext, JCancel;
	JLabel JEnterAmount, JEnterPin;
	JTextField JFieldAmount;
	JPasswordField JFieldPin;
	public WithdrawMoney(String customerId, String customerName)
	{
		this.setVisible(true);
		this.setBounds(100, 100, 1000, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMainPanel = new JPanel();
		JWithdrawPanel = new JPanel();
		JProceedPanel = new JPanel();
		JMainPanel.setLayout(new BorderLayout());
		JWithdrawPanel.setLayout(new GridLayout(2, 2));
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
		JWelcomeButton.setBackground(Color.black);
		JWelcomeButton.setForeground(Color.green);
		JNext.addActionListener(e -> actionOnClickingNext(e, customerId));
		JCancel.addActionListener(e -> actionOnClickingCancel(e));

		JEnterAmount = new JLabel("Please Enter the Amount: ");
		JEnterPin = new JLabel("Please Enter your pin:");
		JEnterAmount.setFont(new Font("Calibri", Font.BOLD, 40));
		JEnterAmount.setForeground(Color.GREEN);
		JEnterPin.setFont(new Font("Calibri", Font.BOLD, 40));
		JEnterPin.setForeground(Color.GREEN);
		JWithdrawPanel.setBackground(Color.black);
		
		JFieldAmount = new JTextField(20);
		JFieldAmount.setFont(new Font("Calibri", Font.BOLD, 40));
		
		JFieldPin = new JPasswordField(20);
		JFieldPin.setFont(new Font("Calibri", Font.BOLD, 40));
		
		JWithdrawPanel.add(JEnterAmount);
		JWithdrawPanel.add(JFieldAmount);
		JWithdrawPanel.add(JEnterPin);
		JWithdrawPanel.add(JFieldPin);
		
		JProceedPanel.add(JNext);
		JProceedPanel.add(JCancel);

		JMainPanel.add("North", JWelcomeButton);
		JMainPanel.add("Center", JWithdrawPanel);
		JMainPanel.add("South", JProceedPanel);

		getContentPane().add(JMainPanel);
		this.setTitle("Withdrawal Screen");

	}


	public void actionOnClickingNext(ActionEvent e, String customerId)
	{
		boolean check = false;
		check = checkCustomerPin(customerId);
		if(check == true)
		{
			//int amount = Integer.parseInt(JFieldAmount.getText());
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
				ResultSet rs = connect.executeAndReturn("select balance from atmdatabase where customerid = '" + customerId + "'");
				rs.next();
				if(Integer.parseInt(JFieldAmount.getText()) > Integer.parseInt(rs.getString(1)))
				{
					JOptionPane.showMessageDialog(null, "Entered amount is more than balance");
					this.dispose();
				}
				else
				{
					int amount = Integer.parseInt(rs.getString(1));
					amount = amount - Integer.parseInt(JFieldAmount.getText());
					int choice  = JOptionPane.showConfirmDialog(null, "Do you want to Withdraw: " + JFieldAmount.getText()+ " rs");
					if(choice == 0)
					{
						try
						{
							connect.executeQuery("update atmdatabase set balance = '" 
						+ amount + "'where customerid = '" + customerId + "'");
							CheckBalance checkBal = new CheckBalance();
							JOptionPane.showMessageDialog(null, "Success your updated balance is: " + checkBal.checkAndReturnBalance(customerId));
							new Verification();
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, ex.toString());
						}
					}
					else
					{
						this.dispose();
					}
				}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.toString());
			}
		}
		else if(check == false)
		{
			JOptionPane.showMessageDialog(null, "Wrong pin");
			System.exit(0);
		}
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
			JOptionPane.showMessageDialog(null, ex.toString());
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
				if(rs1.getString(1).equals(JFieldPin.getText()))
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
