package igra2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Tenk extends Figura implements Runnable {
	
	
	private Thread nit=new Thread(this);
	private int ms=500;
	private boolean radi=false;
	private Random ran=new Random();
	protected int[]a= {polje.vrsta,polje.kolona};
	private boolean flag=true;
	private int dimenzije;
	
	
	public void setDimenzije(int dimenzije) {
		this.dimenzije = dimenzije;
	}

	public Tenk(Polje polje) {
		super(polje);
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized(this) {
					while(!radi) wait();
				}
				
				Thread.sleep(ms);
				
				
				int pr=ran.nextInt(4); //vraca broj izmedju 0 i 3
				a=polje.a; //Svaki put cemo gledati da se pomerimo sa onog polja na kom smo bez obzira da li se pomerio pre
				pomeriTenk(pr);
				
				
			}		
		}catch(InterruptedException e) {}

	}
	
	public synchronized void pokreniTenk() {
		if(flag==true) nit.start();
		else notify();
		radi=true;
		
	}
	
	public synchronized void zaustaviNit() {
		nit.interrupt();
		radi=false;
		flag=false;
	}
	
	public synchronized void pomeriTenk(int p) {
		
		int[] pom=polje.getPozicija();
		if(pom[1]<0 || pom[1]>=dimenzije || pom[0]<0 || pom[0]>=dimenzije) return;			
		
		switch(p) {
		case 0: pom[1]-=1; break; //LEVO
		case 1: pom[1]+=1; break; //DESNO
		case 2: pom[0]-=1; break; //GORE
		case 3: pom[0]+=1; break; //DOLE
		}
		
		
			
			if(!(pom[1]<0 || pom[1]>=dimenzije || pom[0]<0 || pom[0]>=dimenzije)) {
				Polje pr=polje.pomeraj(pom);
				if(pr!=null && pr.dozvoljenaFigura()) {
					polje.repaint();
					pomeriFiguru(pr);
					crtajFiguru(polje);
					//polje=pr;
					polje.setPoziciju(pom[0], pom[1]);
					
				}
			}
			
	}
	
	
	

	@Override
	protected void crt(Graphics g, int x, int y) {
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, x, y);
		g.drawLine(x, 0, 0, y);

	}

}
