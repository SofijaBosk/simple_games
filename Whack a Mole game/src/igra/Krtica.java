package igra;

import java.awt.Color;
import java.awt.Graphics;

public class Krtica extends Zivotinja {

	
	
	
	public Krtica(Rupa rupa) {
		super(rupa);
	}
	
	
	

	@Override
	protected void crt(Graphics g,int x,int y, int w, int h) {

		if(w>rupa.getWidth() || h>rupa.getHeight()) return;
		g.setColor(Color.DARK_GRAY);
		g.drawOval(x, y, w-1, h-1);
		g.setColor(Color.DARK_GRAY);
		g.fillOval(x, y, w-1, h-1);	
	}



	@Override
	public void efekatUdareneZivotinje() {
		rupa.zaustaviNit();

	}

	@Override
	public void efekatPobegleZivotinje() {
		rupa.basta.smanjiBrPovrca();

	}

}


