package diophante;

import utilitaires.Couple;

/*
 * Comment approcher un réel par une fraction ?
 * On souhaite trouver une méthode 
 * - qui approche l'ensemble des flottants, avec une
 *  bonne précision relative, 
 * - qui associe à un réel rationnel p/q cette même fraction, ou 
 *   mieux sa forme réductible.
 * La solution naïve de multiplier par une puissance de 10 et de 
 * prendre la partie entière puis de diviser par cette même 
 * puissance ne fonctionne pas. Elle ne couvre pas l'ensemble des 
 * flottants (par exemple ceux inférieurs à l'inverse du facteur
 *  multiplicatif) et ne permet pas de retrouver une fraction donnée.
 * 
 * L'approximation diophantienne fournit la méthode recherchée.  
 * r : réel, E(r) : partie entière de r, D(r) : partie décimale
 * r = r(0) = E(r) + D(r) 
 *        = E(r) si D(r) = 0 (terminé)
 *        = E(r) + 1/(1 / D(r)) si D(r) != 0
 * 1 / D(r) = r(1) = E(r(1)) + D(r(1)) etc.
 * -> Développement en fraction continue
 * -> Approximation au rang n en prenant D(r(n)) = 0 
 * *  (convergente vers r(0))
 * -> Approximation par une fraction h(n) / k(n)
 * -> Relation de récurrence entre les couples h(n) / k(n)
 * - voir https://fr.wikipedia.org/wiki/Fraction_continue
 * - h(-2) / k(-2) = 0 / 1 (notation symbolique pour un couple)
 * - h(-1) / k(-1) = 1 / 0 
 * - h(n)  / k(n)  = 
 *     E(r(n)) * h(n-1) + h(n-2) / E(r(n)) * k(n-1) + k(n-2)  
 * Calcul par itérations successives
 * - arrêt lorsque l'approximation est suffisante ou lorsque la 
 *   partie décimale est trop petite. 
 */
class Etat {
	double r;
	int h2;
	int h1;
	int k2;
	int k1;
}

class EtatReduction {
	Couple<Integer, Integer> r;
	int h2;
	int h1;
	int k2;
	int k1;

}

public class Approximation {
	public static boolean MODE_VERBEUX = true;
	public static double PRECISION = 1e-15;
	public static double INVERSE_INT_MIN = 1e-9;

	/*
	 * Précondition : Abs(e.r - e.p) > INVERSE_INT_MIN
	 */
	private static Etat suivant(Etat e) {
		int p = (int) Math.floor(e.r);
		Etat ne = new Etat();
		ne.r = 1.0 / (e.r - p); // inverse de la partie décimale
		// équations de récurrence
		ne.h2 = e.h2 * p + e.h1;
		ne.h1 = e.h2;
		ne.k2 = e.k2 * p + e.k1;
		ne.k1 = e.k2;
		return ne;
	}
	
	private static EtatReduction suivant(EtatReduction e) {
		int p = e.r.un / e.r.deux;
		var ne = new EtatReduction();
		ne.r = Couple.creer(e.r.deux, e.r.un % e.r.deux); // inverse de la partie décimale
		// équations de récurrence
		ne.h2 = e.h2 * p + e.h1;
		ne.h1 = e.h2;
		ne.k2 = e.k2 * p + e.k1;
		ne.k1 = e.k2;
		return ne;
	}

	private static int numerateur(Etat e) {
		int p = (int) Math.floor(e.r);
		return e.h2 * p + e.h1;
	}

	private static int denominateur(Etat e) {
		int p = (int) Math.floor(e.r);
		return e.k2 * p + e.k1;
	}

	private static int numerateur(EtatReduction e) {
		int p = e.r.un / e.r.deux;
		return e.h2 * p + e.h1;
	}

	private static int denominateur(EtatReduction e) {
		int p = e.r.un / e.r.deux;
		return e.k2 * p + e.k1;
	}

	public static Couple<Integer, Integer> approximation(double r) {
		double abs = Math.abs(r);
		boolean signe = (r >= 0);
		Etat courant = new Etat();
		courant.r = abs;
		courant.h2 = 1;
		courant.h1 = 0;
		courant.k2 = 0;
		courant.k1 = 1;
		int n = 0;
		while (estRelativementTropLoin(abs, numerateur(courant), denominateur(courant))
				&& partieDecimaleAssezGrande(courant.r)) {
			courant = suivant(courant);
			n++;
		}
		int h = numerateur(courant);
		int k = denominateur(courant);

		h = signe ? h : -h;
		return Couple.creer(h, k);
	}

	private static boolean partieDecimaleAssezGrande(double r) {
		int p = (int) Math.floor(r);
		boolean condition = (r - p) > INVERSE_INT_MIN;
		if (MODE_VERBEUX && !condition) {
			System.out.println("- Dépassement : " + (r - p));
		}
		return condition;
	}

	private static boolean estRelativementTropLoin(double r, int h, int k) {
		boolean condition = (Math.abs(r - (double) h / (double) k) > r * PRECISION);
		if (MODE_VERBEUX && !condition) {
			
		}
		return condition;
	}

	public static Couple<Integer, Integer> reduction(Couple<Integer, Integer> r) {
		var rN = Math.abs(r.un);
		var rD = Math.abs(r.deux);
		boolean signe = ((rN / rD) >= 0);
		var courant = new EtatReduction();
		courant.r = Couple.creer(rN, rD);
		courant.h2 = 1;
		courant.h1 = 0;
		courant.k2 = 0;
		courant.k1 = 1;
		int n = 0;
		while ((courant.r.un % courant.r.deux) != 0) {
			courant = suivant(courant);
			n++;
		}
		if (MODE_VERBEUX)
			System.out.println("- Profondeur : " + n);
		int h = numerateur(courant);
		int k = denominateur(courant);

		h = signe ? h : -h;
		return Couple.creer(h, k);
	}

	public static void main(String[] args) {
		double r = Math.PI;
		System.out.println(r);
		Couple<Integer, Integer> a = approximation(r);
		System.out.println(a.un + " / " + a.deux);
		System.out.println((double) a.un / (double) a.deux);
		// Voir https://oeis.org/A327360.
		r = 34.0 / 6.0;
		System.out.println(r);
		a = approximation(r);
		System.out.println(a.un + " / " + a.deux);
		System.out.println((double) a.un / (double) a.deux);
		r = 517.0 / 23.0;
		System.out.println(r);
		a = approximation(r);
		System.out.println(a.un + " / " + a.deux);
		System.out.println((double) a.un / (double) a.deux);
		r = Math.E;
		System.out.println(r);
		a = approximation(r);
		System.out.println(a.un + " / " + a.deux);
		System.out.println((double) a.un / (double) a.deux);
		// // Voir http://oeis.org/A007676

		System.out.println("1 / 20 : " + reduction(Couple.creer(50, 1000)));
		System.out.println("1 / 3 : " + reduction(Couple.creer(34, 102)));
	}

}
