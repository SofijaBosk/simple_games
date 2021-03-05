package igra3;

import java.awt.Color;
import java.awt.Graphics;

public class Igrac extends KruznaFigura {

	public Igrac(Vektor polozajCentra, int precnik, Vektor brzina, Scena scena) {
		super(polozajCentra, Color.GREEN, precnik, brzina, scena);
	}
	
	@Override
	public synchronized void promenaPolozaja() {
		
		try {
			Vektor pom;
			pom = (Vektor) polozajCentra.clone();
		
		pom.saberi(brzina);
		int sirina=scena.getWidth();
		int visina=scena.getHeight();
		
		if(!((pom.getX()+precnik/2)>sirina || (pom.getY()+precnik/2)>visina || (pom.getX()+precnik/2)<0 || (pom.getY()+precnik/2)<0))	
			polozajCentra=pom;
		
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
	} 

	@Override
	public synchronized void crtajKrug(Scena s) {
		super.crtajKrug(s);
		Graphics g=s.getGraphics();
		g.setColor(Color.BLUE);
		g.fillOval((int)polozajCentra.getX()+precnik/8,(int) polozajCentra.getY()+precnik/8, precnik/3, precnik/3);
	}
	@Override
	public synchronized void bioSudar() {
		scena.zaustaviScenu();
	}

	

}
