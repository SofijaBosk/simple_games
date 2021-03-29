package igra;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Rupa extends Canvas implements Runnable {

	private int ms=100;
	protected Basta basta;
	private Zivotinja zivotinja;
	private Thread nit;
	private boolean radi=false;
	private boolean udarenaZivotinja=true;
	
	private int brKoraka;
	private int brKorakaPom;
	private boolean flag=true;
	
	public Rupa(Basta basta) {
		this.basta = basta;	
		
	}
	
	public synchronized Zivotinja getZivotinja() {
		return zivotinja;
	}
	public synchronized void setZivotinja(Zivotinja zivotinja) {
		this.zivotinja = zivotinja;
	}
	public synchronized int getBrKoraka() {
		return brKoraka;
	}
	public synchronized void setBrKoraka(int brKoraka) {
		this.brKoraka = brKoraka;
		brKorakaPom=0;
	}
	public synchronized int trenBrKoraka() {
		return brKorakaPom;
	}

	public synchronized void zgazenaRupa() {
		if (zivotinja==null) return;
		zivotinja.efekatUdareneZivotinje();
		udarenaZivotinja=true;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized(this) {
					while(!radi) wait();
				}
				Thread.sleep(ms);
				brKorakaPom++;
				if(trenBrKoraka()==getBrKoraka()) {
					Thread.sleep(2000);
					udarenaZivotinja=false;
					zaustaviNit();
				}
				
				
				repaint();
			}		
		}catch(InterruptedException e) {}

	}
	
	public synchronized void stvoriNit() {
		nit=new Thread(this);
		nit.start();
		
	}
	public synchronized void pokreniNit() {
		 notify();
		radi=true;
	}
	public synchronized void zaustaviNit() {
		nit.interrupt();
		radi=false;
		if(!udarenaZivotinja && zivotinja!=null) zivotinja.efekatPobegleZivotinje();
		zivotinja=null;
		basta.obavestenjeRupaSlobodna(this);
		repaint();
	}
	boolean pokrenutaNit() {
		return radi;
	}
	
	@Override
	public synchronized void paint(Graphics g) {	
		basta.paint(g);
		g.setColor(new Color(102,51,0));
		g.fillRect(0, 0, getWidth(), getHeight());
		if(zivotinja!=null) zivotinja.iscrtajZivotinju(this);
	}




	

}
