package tk.ghosthacks96.shadow_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import tk.ghosthacks96.shadow_chat.gui.Server_GUI;

public class MyNewClientThread implements Runnable{
    Socket newClient;
    DataInputStream IN;
    DataOutputStream OUT;
    
        
   public MyNewClientThread(Socket s)
	{
            this.newClient = s;
	}
   
    @Override
    public void run() {
        try {
            try {
                IN = new DataInputStream(newClient.getInputStream());
                OUT = new DataOutputStream(newClient.getOutputStream());
                Main.add(OUT);
                String str ,usr="guest-"+Main.OUTALL.size();
                try{
                    while((str = IN.readUTF())!=null)
                    {
                    	str = Main.crypt1.decrypt(str);
                    	Server_GUI.textArea_1.append("\n"+usr+":"+str);
                    	
                    	if(str.startsWith("CMD:")) {
                    		String[] cmd = str.split(":");
                    		if(cmd[1].startsWith("LOGIN")) {
                    			usr = cmd[1].split(" ")[1];
                    			String welcome = Main.welcomeMSG.replace("(user)", usr);
                    			Main.sendToAll(welcome);
                    		}
                    	}else {
                             Main.sendToAll(usr+":"+str);
                    	}
                    }
                }catch(Exception ex){
                	Main.sendToAll("System: the User "+usr+" has logged out!");
                	Server_GUI.textArea_1.append("\nSystem: the User "+usr+" has logged out!");
                } 
                }catch (IOException ex) {
                Logger.getLogger(MyNewClientThread.class.getName())
                        .log(Level.SEVERE, null, ex);
            } 
        Main.remove(OUT);
                IN.close();
                OUT.close();
                newClient.close();
        } catch (IOException ex) {
            Logger.getLogger(MyNewClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}