

public class Quadrat extends Figur {
// Variables
protected Double sidea;
protected Double d;
// Getter & Setter
public Double getSidea() {
	return sidea;
}
public void setSidea(Double a) {
	this.sidea = a;
}
public Double getD() {
	return d;
}
public void setD(Double d) {
	this.d = d;
}

// Constructor
public Quadrat() {
	super();
}
public Quadrat(Double a, Double a2, Double u, Double diag) {
	super();
	this.sidea = a;
	A = a2;
	U = u;
	this.d = diag;
}
// Functions
/* 
 * U = Umfang
 * A = Fläche
 * sidea = Seite a
 * d = Diagonale
 */
/**
 * This is the Main Calculation for the Quadrat
 * The Calculation checks for every Possible Combination of the Inputs. 
 */	
public void calculate() {
if (sidea != null) {
	A = flaeche();
	U=umfang();
	d=diag();
} else if (U != null) {
	sidea = U/4;
	A = flaeche();
	d = diag();
} else if (A != null) {
	sidea = Math.sqrt(A);
	U=umfang();
	d = diag();
} else if (d != null) {
	sidea = d / Math.sqrt(2);
	A = flaeche();
	U=umfang();
}}
private double flaeche() {
	return sidea*sidea;
}

private double umfang() {
	return 4*sidea;
}

private double diag() {
	return Math.sqrt(Math.pow(sidea, 2)+Math.pow(sidea, 2));
}}