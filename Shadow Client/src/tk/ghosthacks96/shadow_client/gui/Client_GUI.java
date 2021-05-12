package tk.ghosthacks96.shadow_client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tk.ghosthacks96.shadow_client.Client;
import tk.ghosthacks96.shadow_client.Main;

public class Client_GUI extends JFrame {

	private JPanel contentPane;
	public static JTextArea textArea;
	public static JTextField textField;

	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_GUI frame = new Client_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Client_GUI() {
		setTitle("Welcome "+Main.getUsername());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 198);
		layeredPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setBounds(10, 220, 305, 20);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = textField.getText();
		        
		        if(str.trim().equals(""))
		        {
		        
		        }
		        else
		        {
		            try {
		            Client.OUT.writeUTF(Main.crypt.encrypt(str.trim()));
		            } catch (IOException ex) {
		            
		            }
		        }
		        
		        textField.setText("");
			}
		});
		layeredPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = textField.getText();
		        
		        if(str.trim().equals(""))
		        {
		        
		        }
		        else
		        {
		            try {
		            Client.OUT.writeUTF(Main.crypt.encrypt(str.trim()));
		            } catch (IOException ex) {
		            
		            }
		        }
		        
		        textField.setText("");
			}
		});
		btnNewButton.setBounds(325, 219, 89, 23);
		layeredPane.add(btnNewButton);
		
		
	}
}
