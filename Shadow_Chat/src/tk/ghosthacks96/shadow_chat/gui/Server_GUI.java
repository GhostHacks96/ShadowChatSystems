package tk.ghosthacks96.shadow_chat.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import tk.ghosthacks96.shadow_chat.Main;


@SuppressWarnings("all")
public class Server_GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server_GUI frame = new Server_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel jLabel1;
	private JScrollPane jScrollPane1;

	private JLayeredPane layeredPane;
	public static JTextArea textArea_1;
	private JTextField textField;
	JMenuItem stopServer;
	JMenuItem startServer;

	public Server_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLayeredPane layeredPane_1 = new JLayeredPane();
		contentPane.add(layeredPane_1, BorderLayout.CENTER);

		jScrollPane1 = new JScrollPane();
		jScrollPane1.setBounds(10, 195, 404, -162);
		layeredPane_1.add(jScrollPane1);

		jScrollPane1.setAutoscrolls(true);

		textArea_1 = new JTextArea();
		textArea_1.setBounds(20, 35, 394, 149);
		textArea_1.setEditable(false);
		layeredPane_1.add(textArea_1);

		jLabel1 = new JLabel("Messages");
		jLabel1.setBounds(10, 11, 86, 14);
		layeredPane_1.add(jLabel1);

		textField = new JTextField();
		textField.setBounds(19, 196, 296, 20);
		layeredPane_1.add(textField);
		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.sendToAll("Server: " + textField.getText());
				textArea_1.append("\nServer: " + textField.getText());
				textField.setText("");
			}
			
		});
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.sendToAll("Server: " + textField.getText());
				textArea_1.append("\nServer: " + textField.getText());
				textField.setText("");
			}
		});
		btnNewButton.setBounds(325, 195, 89, 23);
		layeredPane_1.add(btnNewButton);

		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);

		startServer = new JMenuItem("Start Server");
		startServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Main.textArea_1()) {
					startServer.setEnabled(false);
					stopServer.setEnabled(true);
				}else {
					Main.openSettingsPage();
				}
				
			}
		});
		toolBar.add(startServer);

		stopServer = new JMenuItem("Stop Server");
		stopServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.running = false;
				Main.sendToAll("CMD: SHUTDOWN");
				startServer.setEnabled(true);
				stopServer.setEnabled(false);
				System.exit(0);
			}
		});
		stopServer.setEnabled(false);
		toolBar.add(stopServer);

		JMenuItem settings = new JMenuItem("Settings");
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.openSettingsPage();
			}
		});

		toolBar.add(settings);

		JMenuItem stats = new JMenuItem("Status");
		stats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Server Status \n\nServer Active: " + Main.running
						+ "\nNumber of Users: " + (Main.OUTALL.size() - 1));
			}
		});
		toolBar.add(stats);

	}
}
