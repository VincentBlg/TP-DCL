
public class Rational {
	private int numerator;
	private int denominator;
	
	public static int pgcd(int a, int b) {
	    return b == 0 ? a : pgcd(b, a % b);
	} 


	public Rational ( int numerator, int denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException("can't divide by 0");
		}
		else {
		int pgcd = pgcd(numerator,denominator);
		this.denominator = denominator/pgcd;
		this.numerator = numerator/pgcd;
			
		}}

	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
	}
	
	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}

	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}

	public int getRational() {
		return numerator/denominator;
	}
	
	public void addRational(Rational r) {
	 int d = this.getDenominator()*r.getDenominator();
	 int n = this.getNumerator()*r.getDenominator() + this.getDenominator()*r.getNumerator();
	 this.setDenominator(d);
	 this.setNumerator(n);
	}
	
	public void multiplyRational(Rational r) {
		 int d = this.getDenominator()*r.getDenominator();
		 int n = this.getNumerator()*r.getNumerator();
		 this.setDenominator(d);
		 this.setNumerator(n);
		}
	
	public void reverseRational() {
		 int d = this.getNumerator();
		 int n = this.getDenominator();
		 this.setDenominator(d);
		 this.setNumerator(n);
		}
	
	public String toString() {
		if (getNumerator()%getDenominator() == 0) {
			return "" + getRational();
		}
		else {
			return "" + getNumerator() + '/' + getDenominator();
	}}
	
	public float getFloat() {
		return numerator/denominator;
	}
	
	public boolean equals(Object o) {
		return o!=null 
				&& o instanceof Rational
				&& (((Rational)o).getDenominator()== this.getDenominator())
				&& (((Rational)o).getNumerator()== this.getNumerator());
	}

	public static void main(String[] args) {
		Rational rational1 = new Rational(1,2);
		Rational rational2 = new Rational(9,2);
		Rational rational3 = new Rational(9,3);
		Rational rational4 = new Rational(2,4);
		
		System.out.println(rational1.toString());
		System.out.println(rational2.toString());
		System.out.println(rational3.toString());
		System.out.println(rational4.toString());
		System.out.println(rational3.equals(rational2));
		System.out.println(rational1.equals(rational4));
	}
}

