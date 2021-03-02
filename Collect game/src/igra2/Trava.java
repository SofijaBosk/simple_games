package igra2;

import java.awt.Color;
import java.awt.Graphics;

public class Trava extends Polje {
	
	//protected boolean moze=true; 
	
	public Trava(Mreza mreza) {
		super(mreza);
		//repaint();
	}

	/*@Override
	public void paint(Graphics g) {
		//super.paint(g);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
*/
	@Override
	public boolean dozvoljenaFigura() {
		/*if(moze) { //Vec je stavljena figura na nju
			moze=false;
			return true;
		}
		*/ //Mozemo bez ovoga za pomeranje figura proveravamo kod figura
		return true;
	}

	
	
	@Override
	protected void crtaj(Graphics g) {
		g.setColor(Color.GREEN);		
	}
	
	

	
	
	
}
