package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LoginPage extends JFrame implements ActionListener {

	private JButton button1,button2;
	JLabel label; 
	public LoginPage()
	{
		Container con=getContentPane();
		setLayout(new BorderLayout());
		button1=new JButton("Customer");
		button2=new JButton("Admin");
		con.add(button1,"North");
		con.add(button2,"South");
		button1.setBackground(Color.PINK);
		button2.setBackground(Color.pink);
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		setVisible(true);
		setSize(250,300);
		setTitle("LoginPage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String args[])
	{
		new LoginPage();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==button1)
		{
			setVisible(false);
			new SignupProcess();
		}
		
	}
	
}
