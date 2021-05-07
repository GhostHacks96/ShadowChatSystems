package tk.ghosthacks96.shadow_chat.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tk.ghosthacks96.shadow_chat.Main;


@SuppressWarnings("all")
public class Settings_Window extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	static Settings_Window frame;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Settings_Window();
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
	public Settings_Window() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Port: ");
		lblNewLabel.setFont(new Font("MS Gothic", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 161, 31);
		layeredPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setToolTipText("must be a valid port number, will cause errors if its not.");
		textField.setBounds(181, 17, 210, 20);
		layeredPane.add(textField);
		textField.setColumns(4);
		textField.setText(Main.portNumber);

		JLabel lblNewLabel_1 = new JLabel("Welcome Message");
		lblNewLabel_1.setFont(new Font("MS Gothic", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 83, 161, 31);
		layeredPane.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setToolTipText("You can put anything, to include the persons username please use the code tag (user)  ");
		textField_1.setBounds(181, 89, 210, 20);
		layeredPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(Main.welcomeMSG);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.welcomeMSG = textField_1.getText();
				Main.portNumber = textField.getText();
				Main.uid = textField_2.getText();
				Main.saveSettings();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(147, 217, 89, 23);
		layeredPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Server UUID:");
		lblNewLabel_2.setBounds(10, 150, 161, 20);
		lblNewLabel_2.setFont(new Font("MS Gothic", Font.BOLD, 15));
		layeredPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(181, 151, 210, 20);
		layeredPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(Main.uid);
	}
}
