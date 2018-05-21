package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class SignupProcess extends JFrame implements ActionListener {
	private JButton button1;
	private JButton button2;
	public SignupProcess()
	{
		Container con=getContentPane();
		setLayout(new BorderLayout());
		button1=new JButton("Login");
		button2=new JButton("Register");
		con.add(button1,"North");
		con.add(button2,"South");
		setTitle("SIGNUP && LOGIN");
		button1.addActionListener(this);
		button2.addActionListener(this);
		setSize(250,300);
		setVisible(true);
	}
	public static void main(String args[])
	{
		new SignupProcess();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==button1)
		{
			setVisible(false);
			new CustomerLogin();
		}
		if(ae.getSource()==button2)
		{
			setVisible(false);
			new RegisterCustomer();
		}
		
	}

}
