package tk.ghosthacks96.shadow_client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import tk.ghosthacks96.shadow_client.gui.Client_GUI;

class ListenFromServer implements Runnable {

    public ListenFromServer() {
    }

    String inString;
    boolean serverClosed;

    @Override
    public void run() {
        try {
            while (true) {
                inString = Client.IN.readUTF();
               // System.out.println(inString);
                inString =  Main.crypt.decrypt(inString);
                //System.out.println(inString);
                if(inString.startsWith("CMD:")) {
                	String[] cmd = inString.split(":");
                	if(cmd[1].equalsIgnoreCase("shutdown")) {
                		JOptionPane.showMessageDialog(null,"The Server has shutdown.");
                	}
                }else {
                	Client_GUI.textArea.append("\n" + inString);
                }
            }
        } catch (Exception ex) {
            serverClosed = true;
        }
        try {
            Client.IN.close();
            Client.OUT.close();
            Client.client.close();
            if(serverClosed) {
            	JOptionPane.showMessageDialog(null, "The Server has shutdown!");
            	System.exit(1);
            }
        } catch (IOException ex) {
            Logger.getLogger(ListenFromServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
