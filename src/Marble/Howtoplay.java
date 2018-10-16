package Marble;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;

public class Howtoplay extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Howtoplay() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		URL url4 =getClass().getClassLoader().getResource("HTP.png");
	      lblNewLabel.setIcon(new ImageIcon(url4));
		lblNewLabel.setBounds(-14, -29, 629, 806);
		contentPane.add(lblNewLabel);
	}

}
