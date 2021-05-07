package tk.ghosthacks96.shadow_client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import tk.ghosthacks96.shadow_client.encryption.Crypt;
import tk.ghosthacks96.shadow_client.gui.Login_Window;

public class Main {
	public static String username;
	static String cryptKey = "my_super_secret_key_ho_ho_ho";
	public static Crypt crypt;
	
	
	public static void main(String[] args) {
		
		new Login_Window().start();
		
	}
	
	
	public static String getUsername() {
		return username;
	}
	public static boolean sendLogin(String user, String pass) {
		
		try {
			URL url = new URL("http://192.168.0.12/projects/shadowchat/api.php?login=1&user="+user+"&pass="+pass);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
			String code;
			while((code = in.readLine())!= null) {
				if(code.equalsIgnoreCase("0")) {
					username = user;
					Login_Window.frame.setVisible(false);
					Login_Window.frame.dispose();
					Client.start();
					return true;
				}else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
	}

}
