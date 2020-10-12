
public class RationalFraction implements IRational{
	private int numerator;
	private int denominator;
	

	
	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}

	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}

	@Override
	public Rational somme(Rational x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rational zero() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rational produit(Rational x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rational un() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rational oppose() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rational inverse() {
		return null;
		}
	

	@Override
	public double quotient() {
		// TODO Auto-generated method stub
		return (double)this.getNumerator()/this.getDenominator();
	}

	@Override
	public Rational build(int a, int b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rational build(double d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumerator() {
		// TODO Auto-generated method stub
		return this.numerator;
	}

	@Override
	public int getDenominator() {
		// TODO Auto-generated method stub
		return this.denominator;
	}
	



}
