package igra2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;


public abstract class Polje extends Canvas {

	Mreza mr;
	protected int vrsta;
	protected int kolona;
	protected int[]a= {vrsta,kolona};
	
	public Polje(Mreza mreza) {
		super();
		this.mr = mreza;
	}

	public Mreza getMreza() {
		return mr;
	}
	
	public synchronized int[] getPozicija() {
		a[0]=vrsta;
		a[1]=kolona;
		return a;
	}//int[0]=vrsta int[1]=kolona
	
	
	public synchronized Polje pomeraj(int[] aa) {
		return mr.mreza[aa[0]][aa[1]];
	}
	
	public abstract boolean dozvoljenaFigura();


	public void setPoziciju(int vr, int kol) {
		vrsta=vr;
		kolona=kol;
		a[0]=vr;
		a[1]=kol;
		
	}
	
	@Override
	public void paint(Graphics g) {
		crtaj(g);
		//g.setColor(Color.green);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	protected abstract void crtaj(Graphics g);
	
	
	
	

	
	
	
	
}
