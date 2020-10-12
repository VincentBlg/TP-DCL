
import diophante.Approximation;
import utilitaires.Couple;

public class RationalQ {
	private double quotient;	
	
	public RationalQ(double quotient) {
		this.quotient = quotient;
		
	}
	
	public void setRationalQ(double d) {
		this.quotient = d;
	}
	
	public double getRationalQ() {
		return this.quotient;
	}
	
	
	public void addRationalQ(RationalQ r) {
		this.setRationalQ(r.getRationalQ() + this.getRationalQ());
	}
	
	public void multiplyRationalQ(RationalQ r) {
		this.setRationalQ(r.getRationalQ()*this.getRationalQ());
	}
	
	public void reverseRationalQ() {
		this.setRationalQ(1/this.getRationalQ());
	}
	
	
	public int getDenominator() {
		Couple<Integer, Integer> a = Approximation.approximation(this.getRationalQ());
		return a.deux;
	}
	
	public int getNumerator() {
		Couple<Integer, Integer> a = Approximation.approximation(this.getRationalQ());
		return a.un;
	}
	
	
	public static void main(String[] args) {
		RationalQ r = new RationalQ(0.333333333333333334);
		System.out.println(r.getNumerator());
		System.out.println(r.getDenominator());
		
	}

}