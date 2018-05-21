package gui;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import beans.CustomerBean;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class CustomerDb extends JFrame {
	private JButton button;
	public CustomerDb()
	{
		super("Display All");
		String heading[]={"Name","Sex","Address","password","email","mobileno"};
		String data[][]=new String[20][6];
		ArrayList<CustomerBean> list=new ArrayList<CustomerBean>();
		try
		{
			FileInputStream fin=new FileInputStream("Registration.dat");
			ObjectInputStream oin=new ObjectInputStream(fin);
			list=(ArrayList<CustomerBean>)oin.readObject();
			
			int r=0,c=0;
			for(CustomerBean re : list)
			{
				data[r][0]=(re.getName());
				data[r][1]=(re.getSex());
				data[r][2]=(re.getAddress());
				data[r][3]=(re.getPassword());;
				
				data[r][4]=(re.getEmail());;
				data[r][5]=String.valueOf((re.getMobileno()));
				
				r++;
				c=0;
			}
			
			Container con=getContentPane();
			con.setLayout(new BorderLayout());
			
			JTable datatable=new JTable(data, heading);
			JScrollPane pane=new JScrollPane(datatable);
			
			con.add(new JLabel("All Registration Details"),BorderLayout.NORTH);
			con.add(pane,BorderLayout.CENTER);
           pane.setBackground(Color.GRAY);
	        con.setBackground(Color.PINK);
	        datatable.setBackground(Color.WHITE);
			setSize(450, 300);
			
			
			setVisible(true);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		new CustomerDb();
	}
}

