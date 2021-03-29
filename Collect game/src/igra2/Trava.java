package igra2;

import java.awt.Color;
import java.awt.Graphics;

public class Trava extends Polje {
	
	 public Trava(Mreza mreza) {
		super(mreza);
	}

	@Override
	public boolean dozvoljenaFigura() {
		return true;
	}

	
	
	@Override
	protected void crtaj(Graphics g) {
		g.setColor(Color.GREEN);		
	}	
	
}
