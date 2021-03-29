package igra2;

import java.awt.Color;
import java.awt.Graphics;

public class Novcic extends Figura {

	public Novcic(Polje polje) {
		super(polje);
	}

	@Override
	protected void crt(Graphics g, int x, int y) {

		g.setColor(Color.YELLOW);
		g.drawOval(x/4, y/4, x/2, y/2);
		g.fillOval(x/4, y/4, x/2, y/2);
		
	}

}
