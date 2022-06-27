//import java.util.ArrayList;

public class Rechteck extends Quadrat {
//Variables

private Double sideb;
private Double alpha;
private Double beta;
private Double gamma;
private Double delta;
// Getter & Setter


public Double getSideb() {
	return sideb;
}
public void setSideb(Double sideb) {
	this.sideb = sideb;
}
public Double getAlpha() {
	return alpha;
}
public void setAlpha(Double alpha) {
	this.alpha = alpha;
}
public Double getBeta() {
	return beta;
}
public void setBeta(Double beta) {
	this.beta = beta;
}
public Double getGamma() {
	return gamma;
}
public void setGamma(Double gamma) {
	this.gamma = gamma;
}
public Double getDelta() {
	return delta;
}
public void setDelta(Double delta) {
	this.delta = delta;
}
//Constructor
public Rechteck() {
	super();
}
public Rechteck(Double sidea, Double sideb, Double a, Double d, Double alpha, Double beta, Double gamma, Double delta) {
	super();
	this.sideb = sideb;
	this.A = a;
	this.alpha = alpha;
	this.beta = beta;
	this.gamma = gamma;
	this.delta = delta;
}

// Functions
/* 
 * U = Umfang
 * A = Fläche
 * sidea = Seite a
 * sideb = Seite b
 * d = Diagonale
 * alpha = Winkel Alpha
 * beta = Winkel Beta
 * gamma = Winkel Gamma
 * Delta = Winkel Delta
 */

/**
 * This is the Main Calculation for the Rechteck
 * The Calculation checks for every Possible Combination of the Inputs. 
 * @param test
 */	
public void calculate() {	
	if (sidea != null) {
		if (sideb != null) {
			U();
			A();
			d = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2));
			alpha = Math.toDegrees(Math.atan(sideb/sidea));
			beta = 90 - alpha;
			gamma = 180 - 2*alpha;
			delta = 180 - gamma;
		} else if (A != null) {
			sideb = A/sidea;
			U();
			d = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2));
			alpha = Math.toDegrees(Math.atan(sideb/sidea));
			beta = 90 - alpha;
			gamma = 180 - 2*alpha;
			delta = 180 - gamma;
		} else if (U != null && U>((double) sidea*2)) {
			sideb = U/2 -sidea;
			A();
			d = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2));
			alpha = Math.toDegrees(Math.atan(sideb/sidea));
			beta = 90 - alpha;
			gamma = 180 - 2*alpha;
			delta = 180 - gamma;
		} else if (d != null) {
			sideb = Math.sqrt(Math.pow(d, 2) - Math.pow(sidea, 2));
			U();
			A();
			alpha = Math.toDegrees(Math.atan(sideb/sidea));
			beta = 90 - alpha;
			gamma = 180 - 2*alpha;
			delta = 180 - gamma;
		} else if (alpha != null && alpha < 90) {
			beta = 90 - alpha;
			gamma = 180 - 2*alpha;
			delta = 180 - gamma;
			d = sidea/Math.cos(Math.toRadians(alpha));
			sideb = Math.sqrt(Math.pow(d, 2) - Math.pow(sidea, 2));
			U();
			A();
		} else if (beta != null && beta < 90) {
			alpha = 90 - beta;
			gamma = 180 - 2*alpha;
			delta = 180 - gamma;
			d = sidea/Math.cos(Math.toRadians(alpha));
			sideb = Math.sqrt(Math.pow(d, 2) - Math.pow(sidea, 2));
			U();
			A();
		} else if (gamma != null && gamma < 180) {
			alpha = 90 - gamma/2;
			beta = 90 - alpha;
			delta = 180 - gamma;
			d = sidea/Math.cos(Math.toRadians(alpha));
			sideb = Math.sqrt(Math.pow(d, 2) - Math.pow(sidea, 2));
			U();
			A();
		} else if (delta != null && delta < 180) {
			gamma = 180 - delta;
			alpha = 90 - gamma/2;
			beta = 90 - alpha;
			d = sidea/Math.cos(Math.toRadians(alpha));
			sideb = Math.sqrt(Math.pow(d, 2) - Math.pow(sidea, 2));
			U();
			A();
		}
	} else if (sideb != null) {
		if (A != null) {
			sidea = A/sideb;
			U();
			d = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2));
			alpha = Math.toDegrees(Math.atan(sideb/sidea));
			beta = 90 - alpha;
			gamma = 180 - 2*alpha;
			delta = 180 - gamma;
		} else if (U != null && U>((double) sideb*2)) {
			sidea = U/2-sideb;
			A();
			d = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2));
			alpha = Math.toDegrees(Math.atan(sideb/sidea));
			beta = 90 - alpha;
			gamma = 180 - 2*alpha;
			delta = 180 - gamma;
		} else if (d != null) {
			sidea = Math.sqrt(Math.pow(d, 2) - Math.pow(sideb, 2));
			U();
			A();
			alpha = Math.toDegrees(Math.atan(sideb/sidea));
			beta = 90 - alpha;
			gamma = 180 - 2*alpha;
			delta = 180 - gamma;
		} else if (alpha != null && alpha < 90) {
			beta = 90 - alpha;
			gamma = 180 - 2*alpha;
			delta = 180 - gamma;
			d = sideb/Math.sin(Math.toRadians(alpha));
			sidea = Math.sqrt(Math.pow(d, 2) - Math.pow(sideb, 2));
			U();
			A();
		} else if (beta != null && beta < 90) {
			alpha = 90 - beta;
			gamma = 180 - 2*alpha;
			delta = 180 - gamma;
			d = sideb/Math.sin(Math.toRadians(alpha));
			sidea = Math.sqrt(Math.pow(d, 2) - Math.pow(sideb, 2));
			U();
			A();
		} else if (gamma != null && gamma < 180) {
			alpha = 90 - gamma/2;
			beta = 90 - alpha;
			delta = 180 - gamma;
			d = sideb/Math.sin(Math.toRadians(alpha));
			sidea = Math.sqrt(Math.pow(d, 2) - Math.pow(sideb, 2));
			U();
			A();
		} else if (delta != null && delta < 180) {
			gamma = 180 - delta;
			alpha = 90 - gamma/2;
			beta = 90 - alpha;
			d = sideb/Math.sin(Math.toRadians(alpha));
			sidea = Math.sqrt(Math.pow(d, 2) - Math.pow(sideb, 2));
			U();
			A();			
		}
	} else if (A != null) {
		if (U != null) {
			sidea = (U - Math.sqrt(Math.pow(U, 2) - 16*A)) /4;
			sideb = (U + Math.sqrt(Math.pow(U, 2) - 16*A)) /4;
			d = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2));
			alpha = Math.toDegrees(Math.atan(sideb/sidea));
			beta = 90 - alpha;
			gamma = 180 - 2*alpha;
			delta = 180 - gamma;
		} else if (d != null) {
			sidea = Math.sqrt(2*(Math.pow(d, 2)-Math.sqrt(Math.pow(d, 4)-4*Math.pow(A, 2))))/2;
			sideb = Math.sqrt(2*(Math.pow(d, 2)+Math.sqrt(Math.pow(d, 4)-4*Math.pow(A, 2))))/2;
			U();
			alpha = Math.toDegrees(Math.atan(sideb/sidea));
			beta = 90-alpha;
			gamma = 180 - 2*alpha;
			delta=180-gamma;
		} else if (alpha != null && alpha < 90) {
			beta = 90-alpha;
			gamma = 180 - 2*alpha;
			delta=180-gamma;
			sideb = Math.sqrt(A*Math.tan(Math.toRadians(alpha)));
			sidea = A/sideb;
			U();
			d = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2));
		} else if (beta != null && beta < 90) {
			alpha = 90-beta;
			gamma = 180 - 2*alpha;
			delta=180-gamma;
			sideb = Math.sqrt(A*Math.tan(Math.toRadians(alpha)));
			sidea = A/sideb;
			U();
			d = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2));
		} else if (gamma != null && gamma < 180) {
			alpha = 90-gamma/2;
			beta = 90 - alpha;
			delta=180-gamma;
			sideb = Math.sqrt(A*Math.tan(Math.toRadians(alpha)));
			sidea = A/sideb;
			U();
			d = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2));
			
		} else if (delta != null && delta < 180) {
			gamma = 180 - delta;
			alpha=90-gamma/2;
			beta = 90-alpha;
			sideb = Math.sqrt(A*Math.tan(Math.toRadians(alpha)));
			sidea = A/sideb;
			U();
			d = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2));
		}
	} else if (U != null) {	
		if (alpha != null && alpha < 90) {
			beta = 90-alpha;
			gamma = 180 - 2*alpha;
			delta=180-gamma;
			sidea = U/(2*Math.tan(Math.toRadians(alpha)) +1);
			sideb = U/2 - sidea;
			A();
			d = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2));
		} else if (beta != null && beta < 90) {
			alpha = 90-beta;
			gamma = 180 - 2*alpha;
			delta=180-gamma;
			sidea = U/(2*Math.tan(Math.toRadians(alpha)) +1);
			sideb = U/2 - sidea;
			A();
			d = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2));
		} else if (gamma != null && gamma < 180) {
			alpha = 90-gamma/2;
			beta = 90 - alpha;
			delta=180-gamma;
			sidea = U/(2*Math.tan(Math.toRadians(alpha)) +1);
			sideb = U/2 - sidea;
			A();
			d = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2));
		} else if (delta != null && delta < 180) {
			gamma = 180 - delta;
			alpha=90-gamma/2;
			beta = 90-alpha;
			sidea = U/(2*Math.tan(Math.toRadians(alpha)) +1);
			sideb = U/2 - sidea;
			A();
			d = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2));
		} else if (d != null) {
			if ((U>(d*2)) && (d>(U/((double) 1000/354)))) {
			sidea = (U - Math.sqrt((8*Math.pow(d, 2))-Math.pow(U, 2)))/4;
			sideb = (U + Math.sqrt((8*Math.pow(d, 2))-Math.pow(U, 2)))/4;
			A();
			alpha = Math.toDegrees(Math.atan(sideb/sidea));
			beta = 90 - alpha;
			gamma = 180 - 2*alpha;
			delta = 180-gamma;
			
			}
		}
	} else if (d != null) {
	if (alpha != null && alpha < 90) {
		beta = 90-alpha;
		gamma = 180 - 2*alpha;
		delta=180-gamma;
		sidea = d*Math.cos(Math.toRadians(alpha));
		sideb = Math.sqrt(Math.pow(d, 2) - Math.pow(sidea, 2));
		U();
		A();
	} else if (beta != null && beta < 90) {
		alpha = 90-beta;
		gamma = 180 - 2*alpha;
		delta=180-gamma;
		sidea = d*Math.cos(Math.toRadians(alpha));
		sideb = Math.sqrt(Math.pow(d, 2) - Math.pow(sideb, 2));
		U();
		A();
	} else if (gamma != null && gamma < 180) {
		alpha = 90-gamma/2;
		beta = 90 - alpha;
		delta=180-gamma;
		sidea = d*Math.cos(Math.toRadians(alpha));
		sideb = Math.sqrt(Math.pow(d, 2) - Math.pow(sideb, 2));
		U();
		A();
	} else if (delta != null && delta < 180) {
		gamma = 180 - delta;
		alpha=90-gamma/2;
		beta = 90-alpha;
		sidea = d*Math.cos(Math.toRadians(alpha));
		sideb = Math.sqrt(Math.pow(d, 2) - Math.pow(sideb, 2));
		U();
		A();
	} }
	if (sidea!=null && sideb!=null && A!=null && U!=null && d!=null && alpha!=null && beta!=null && gamma!=null && delta!=null) {
	if (sidea>0 && sideb>0 && A>0 && U>0 && d>0 && alpha>0 && beta>0 && gamma>0 && delta>0) {
	//vartotest(test);
	} else {
		reset();
	}
	} else {
		reset();
	}
}
/**
 * Resets all parameters if the Rechteck is not valid.
 */
private void reset() {
	sidea = null;
	sideb = null;
	A = null;
	U=null;
	d=null;
	alpha=null;
	beta=null;
	gamma=null;
	delta=null;
}
private void U() {
	 U = 2*(sidea + sideb);
}
private void A() {
	A = sidea * sideb;
}
}
