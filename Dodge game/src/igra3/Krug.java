package igra3;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Krug {

	protected Vektor polozajCentra;
	protected Color boja;
	protected int precnik;
	
	public Krug(Vektor polozajCentra, Color boja, int precnik) {
		this.polozajCentra = polozajCentra;
		this.boja = boja;
		this.precnik = precnik;
	}
	
	public boolean preklapaSe(Krug k1,Krug k2) {
		
		//Rastojanje izmedju tacaka:
		double a=Math.pow(k1.polozajCentra.getX()-k2.polozajCentra.getX(),2)+Math.pow(k1.polozajCentra.getY()-k2.polozajCentra.getY(), 2);
		double rastojanje=Math.sqrt(a);
		
		
		//Ako je rastojanje izmedju 2 centra manje od rastojanja zbira poluprecnika krugova preklapaju se
		if(rastojanje<(k2.precnik/2+k1.precnik/2))
			return true;
		else
			return false;
		
	}
	
	public void crtajKrug(Scena s) {
		Graphics g=s.getGraphics();
		g.setColor(boja);
		g.fillOval((int)polozajCentra.getX()-precnik/4,(int) polozajCentra.getY()-precnik/4, precnik, precnik); //g.fillOval(0, 0, precnik, precnik);
	}
	
}
