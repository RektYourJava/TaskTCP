import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {
	Socket s;
	InputStream is;
	OutputStream os;
	PrintWriter out;
	String lecture, pseudo, str;
	BufferedReader inq, clavier;

	public Client(InetAddress adresse, int port) throws IOException {
		s = new Socket(adresse, port);
		is = s.getInputStream();
		os = s.getOutputStream();
		clavier = new BufferedReader(new InputStreamReader(System.in));
		inq = new BufferedReader(new InputStreamReader(is));
		out = new PrintWriter(os, true);
		send_Message();
	}

	public void send_Message() {
		while (true) {
			try {
				new Ecoute().start();
				str = clavier.readLine();
				out.println(str);
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Server Disconnected");
			}
		}
	}

	class Ecoute extends Thread {

		public void run() {
			while (true) {
				try {
					System.out.println(inq.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		new Client(InetAddress.getLocalHost(),9876);
	}

}
