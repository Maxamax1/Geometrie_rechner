
public class Kreis extends Figur {
// Variables
private Double r;
private Double d;
// Getter & Setter
public Double getR() {
	return r;
}
public void setR(Double r) {
	this.r = r;
}
public Double getD() {
	return d;
}
public void setD(Double d) {
	this.d = d;
}

//Constructor
public Kreis() {
	super();
}
public Kreis(Double d, Double u, Double r, Double a) {
	super();
	this.d = d;
	U = u;
	this.r = r;
	A = a;
}
// Functions
/* 
 * U = Umfang
 * A = Fläche
 * d = Durchmesser
 * r = Radius
 */

/**
 * This is the Main Calculation for the Circle
 * The Calculation checks for every Possible Combination of the Inputs. 
 */
public void calculate() {
	if(d != null) {
		r = (d/2);
		A();
		U();
	} else if (U != null) {
		r = U / Math.PI /2;
		d();
		A();
	}else if (r != null) {
		U();
		d();
		A();
	}else if (A != null) {
		r = Math.sqrt(A/Math.PI);
		U();
		d();
	}	
}
private void A() {
	A = Math.PI * Math.pow(r, 2);
}
private void U() {
	U = 2*Math.PI*r;
}
private void d() {
	d = 2*r;
}
}
