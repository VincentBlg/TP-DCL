package utilitaires;

public class Couple<T1, T2> {
	public final T1 un;
	public final T2 deux;
	public Couple(T1 un, T2 deux){
		this.un = un;
		this.deux = deux;
	}
	public static <T1, T2> Couple<T1, T2> creer(T1 h, T2 k) {
		return new Couple<>(h, k);
	}
	@Override
	public String toString() {
		return "(" + this.un + ", " + this.deux + ")";
	}
}
