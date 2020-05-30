import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MainInterface extends JFrame
{
	JPanel JWelcomePanel, JProceedPanel;
	JButton JWelcomeButton, JInstructionButton, JNext, JCancel;
	public MainInterface()
	{
		this.setVisible(true);
		this.setBounds(100, 100, 1000, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JWelcomePanel = new JPanel();
		JProceedPanel = new JPanel();
		JWelcomePanel.setLayout(new GridLayout(3, 1));
		JProceedPanel.setLayout(new GridLayout(1, 2));
		
		JWelcomeButton = new JButton("Welcome User");
		JInstructionButton = new JButton("Please insert your card and press NEXT");
		JNext = new JButton("Next");
		JCancel = new JButton("Cancel");
		JNext.addActionListener(e -> actionOnClickingNext(e));
		JCancel.addActionListener(e -> actionOnClickingCancel(e));
		
		JWelcomeButton.setBackground(Color.black);
		JWelcomeButton.setForeground(Color.orange);
		JWelcomeButton.setFont(new Font("Calibri", Font.BOLD, 100));
		
		JInstructionButton.setBackground(Color.black);
		JInstructionButton.setForeground(Color.CYAN);
		JInstructionButton.setFont(new Font("Calibri", Font.BOLD, 40));
		
		JNext.setBackground(Color.black);
		JNext.setForeground(Color.green);
		JNext.setFont(new Font("Calibri", Font.BOLD, 40));
		
		JCancel.setBackground(Color.black);
		JCancel.setForeground(Color.red);
		JCancel.setFont(new Font("Calibri", Font.BOLD, 40));
	
		
		JProceedPanel.add(JNext);
		JProceedPanel.add(JCancel);
		
		JWelcomePanel.add(JWelcomeButton);
		JWelcomePanel.add(JInstructionButton);
		JWelcomePanel.add(JProceedPanel);
		
		getContentPane().add(JWelcomePanel);
		this.setTitle("My ATM");
		
	}
	
	public void actionOnClickingNext(ActionEvent e)
	{
		new AccountSelection();
	}
	public void actionOnClickingCancel(ActionEvent e)
	{
		JOptionPane.showMessageDialog(null, "Happy to help you(Bye Bye)");
		System.exit(0);
	}
	public static void main(String args[])
	{
		new MainInterface();
	}
}
