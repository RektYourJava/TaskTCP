import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Serveur {
	public static final int service = 9876;
	private ServerSocket srv;
	static Socket clt;
	ArrayList<Lecture> list = new ArrayList<Lecture>();
	ArrayList<String> message_recu = new ArrayList<String>();
	ArrayList<Task> listTask = new ArrayList<Task>();

	public Serveur() throws IOException {
		srv = new ServerSocket(service);
	}

	private void attente_connection() {
		while (true) {
			try {
				clt = srv.accept();
				Lecture l = new Lecture(clt);
				list.add(l);
				l.showMenu();
				l.start();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erreur Client");
			}
		}
	}

	public String showTask() {
		String msg = "";
		for (int i = 0; i < listTask.size(); i++) {
			msg += listTask.get(i).toString() + "\n";
		}
		if(msg.length() == 0){
			return "Liste de tache vide";
		}
		return msg;
	}

	public boolean executantExist(String pseudo, String tache) {
		int n = Integer.parseInt(tache);
		if (listTask.get(n).getExecutor().equals(pseudo)) {
			return true;
		}
		return false;
	}

	class Lecture extends Thread {
		Socket s_client;
		PrintWriter out;
		BufferedReader inq;
		String phrase;
		InputStream is;

		public Lecture(Socket s) {
			s_client = s;
		}

		public void reponse() throws IOException {
			while (true) {
				phrase = new String();
				is = s_client.getInputStream();
				inq = new BufferedReader(new InputStreamReader(is));
				phrase = inq.readLine();
				out = new PrintWriter(s_client.getOutputStream(), true);

				if (phrase.equals("3")) {
					out.println(showTask());
				} else {
					String[] parts = phrase.split(":");
					if (parts[0].equals("1") && (parts.length == 4)) {
						Task t = new Task(parts[2], parts[3], parts[1]);
						listTask.add(t);
						out.println("Tache créée | Tache : " + parts[1] + " | Createur : " + parts[2]
								+ " | Executant : " + parts[3] + " | n°" + t.getNumTask());
					} else if (parts[0].equals("2") && parts.length == 3) {
						try {
							if (executantExist(parts[1], parts[2])) {
								listTask.get(Integer.parseInt(parts[2])).changeState();
								out.println(
										"Tache n°" + listTask.get(Integer.parseInt(parts[2])).getNumTask() + " done");
							} else {
								out.println("Tache ou executant inexistant");
							}
						} catch (Exception e) {
							out.println("Erreur de syntaxe");
						}
					} else if (parts[0].equals("4") && parts.length == 2) {
						try {
							listTask.remove(Integer.parseInt(parts[1]));
							out.println("Tache supprimée");
						} catch (Exception e) {
							out.println("Tache inexistante");
						}
					} else {
						out.println("Erreur de syntaxe");
					}
				}
			}

		}

		public void showMenu() throws IOException {
			phrase = "1-Créer Taches(Saisir 1:nom_tache:nom_createur:nom_executeur)"
					+ "\n2-Executer Tache(Saisir 2:nom_executant:numero_tache)" + "\n3-Afficher les Taches(Saisir 3)"
					+ "\n4-Supprimer Tache(Saisir 4:num_tache)";
			out = new PrintWriter(clt.getOutputStream(), true);
			out.println(phrase);
		}

		public void run() {
			try {
				reponse();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

	}

	public static void main(String[] args) throws IOException {
		Serveur s = new Serveur();
		System.out.println("Server OK");
		s.attente_connection();
	}

}
