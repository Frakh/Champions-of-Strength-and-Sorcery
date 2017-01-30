package es.netclasses;

import game.carte.ICarte;
import game.deplacable.Joueur;
import mainapp.Appli;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class NetCodeInterface implements Runnable{

	private Joueur joueur;
	private Socket ioSocket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private List<PlayerData> listeJoueur;
	public static boolean THREAD_CONTINUES = true;

	public NetCodeInterface(String ip, int port, List<PlayerData> pdl, Joueur j) throws IOException {
		ioSocket = new Socket(ip, port);
		oos = new ObjectOutputStream(ioSocket.getOutputStream());
		ois = new ObjectInputStream(ioSocket.getInputStream());
		listeJoueur = pdl;
		this.joueur = j;
	}

	public ICarte carte() {
		try {
			return (ICarte) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void start() {
		new Thread(this).start();
	}

	public void sendThisPlayerData(Joueur joueur) {
		PlayerData pldt = new PlayerData();
		pldt.img = joueur.getImage(null);
		pldt.namae = Appli.name;
		pldt.xpos = joueur.getPosition().getX();
		pldt.ypos = joueur.getPosition().getY();
		try {
			oos.writeObject(pldt);
			oos.reset();
		} catch (IOException e) {
			System.out.println("IOException : " + e.getMessage());
		}
	}

	/**
	 * Methode d'obtention des données des autres joueurs
	 * Est censé obtenir un tableau de PlayerData ( PlayerData[] )
	 * @return PlayerData[]
	 */
	public PlayerData[] getPlayerDataFromServer() throws IOException, ClassNotFoundException {

		PlayerData[] arraypl;
		arraypl = (PlayerData[]) ois.readObject();

		synchronized (listeJoueur) {
			listeJoueur.clear();
			for (PlayerData pl : arraypl) {
				listeJoueur.add(pl);
			}
		}
		return arraypl;
	}

	@Override
	public void run() {

		int cpt = 0;
		while (THREAD_CONTINUES) {
			try {
				sendThisPlayerData(joueur);
				getPlayerDataFromServer();
				System.out.println("CPT Block de reception :" + cpt++);
			} catch (IOException e) {
				System.out.println("Arret du thread : etape bool");
				THREAD_CONTINUES = false;
				try {
					this.ioSocket.close();
				} catch (IOException e1) {
					throw new RuntimeException(e);
				}
				System.out.println("Arret du thread");
			} catch (ClassNotFoundException e) {
				System.out.println("Classe non trouvée : " + e.getMessage());
			}
		}
	}
}
