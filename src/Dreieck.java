//import java.util.ArrayList;

public class Dreieck extends Figur {
//variables
private Double sidea;
private Double sideb;
private Double sidec;
private Double alpha;
private Double beta;
private Double gamma;
private Double ha;
private Double hb;
private Double hc;
private Double Inradius;
private Double Umradius;
// Getter & Setter
public Double getSidea() {
	return sidea;
}
public void setSidea(Double a) {
	this.sidea = a;
}
public Double getB() {
	return sideb;
}
public void setB(Double b) {
	this.sideb = b;
}
public Double getC() {
	return sidec;
}
public void setC(Double c) {
	this.sidec = c;
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
public Double getHeighta() {
	return ha;
}
public void setHeighta(Double heighta) {
	this.ha = heighta;
}

public Double getHeightb() {
	return hb;
}
public void setHeightb(Double heightb) {
	this.hb = heightb;
}
public Double getHeightc() {
	return hc;
}
public void setHeightc(Double heightc) {
	this.hc = heightc;
}

public Double getInradius() {
	return Inradius;
}
public void setInradius(Double inradius) {
	Inradius = inradius;
}
public Double getUmradius() {
	return Umradius;
}
public void setUmradius(Double umradius) {
	Umradius = umradius;
}
// Constructor
public Dreieck() {
	super();
}
public Dreieck(Double a, Double b, Double c, Double alpha, Double beta, Double gamma, Double heighta, Double heightb, Double heightc, Double Inradius, Double Umradius) {
	super();
	this.sidea = a;
	this.sideb = b;
	this.sidec = c;
	this.alpha = alpha;
	this.beta = beta;
	this.gamma = gamma;
	this.ha = heighta;
	this.hb = heightb;
	this.hc = heightc;
	this.Inradius = Inradius;
	this.Umradius = Umradius;
}

//Functions
/**
 * Calculates all Values for the Triangle
 */
public void calculate() {
	//System.out.println(sidea);
	if(sidea != null) {
		if(sideb != null) {
			if(sidec != null) {
				if( !((sidea+sideb)<=sidec) && !((sidea+sidec)<=sideb) && !((sideb+sidec)<=sidea)){	
					getAngle("alpha");
					getAngle("beta");
					getAngle("gamma");
				}
			} else if (alpha != null) {
				getAngle("beta");
				getAngle("gamma");
				sidec =	(Math.sin(Math.toRadians(gamma))*sidea) /Math.sin(Math.toRadians(alpha));
			} else if (beta !=null) {
				getAngle("alpha");
				getAngle("gamma");
				sidec =	(Math.sin(Math.toRadians(gamma))*sidea) /Math.sin(Math.toRadians(alpha));
				
			} else if (gamma !=null) {
				sidec = Math.sqrt(Math.pow(sidea, 2)+Math.pow(sideb, 2)-2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
				getAngle("alpha");
				getAngle("beta");
				
			} else if (ha !=null) {	// 2 lösungen
				getAngle("gamma");
				sidec = (Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma))));
				getAngle("alpha");
				getAngle("beta");
				
			} else if (hb !=null) {
				A = hb*sideb/2;
				ha = 2*A/sidea;
				getAngle("gamma");
				sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
				getAngle("alpha");
				getAngle("beta");
				
			} else if (hc !=null) {
				getAngle("beta");
				getAngle("alpha");
				getAngle("gamma");
				sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if (U != null) {
					sidec = U - sidea - sideb;
					getAngle("alpha");
					getAngle("beta");
					getAngle("gamma");
					
				} else if (A != null) {
					ha = 2*A/sidea;
					getAngle("gamma");
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					getAngle("alpha");
					getAngle("beta");	
				}
			} else if (sidec !=null) {

				if (alpha != null) {
					getAngle("gamma");
					getAngle("beta");
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
				
				} else if (beta !=null) {
				sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
				getAngle("alpha"); 
				getAngle("gamma");
				
				} else if (gamma !=null) {
					getAngle("alpha");
					getAngle("beta");
				sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
				
				} else if (ha !=null) {
				sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
				getAngle("beta");
				getAngle("alpha");
				getAngle("gamma");
				
				} else if (hb !=null) {
				getAngle("gamma");
				getAngle("alpha"); 
				getAngle("beta"); 
				sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
				
				} else if (hc !=null) {
				A = hc*sidec/2;
				ha = 2*A/sidea;
				getAngle("beta"); 
				sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
				getAngle("alpha"); 
				getAngle("gamma"); 
				
				} else if (U != null) {
					sideb = U - sidea- sidec;
					getAngle("alpha"); 
					getAngle("beta"); 
					getAngle("gamma"); 
					
				} else if (A != null) {
					ha = 2*A/sidec;
					getAngle("beta"); 
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
					getAngle("alpha"); 
					getAngle("gamma"); 
					
				}
				
			} else if (alpha != null) {
				if (beta !=null) {
					getAngle("gamma"); 
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if (gamma !=null) {
					getAngle("beta"); 
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if (hb !=null) {
					sidec = hb/Math.sin(Math.toRadians(alpha));
					getAngle("gamma"); 
					getAngle("beta"); 
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(gamma)));
					
				} else if (hc !=null) {
					sideb = hc/Math.sin(Math.toRadians(alpha));
					getAngle("beta"); 
					getAngle("gamma"); 
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
				}
			} else if (beta !=null) {
				
				if (gamma !=null) {
					getAngle("alpha"); 
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if (ha !=null) {
					sidec = ha/Math.sin(Math.toRadians(beta));
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
					getAngle("alpha"); 
					getAngle("gamma"); 
					
				} else if (hb !=null) {
					hc = sidea/Math.sin(Math.toRadians(beta));
					getAngle("gamma"); 
					getAngle("alpha"); 
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					A = hb*sideb/2;
					sidec = 2*A/hc;
					
				} else if (A != null) {
					ha = 2*A/sidea;
					sidec = ha/Math.sin(Math.toRadians(beta));
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
					getAngle("alpha"); 
					getAngle("gamma"); 
					
				}
			} else if (gamma !=null) {
				
				if (ha !=null) {
					sideb = ha/Math.sin(Math.toRadians(gamma));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					getAngle("alpha"); 
					getAngle("beta"); 
					
				} else if (hc !=null) {
					getAngle("beta"); 
					getAngle("alpha"); 
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					A = hb*sideb/2;
					sidec = 2*A/hc;
					
				} else if (A != null) {
					ha = 2*A/sidea;
					sideb = ha/Math.sin(Math.toRadians(gamma));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					getAngle("alpha"); 
					getAngle("beta"); 
					
				}
			} else if (ha != null) {
				if (hb != null) {
					A = ha*sidea/2;
					sideb = 2*A/hb;
					getAngle("gamma"); 
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					getAngle("alpha"); 
					getAngle("beta"); 
					
				} else if (hc !=null) {
					A = ha*sidea/2;
					sidec = 2*A/hc;
					getAngle("beta"); 
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
					getAngle("alpha"); 
					
				}
			} else if (hb != null) {
				if (hc != null) {
					getAngle("gamma"); 
					getAngle("beta"); 
					getAngle("alpha");
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					A = hb*sideb/2;
					sidec = 2*A/hc;
					
				} else if (A != null) {
					sideb = 2*A/hb;
					ha = 2*A/sidea;
					getAngle("gamma"); 
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					getAngle("alpha"); 
					getAngle("beta"); 
					
				}
			} else if (hc != null) {
				if (A != null) {
					sidec = 2*A/hc;
					ha = 2*A/sidec;
					getAngle("beta"); 
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
					getAngle("alpha"); 
					getAngle("gamma"); 
				}
			}
		} else if(sideb != null) {
			if(sidec != null) {
				if(alpha != null) {
					sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					getAngle("beta"); 
					getAngle("gamma"); 
					
				} else if(beta != null) {
					getAngle("gamma"); 
					getAngle("alpha"); 
					sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					
				} else if(gamma != null) {
					getAngle("beta"); 
					getAngle("alpha"); 
					sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					
				} else if(ha != null) {
					getAngle("gamma"); 
					getAngle("beta"); 
					getAngle("alpha"); 
					sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					
				} else if(hb != null) {
					 getAngle("alpha"); 
					 sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					 getAngle("beta"); 
					 getAngle("gamma"); 
					 
				} else if(hc != null) {
					 A = hc * sidec/2;
					 hb = 2*A/sideb;
					 getAngle("alpha"); 
					 sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					 getAngle("beta"); 
					 getAngle("gamma"); 
					 
				} else if (U != null) {
					 sidea = U - sideb- sidec;
					 getAngle("alpha"); 
					 getAngle("beta"); 
					 getAngle("gamma"); 
					 
				} else if (A != null) {
					 hb = 2*A/sideb;
					 getAngle("alpha"); 
					 sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha))); 
					 getAngle("beta"); 
					 getAngle("gamma"); 
					 
				}
			} else if(alpha != null) {
				if(beta != null) {
					getAngle("gamma"); 
					sidea = sideb*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(beta));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if(gamma != null) {
					getAngle("beta"); 
					sidea = sideb*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(beta));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if(ha != null) {
					 hc = sideb*Math.sin(Math.toRadians(alpha));
					 getAngle("gamma"); 
					 getAngle("beta"); 
					 sidea = sideb*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(beta));
					 sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					 
				} else if(hb != null) {
					 sidec = hb/Math.sin(Math.toRadians(alpha));
					 sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					 getAngle("beta"); 
					 getAngle("gamma"); 
					 
				} else if (A != null) {
					 hb = 2*A/sideb;
					 sidec = hb/Math.sin(Math.toRadians(alpha));
					 sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					 getAngle("beta"); 
					 getAngle("gamma"); 
				}
			} else if(beta != null) {	
				if(gamma != null) {
					getAngle("alpha"); 
					sidea = sideb*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(beta));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if(ha != null) {
					sidec = ha/Math.sin(Math.toRadians(beta));
					getAngle("gamma"); 
					getAngle("alpha"); 
					sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					
				} else if(hc != null) {
					sidea = hc/Math.sin(Math.toRadians(beta));
					getAngle("alpha"); 
					getAngle("gamma"); 
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				}
			} else if(gamma != null) {
				if(hb != null) {
					A = hb*sideb/2;
					sidea = 2*A/ha;
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					getAngle("alpha"); 
					getAngle("beta"); 
					
				} else if(hc != null) {
					ha = sideb/Math.sin(Math.toRadians(gamma));
					getAngle("alpha"); 
					getAngle("beta"); 
					sidea = sideb*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(beta));
					A = ha*sidea/2;
					sidec = 2*A/hc;
					
				} else if (A != null) {
					ha = sideb*Math.sin(Math.toRadians(gamma));
					sidea = 2*A/ha;
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					getAngle("alpha"); 
					getAngle("beta"); 
					
				}
			} else if(ha != null) {
				if (hb != null) {
					A = hb*sideb/2;
					sidea = 2*A/ha;
					getAngle("gamma"); 
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					getAngle("alpha"); 
					getAngle("beta"); 
					
				} else if (hc != null) {
					getAngle("gamma"); 
					getAngle("alpha"); 
					getAngle("beta"); 
					sidea = sideb*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(beta));
					A = ha*sidea/2;
					sidec = 2*A/hc;
					
				} else if (A != null) {
					sidea = 2*A/ha;
					getAngle("gamma"); 
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					getAngle("alpha"); 
					getAngle("beta"); 
					
				}
			} else if(hb != null) {
				if(hc != null) {
					A = hb*sideb/2;
					sidec = 2*A/hc;
					getAngle("alpha"); 
					sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					getAngle("beta"); 
					getAngle("gamma"); 
					
				}
			}
		} else if(sidec != null) {
			if (alpha != null) {
				if(beta != null) {
					getAngle("gamma"); 
					sidea = sidec*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(gamma));
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
					
				} else if(gamma != null) {
					getAngle("beta"); 
					sidea = sidec*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(gamma));
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
					
				} else if(ha != null) {
					hb = sidec*Math.sin(Math.toRadians(alpha));
					getAngle("beta"); 
					getAngle("gamma"); 
					sidea = sidec*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(gamma));
					A = ha*sidea/2;
					sideb = 2*A/hb;
					
				} else if(hc != null) {
					A = hc*sidec/2;
					hb = sidec*Math.sin(Math.toRadians(alpha));
					sideb = 2*A/hb;
					sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					getAngle("beta"); 
					getAngle("gamma"); 
					
				} else if (A != null) {
					hb = sidec*Math.sin(Math.toRadians(alpha));
					sideb = 2*A/hb;
					sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					getAngle("beta"); 
					getAngle("gamma"); 
					
				}
			} else if(beta != null) {	
				if(gamma != null) {
				getAngle("alpha"); 
				sidea = sidec*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(gamma));
				sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
				
				} else if(hb != null) {
				ha = sidec*Math.sin(Math.toRadians(beta));
				getAngle("alpha"); 
				getAngle("gamma"); 
				sidea = sidec*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(gamma));
				A = ha * sidea/2;
				sideb = 2*A/hb;
				
				} else if(hc != null) {
				A = hc * sidec/2;
				ha = sidec*Math.sin(Math.toRadians(beta));
				sidea = 2*A/ha;
				sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
				getAngle("alpha"); 
				getAngle("gamma"); 
				
				} else if (A != null) {
					ha = sidec*Math.sin(Math.toRadians(beta));
					sidea = 2*A/ha;
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
					getAngle("alpha"); 
					getAngle("gamma"); 
					
				}
			} else if(gamma != null) {
				if(ha != null) {
					sideb = ha/Math.sin(Math.toRadians(gamma));
					getAngle("beta"); 
					getAngle("alpha"); 
					sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					
				} else if(hb != null) {
					sidea = hb/Math.sin(Math.toRadians(gamma));
					getAngle("alpha"); 
					getAngle("beta"); 
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
					
				} 
			} else if(ha != null) {
				if (hb != null) {
					getAngle("beta"); 
					getAngle("alpha"); 
					getAngle("gamma"); 
					sidea = sidec*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(gamma));
					A = ha*sidea/2;
					sideb = 2*A/hb;
					
				} else if (hc != null) {
					A = hc*sidec/2;
					sidea = 2*A/ha;
					getAngle("beta"); 
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
					getAngle("alpha"); 
					getAngle("gamma"); 
					
				} else if (A != null) {
					sidea = 2*A/ha;
					getAngle("beta"); 
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
					getAngle("alpha"); 
					getAngle("gamma"); 
					
				}
			} else if(hb != null) {
				if(hc != null) {
					A = hc*sidec/2;
					sideb = 2*A/hb;
					getAngle("alpha"); 
					sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					getAngle("beta"); 
					getAngle("gamma"); 
					
				} else if (A != null) {
					sideb = 2*A/hb;
					getAngle("alpha"); 
					sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					getAngle("beta"); 
					getAngle("gamma"); 
					
				}
			}
		} else if(alpha != null) {
			if(beta != null) {	
				if(ha != null) {
					getAngle("gamma"); 
					sideb = ha/Math.sin(Math.toRadians(gamma));
					sidea = sideb*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(beta));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if(hb != null) {
					getAngle("gamma"); 
					sidea = hb/Math.sin(Math.toRadians(gamma));
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if(hc != null) {
					getAngle("gamma"); 
					sidea = hc/Math.sin(Math.toRadians(beta));
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if (A != null) {
					getAngle("gamma"); 
					Double ru = Math.sqrt(A/(2*Math.sin(Math.toRadians(alpha))*Math.sin(Math.toRadians(beta))*Math.sin(Math.toRadians(gamma))));
					sidea = 2*ru*Math.sin(Math.toRadians(alpha));
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if (U != null) {
					getAngle("gamma"); 
					Double ru = U/(8*Math.cos(Math.toRadians(alpha/2))*Math.cos(Math.toRadians(beta/2))*Math.cos(Math.toRadians(gamma/2)));
					sidea = 2*ru*Math.sin(Math.toRadians(alpha));
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					sidec = U - sidea - sideb;
					
				}
			} else if(gamma != null) {
				if(ha != null) {
					getAngle("beta"); 
					sideb = ha/Math.sin(Math.toRadians(gamma));
					sidea = sideb*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(beta));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if(hb != null) {
					getAngle("beta"); 
					sidea = hb/Math.sin(Math.toRadians(gamma));
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if(hc != null) {
					getAngle("beta"); 
					sidea = hc/Math.sin(Math.toRadians(beta));
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				}
			} else if(ha != null) {
				if (hb != null) {
					sidec = hb/Math.sin(Math.toRadians(alpha));
					getAngle("beta"); 
					getAngle("gamma"); 
					sidea = sidec*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(gamma));
					A = ha*sidea/2;
					sideb = 2*A/hb;
					
				} else if (hc != null) {
					sideb = hc/Math.sin(Math.toRadians(alpha));
					getAngle("gamma"); 
					getAngle("beta"); 
					sidea = sideb*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(beta));
					A = ha*sidea/2;
					sidec = 2*A/hc;
					
				}
			} else if(hb != null) {
				if(hc != null) {
					sidec = hb/Math.sin(Math.toRadians(alpha));
					A = hc*sidec/2;
					sideb = 2*A/hb;
					sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					getAngle("beta"); 
					getAngle("gamma"); 
					
				} else if (A != null) {
					sideb = 2*A/hb;
					sidec = hb/Math.sin(Math.toRadians(alpha));
					sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					getAngle("beta"); 
					getAngle("gamma"); 
					
				}
			}
		} else if(beta != null) {
			if(gamma != null) {
				if(ha != null) {
					getAngle("alpha"); 
					sideb = ha/Math.sin(Math.toRadians(gamma));
					sidea = sideb*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(beta));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if(hb != null) {
					getAngle("alpha"); 
					sidea = hb/Math.sin(Math.toRadians(gamma));
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if(hc != null) {
					getAngle("alpha"); 
					sidea = hc/Math.sin(Math.toRadians(beta));
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if (A != null) {
					getAngle("alpha"); 
					double ru = Math.sqrt(A/(2*Math.sin(Math.toRadians(alpha))*Math.sin(Math.toRadians(beta))*Math.sin(Math.toRadians(gamma))));
					sidea = 2*ru*Math.sin(Math.toRadians(alpha));
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					
				} else if (U != null) {
					getAngle("alpha"); 
					Double ru = U /(8*Math.cos(Math.toRadians(alpha /2))*Math.cos(Math.toRadians(beta/2))*Math.cos(Math.toRadians(gamma/2)));
					sidea = 2*ru*Math.sin(Math.toRadians(alpha));
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					sidec = U - sidea - sideb;
					
				}
			} else if(ha != null) {
				if (hb != null) {
					sidec = hc/Math.sin(Math.toRadians(beta));
					getAngle("alpha"); 
					getAngle("gamma"); 
					sidea = sidec*Math.sin(Math.toRadians(alpha))/Math.sin(Math.toRadians(gamma));
					A = ha*sidea/2;
					sideb = 2*A/sidec;
					
				} else if (hc != null) {
					sidec = ha/Math.sin(Math.toRadians(beta));
					A = hc*sidec/2;
					sidea = 2*A/ha;
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
					getAngle("alpha"); 
					getAngle("gamma"); 
					
				} else if (A != null) {
					sidea = 2*A/ha;
					sidec = ha/Math.sin(Math.toRadians(beta));
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
					getAngle("alpha"); 
					getAngle("gamma"); 
					
				}
			} else if(hb != null) {
				if(hc != null) {
					sidea = hc/Math.sin(Math.toRadians(beta));
					getAngle("gamma"); 
					getAngle("alpha"); 
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					A = hb*sideb/2;
					sidec = 2*A/hc;
				}
			} else if (hc != null) {
				if (A != null) {
					sidec = 2*A/hc;
					sidea = 2*A/ha;
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
					getAngle("alpha"); 
					getAngle("gamma"); 
					
				}
			}
		} else if(gamma != null) {
			if(ha != null) {
				if (hb != null) {
					sideb = hb/Math.sin(Math.toRadians(gamma));
					A = hb*sideb/2;
					sidea = 2*A/ha;
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					getAngle("alpha"); 
					getAngle("beta"); 
					
				} else if (hc != null) {
					sideb = ha/Math.sin(Math.toRadians(gamma));
					getAngle("alpha"); 
					getAngle("beta"); 
					A = ha*sidea/2;
					sidec = 2*A/hc;
					
				} else if (A != null) {
					sidea = 2*A/ha;
					sideb = ha/Math.sin(Math.toRadians(gamma));
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					getAngle("alpha"); 
					getAngle("beta"); 
					
				}
			} else if(hb != null) {
				if(hc != null) {
					sidea = hb/Math.sin(Math.toRadians(gamma));
					getAngle("beta"); 
					getAngle("alpha"); 
					sideb = sidea*Math.sin(Math.toRadians(beta))/Math.sin(Math.toRadians(alpha));
					A = hb*sideb/2;
					sidec = 2*A/hc;
					
				} else if (A != null) {
					sideb = 2*A/hb;
					ha = sideb/Math.sin(Math.toRadians(gamma));
					sidea = 2*A/ha;
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					getAngle("alpha"); 
					getAngle("beta"); 
					
				}
			}
		} else if (ha != null) {
			if(hb != null) {
				if(hc != null) {
					sidea = 2*ha*Math.pow(hb, 2)*Math.pow(hc, 2) / Math.sqrt(Math.pow(hb, 2) * Math.pow(hc, 2)*(2 * Math.pow(ha, 2)*(Math.pow(hb, 2)+Math.pow(hc, 2)) - Math.pow(hb, 2) * Math.pow(hc, 2)) - Math.pow(ha, 4) * (Math.pow(hb, 4) - 2*Math.pow(hb, 2)*Math.pow(hc, 2) + Math.pow(hc, 4)));
					A = ha*sidea/2;
					sideb = 2*A/hb;
					sidec = 2*A/hc;
					getAngle("alpha"); 
					getAngle("beta"); 
					getAngle("gamma"); 
				} else if (A != null) {
					sidea = 2*A/ha;
					sideb = 2*A/hb;
					sidec = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sideb, 2) - 2*sidea*sideb*Math.cos(Math.toRadians(gamma)));
					getAngle("alpha"); 
					getAngle("beta"); 
					
				}
			} else if (hc != null) {
				if (A != null) {
					sidea = 2*A/ha;
					sidec = 2*A/hc;
					sideb = Math.sqrt(Math.pow(sidea, 2) + Math.pow(sidec, 2) - 2*sidea*sidec*Math.cos(Math.toRadians(beta)));
					getAngle("alpha"); 
					getAngle("gamma"); 
					
				}
			}
		} else if (hb != null) {
			if (hc != null) {
				if (A != null) {
					sideb = 2*A/hb;
					sidec = 2*A/hc;
					getAngle("alpha"); 
					sidea = Math.sqrt(Math.pow(sideb, 2) + Math.pow(sidec, 2) - 2*sideb*sidec*Math.cos(Math.toRadians(alpha)));
					getAngle("beta"); 
					getAngle("gamma"); 
					
				}
			}
		}
	if(sidea!=null && sideb!=null && sidec!= null && gamma!=null && alpha!=null && beta!=null) {
	

	

	if (!(ha!=null && hb!=null && hc!=null)) {
		ha = (sideb*Math.sin(Math.toRadians(gamma)));
		hb = (sidec*Math.sin(Math.toRadians(alpha)));
		hc = (sidea*Math.sin(Math.toRadians(beta)));
	}	else {
		System.out.println("test");
	}
	double s = (sidea+sideb+sidec)/2;
	A = Math.sqrt(s*(s-sidea)*(s-sideb)*(s-sidec));		
	Inradius = (A/s);
	U = (sidea + sideb + sidec); 
	
	Umradius = (sidea*sideb*sidec)/(4*Inradius*s);
//	if (sidea>0 && sidea>0 && sidec>0 && alpha>0 && beta>0 && gamma>0 && U>0 && hb>0 && A>0) {
//	Datenbank.save_dreieck(test);
//	}
	}
}
	private void getAngle(String search) {
		//Double angle=null;
		switch (search) {
		case "alpha":
			if (sideb != null && sidec !=null && sidea !=null) {
				alpha = Math.toDegrees(Math.acos((Math.pow(sideb, 2) + Math.pow(sidec, 2) - Math.pow(sidea, 2) ) / (2*sideb*sidec)));
			} else if (sidea!=null && beta!=null && sideb != null) {
				alpha =	Math.toDegrees(Math.asin(sidea*Math.sin(Math.toRadians(beta))/sideb));
			} else if (sidea!=null && sidec!=null && gamma!=null) {
				alpha = Math.toDegrees(Math.asin(Math.sin(Math.toRadians(gamma))*sidea/sidec));
			} else if (gamma !=null && beta !=null) {
				alpha =	(180-gamma-beta);
			} else if (hb!=null && sidec!=null) {
				alpha = Math.toDegrees(Math.asin(hb/sidec));
			} else if (hc!=null && sideb!=null) {
				alpha = Math.toDegrees(Math.asin(hc/sideb));
			}
			break;
		case "beta":
			if (sideb != null && sidec !=null && sidea !=null) {
				beta = Math.toDegrees(Math.acos((Math.pow(sidec, 2) + Math.pow(sidea, 2) - Math.pow(sideb, 2) ) / (2*sidec*sidea)));
			} else if (sideb !=null && alpha!=null && sidea!=null) {
				beta = Math.toDegrees(Math.asin((sideb*Math.sin(Math.toRadians(alpha))/sidea))); 
				//beta = Math.toDegrees(Math.asin(Math.sin(Math.toRadians(alpha))*sideb/sidea));
			} else if (sideb !=null && gamma!=null && sidec!=null) {
				beta = Math.toDegrees(Math.asin(Math.sin(Math.toRadians(gamma))*sideb/sidec));
			} else if (hc!=null&&sidea!=null) {
				beta = Math.toDegrees(Math.asin(hc/sidea));
			} else if (hc!=null&&sidec!=null) {
				beta = Math.toDegrees(Math.asin(ha/sidec));
			} else if (gamma !=null && alpha !=null) {
				beta =	(180-alpha-gamma);
			} else if (gamma !=null && sidea !=null) {
				beta = sidea * Math.toDegrees(Math.asin(gamma));
			}
			break;
		case "gamma":
			if (sideb != null && sidec !=null && sidea !=null) {
				gamma = Math.toDegrees(Math.acos((Math.pow(sidea, 2) + Math.pow(sideb, 2) - Math.pow(sidec, 2) ) / (2*sidea*sideb)));
			} else if (ha!=null && sideb!=null) {
				gamma = Math.toDegrees(Math.asin(ha/sideb));
			} else if (hb!=null && sidea!=null) {
				gamma = Math.toDegrees(Math.asin(hb/sidea));
			} else if (alpha!=null && sidec!=null && sidea!=null) {
				gamma = Math.toDegrees(Math.asin(Math.sin(Math.toRadians(alpha))*sidec/sidea));
			} else if (beta!=null && sidec!=null && sideb!=null) {
				gamma = Math.toDegrees(Math.asin(Math.sin(Math.toRadians(beta))*sidec/sideb));
			} else if (alpha !=null && beta !=null) {
				gamma =	(180-alpha-beta);
			} 
			break;
		default:
			
			break;
		}	
	} 
}
