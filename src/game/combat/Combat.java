package game.combat;

import game.Heros;
import game.IUnite;

public class Combat {

	public static final int HAUTEURTERRAIN = 13;
	public static final int LARGEURTERRAIN = 20;
	public CaseCombat[][] terrainCombat;
	Heros armee1;
	Heros armee2;
	IUnite[] ArmeeGauche;
	IUnite[] ArmeeDroite;


	public Combat(Heros h1, Heros h2) {
		terrainCombat = new CaseCombat[HAUTEURTERRAIN][LARGEURTERRAIN];
		armee1 = h1;
		armee2 = h2;
		ArmeeGauche = new IUnite[7];
		ArmeeDroite = new IUnite[7];
	}

	public void initialiserCombat() {
		int nbTroupes1 = 0;
		int nbTroupes2 = 0;
		for (int i = 0; i < armee1.armee.length; i++) {
			if (armee1.armee[i] != null) {
				nbTroupes1++;
				ArmeeGauche[i] = armee1.armee[i];
				boostStats(ArmeeGauche[i], armee1.getAttaque(), armee1.getDefense(), armee1.getChance(), armee1.getMoral());
			}

		}
		for (int i = 0; i < armee2.armee.length; i++) {
			if (armee2.armee[i] != null) {
				nbTroupes1++;
				ArmeeDroite[i] = armee1.armee[i];
				boostStats(ArmeeDroite[i], armee2.getAttaque(), armee2.getDefense(), armee2.getChance(), armee2.getMoral());
			}

		}

		placerTroupes(nbTroupes1, nbTroupes2);


	}

	private void boostStats(IUnite unit, int attaque, int defense, int moral, int chance) {
		unit.setAttaque(unit.getAttaque() + attaque);
		unit.setDefense(unit.getDefense() + defense);
		unit.setMoral(unit.getMoral() + moral);
		unit.setChance(unit.getChance() + chance);
	}

	private void placerTroupes(int nbTroupes1, int nbTroupes2) {

		double step = ((double)HAUTEURTERRAIN)/((double)nbTroupes1+1);
		for (int i = 1; i <= nbTroupes1; ++i) {
			terrainCombat[(int)(step*i)][0].setUnit(ArmeeGauche[i-1]);
		}

		step = ((double)HAUTEURTERRAIN)/((double)nbTroupes2+1);
		for (int i = 1; i <= nbTroupes2; ++i) {
			terrainCombat[(int)(step*i)][LARGEURTERRAIN-1].setUnit(ArmeeDroite[i-1]);
		}
	}

}
