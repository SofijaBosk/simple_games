package igra;

import java.awt.Graphics;

public abstract class Zivotinja {

	protected Rupa rupa;

	public Zivotinja(Rupa rupa) {
		this.rupa = rupa;
	}
	
	public void iscrtajZivotinju(Rupa r) {
		Graphics g=r.getGraphics();
		int w=r.getWidth()*r.trenBrKoraka()/r.getBrKoraka(); 
		int h=r.getHeight()*r.trenBrKoraka()/r.getBrKoraka();
		
		int x=r.getWidth()/2-w/2;
		int y=r.getHeight()/2-h/2;
		
		crt(g,x,y,w,h);
		
	}
	
	
	protected abstract void crt(Graphics g,int x,int y,int w,int h);
	
	
	
	public abstract void efekatUdareneZivotinje();
	public abstract void efekatPobegleZivotinje();
	
	
	
}
