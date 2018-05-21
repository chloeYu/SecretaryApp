package gui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AdministratorLogin extends JFrame {
	private JButton button;
	private JPasswordField pass;
	private JTextField field;
	private JLabel label,label2;
	public AdministratorLogin()
	{
		Container con=getContentPane();
		setLayout(new FlowLayout());
		button=new JButton("Login");
		field=new JTextField(15);
		pass=new JPasswordField(15);
		label=new JLabel("Enter user name");
		label2=new JLabel("Enter  password");
		con.add(label);
		con.add(field);
		con.add(label2);
		con.add(pass);
		con.add(button);
		setTitle("Administrator Login");
		setVisible(true);
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
	public static void main(String args[])
	{
		new AdministratorLogin();
	}

}
