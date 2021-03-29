package igra3;

import java.awt.Color;

public abstract class KruznaFigura extends Krug {

	Vektor brzina;
	Scena scena;

	
	public KruznaFigura(Vektor polozajCentra, Color boja, int precnik, Vektor brzina, Scena scena) {
		super(polozajCentra, boja, precnik);
		this.brzina=brzina;
		this.scena=scena;
	}
	

	public synchronized Vektor getBrzina() {
		return brzina;
	}

	public void setBrzina(Vektor brzina) {
		this.brzina = brzina;
	}


	//Obavestava figuru kad prodje odredjeni vremenski period da treba da se pomeri
	public abstract void promenaPolozaja() ;
	
	//Obavestavamo figuru da se sudarila sa drugom figurom:
	public void bioSudar() {}

}