package tk.ghosthacks96.shadow_client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import tk.ghosthacks96.shadow_client.encryption.Crypt;
import tk.ghosthacks96.shadow_client.gui.Client_GUI;

public class Client {
   public static DataOutputStream OUT = null;
   public static Socket client = null;
   public static DataInputStream IN = null;

    public static void start() {
        Client chatter = new Client();
        
      	chatter.setUp();
      	chatter.letsChat();
    }

    private void setUp() {
        try {
            String ipOfserver = JOptionPane.showInputDialog("Enter the IP address of the Server :- ");
            String portNo = JOptionPane.showInputDialog("Enter the port number:- ");
            
            try {
    			URL url = new URL("http://chat.ghosthacks96.tk/projects/shadowchat/api.php?&crypt="+ipOfserver);
    			BufferedReader in = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
    			String code;
    			while((code = in.readLine())!= null) {
    				if(code.startsWith("ERROR")) {
    					JOptionPane.showMessageDialog(null, "There was a fatal error please restart the software, shutting down after this msg.");
    					System.exit(1);
    				}else {
    					Main.crypt = new Crypt(code);
    				}
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    			System.exit(1);
    		}
            
            client = new Socket(ipOfserver,Integer.parseInt(portNo));
            OUT = new DataOutputStream(client.getOutputStream());
            IN = new DataInputStream(client.getInputStream());
            
            OUT.writeUTF(Main.crypt.encrypt("CMD:LOGIN "+Main.getUsername()));
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).
                    log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    private void letsChat() {
        new Client_GUI().start();
        Thread listenFromServer = new Thread(new ListenFromServer());
        listenFromServer.start();
        }
}
