package igra2;

import java.awt.Color;
import java.awt.Graphics;

public class Igrac extends Figura {

	
	
	public Igrac(Polje polje) {
		super(polje);
	}

	@Override
	protected void crt(Graphics g, int x, int y) {
		g.setColor(Color.RED);
		g.drawLine(x/2, 0, x/2, y);
		g.drawLine(0, y/2, x, y/2);
		
	}

	

}
