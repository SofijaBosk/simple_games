package igra3;

import java.awt.Color;

public class Balon extends KruznaFigura {

	public Balon(Vektor polozajCentra, Color boja, int precnik, Vektor brzina, Scena scena) {
		super(polozajCentra, boja, precnik, brzina, scena);
	}

	@Override
	public void promenaPolozaja() {
			polozajCentra.saberi(brzina);
			int sirina=scena.getWidth();
			int visina=scena.getHeight();
			
			if(polozajCentra.getX()>sirina || polozajCentra.getY()>visina || polozajCentra.getX()<0 || polozajCentra.getY()<0)	
				scena.getListaBalona().remove(this);
			
		
		
		
	}

}
