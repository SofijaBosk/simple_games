package igra2;

import java.awt.Color;
import java.awt.Graphics;

public  class Zid extends Polje {

	
	public Zid(Mreza mreza) {
		super(mreza);
		//repaint();
		}

/*
	@Override
	public void paint(Graphics g) {
		//super.paint(g);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	*/

	@Override
	public boolean dozvoljenaFigura() {
		return false;
	}


	@Override
	protected void crtaj(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		
	}

}
