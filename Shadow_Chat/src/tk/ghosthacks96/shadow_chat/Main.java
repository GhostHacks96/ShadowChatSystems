package tk.ghosthacks96.shadow_chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import tk.ghosthacks96.shadow_chat.gui.Server_GUI;
import tk.ghosthacks96.shadow_chat.gui.Settings_Window;
import tk.ghosthacks96.shadow_chatencryption.Crypt;

public class Main {

	static Socket individualServer = null;
	public static String portNumber = null;
	public static String welcomeMSG = null;
	public static String uid = null;
	public static List<DataOutputStream> OUTALL = new ArrayList<DataOutputStream>();
	public static boolean running = true;
	public static Thread server;

	static BufferedWriter log;

	public static String cryptKey = "my_super_secret_key_ho_ho_ho";
	public static Crypt crypt1;

	synchronized public static void add(DataOutputStream opStream) {
		OUTALL.add(opStream);
	}

	synchronized public static void remove(DataOutputStream opStream) {
		OUTALL.remove(opStream);
	}

	synchronized public static void sendToAll(String msg) {
		logInfo(msg);
		for (DataOutputStream dop : OUTALL) {
			try {
				dop.writeUTF(crypt1.encrypt(msg));
			} catch (IOException ex) {
				// Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null, ex);
			}
		}
	}

	public static void main(String[] args) throws Exception {

		setUp();
		new Server_GUI().start(); // Start server window

	}

	static File path = new File(System.getProperty("user.home") + File.separator + "ShadowChat");
	static File config = new File(path + File.separator + "config.gh");
	static File logs = new File(path + File.separator + "log.gh");

	private static void setUp() throws Exception {

		if (!config.exists()) {
			path.mkdirs();
			config.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(config));
			out.write("PORT:9000");
			out.newLine();
			out.write("Welcome-MSG:Welcome to the Server (user)");
			out.newLine();
			out.write("UUID: ");
			out.flush();
			out.close();
		}

		if (!logs.exists()) {
			logs.createNewFile();
		}

		log = new BufferedWriter(new FileWriter(logs));

		BufferedReader in = new BufferedReader(new FileReader(config));
		String settings;
		while ((settings = in.readLine()) != null) {
			if (settings.startsWith("PORT:")) {
				portNumber = settings.split(":")[1];
			} else if (settings.startsWith("Welcome-MSG")) {
				welcomeMSG = settings.split(":")[1];
			} else if (settings.startsWith("UUID")) {
				uid = settings.split(":")[1];
			}
		}
		in.close();
	}

	private static void getCryptKey() {
		try {
			URL url = new URL("http://chat.ghosthacks96.tk/projects/shadowchat/api.php?type=server&crypt=" + uid);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
			String code;
			while ((code = in.readLine()) != null) {
				if (code.startsWith("ERROR")) {
					JOptionPane.showMessageDialog(null,
							"There was a fatal error please restart the software, shutting down after this msg.");
					System.exit(1);
				} else {
					cryptKey = code;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public static boolean textArea_1() {
		server = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					ServerSocket ss = new ServerSocket(Integer.parseInt(portNumber));
					Server_GUI.textArea_1.setText("Server Started\n\n");
					while (true) {
						individualServer = ss.accept();

						logInfo("User connected from ip: " + individualServer.getInetAddress());

						Thread clientThread = new Thread(new MyNewClientThread(individualServer));
						clientThread.start();
						running = true;
					}
				} catch (IOException ex) {
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

		});

		if (uid == "0") {
			JOptionPane.showMessageDialog(null,
					"Please Edit the settings and put in your servers UUID from the website! The server can not start until the correct UUID is added!");
			return false;
		} else {
			getCryptKey();
			server.start();
			crypt1 = new Crypt(cryptKey);
		}
		return true;
	}

	public static void openSettingsPage() {
		new Settings_Window().start();
	}

	public static void saveSettings() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(config));
			out.write("PORT:" + portNumber);
			out.newLine();
			out.write("Welcome-MSG:" + welcomeMSG);
			out.newLine();
			out.write("UUID:" + uid);
			out.close();
		} catch (Exception e) {

		}
	}

	static Date date = new Date();
	static SimpleDateFormat df = new SimpleDateFormat("M-d-y__h:m");

	public static void logInfo(String error) {
		System.out.println(df.format(date) + " :: " + error);
		try {
			log.append(df.format(date) + " :: " + error);
			log.newLine();
			log.flush();
		} catch (Exception e) {

		}
	}
}