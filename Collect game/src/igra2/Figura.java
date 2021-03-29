package igra2;

import java.awt.Graphics;

public abstract class Figura {

	protected Polje polje;

	public Figura(Polje polje) {
		super();
		this.polje = polje;
	}

	public synchronized Polje getPolje() {
		return polje;
	}
	
	public synchronized void pomeriFiguru(Polje p) {
		if(p.dozvoljenaFigura()) {
			polje=p;
			polje.setPoziciju(p.vrsta, p.kolona);
		}
	}
	
	public void crtajFiguru(Polje p) {
		Graphics g=p.getGraphics();
		int x=p.getWidth();
		int y=p.getHeight();
		crt(g,x,y);
	}


	protected abstract void crt(Graphics g, int x, int y);

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Figura other = (Figura) obj;
		if (polje == null) {
			if (other.polje != null)
				return false;
		} else if (!(polje==other.polje))
			return false;
		return true;
	}
	
	
	
	
	
	
}
