package utilitaire;

import game.Partie;

import java.util.Scanner;

public class CommandReader extends BaseThread {

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);

		String commande = "";

		while (!commande.equals("exit")) {
			commande = sc.nextLine();

			switch (commande) {

				case "help":
					System.out.println("Liste des commandes :\n" +
							"help : demmander de l'aide\n" +
							"partie -j : obtenir la liste des parties joinables :\n" +
							"partie -r : obtenir la liste des parties en cours\n" +
							"partie -a : obtenir toutes les parties\n" +
							"exit : sortir\n" +
							"kill : stopper une partie, donner ensuite l'id de la partie");
					break;
				case "exit":
					stopServer();
					break;
				case "kill":
					System.out.print("Entrez l'id de la partie : ");
					int idpartie = sc.nextInt();
					Partie.killPartie(idpartie);
					break;
				case "partie -j":
					System.out.println(Partie.getPartiesJoinable());
					break;
				case "partie -r":
					System.out.println(Partie.getPartiesEnCours());
					break;
				case "partie -a":
					System.out.println("Parties joinables :\n" + Partie.getPartiesJoinable());
					System.out.println("Parties en cours :\n" + Partie.getPartiesEnCours());
					break;
			}
		}
	}

	public void stopServer() {

	}
}
