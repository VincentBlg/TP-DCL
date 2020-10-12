import hierarchie.Corps;

public interface IRational extends Corps<Rational> {
	public double quotient();
	public int getNumerator();
	public int getDenominator();
	public Rational build(int a,int b);
	public Rational build(double d);
	

}
