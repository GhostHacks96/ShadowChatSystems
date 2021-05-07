package tk.ghosthacks96.shadow_client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import tk.ghosthacks96.shadow_client.Main;

public class Login_Window extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	public static Login_Window frame;

	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login_Window();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login_Window() {
		setTitle("Shadow Chat Login");
		setResizable(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("MS Gothic", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(144, 11, 106, 14);
		layeredPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(144, 99, 103, 20);
		layeredPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(144, 77, 106, 14);
		layeredPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(144, 130, 106, 14);
		layeredPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(144, 154, 106, 20);
		layeredPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(144, 209, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Main.sendLogin(textField.getText(),textField_1.getText())) {
				}else {
					JOptionPane.showMessageDialog(null, "Invalid Login!");
				}
			}
			
		});
		layeredPane.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(430, 68, -424, -30);
		layeredPane.add(separator);
	}
}
