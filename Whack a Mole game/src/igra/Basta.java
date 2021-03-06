package igra;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Basta extends Panel implements Runnable {

	List<Rupa> proba=new ArrayList<>();
	private int povrce=100;
	
	private int brVrsta;
	private int brKolona;

	private int brKoraka; //za sve rupe u basti
	private int ms;//interval cekanja
	private boolean radi=false;
	private Rupa r;
	Thread nit=new Thread(this);
	
	
	private Label lpovrce;
	
	public Basta(int brVrsta, int brKolona) {
		super();
		this.brVrsta = brVrsta;
		this.brKolona = brKolona;
		nit.start();
		
		}

	public synchronized int getBrKoraka() {
		return brKoraka;
	}
	public synchronized void setBrKoraka(int brKoraka) {
		this.brKoraka = brKoraka;
		StaviRupe();
	}
	
	public void StaviRupe() {
		setLayout(new GridLayout(brVrsta,brKolona,20,20));
		for(int i=0;i<brVrsta*brKolona;i++) {
			proba.add(new Rupa(this));
			int p=i;
			proba.get(i).setBrKoraka(brKoraka);
			proba.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					proba.get(p).zgazenaRupa();
					
				}
			});
			add(proba.get(i));
		
		}
	}
	


	public synchronized void smanjiBrPovrca() {
		
		
		if(povrce==0)zaustaviNit();
		else 
		povrce--;
		
		azurirajLabelu();
	}

	private synchronized void azurirajLabelu() {
		if(lpovrce==null) return;
		lpovrce.setText("Povrce: "+getPovrce());
		
	}

	public void paint(Graphics g) {
		g.setColor(new Color(0,170,0));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
	}
	
	public synchronized void pokreni() {
		nit.start();
	}
	public synchronized void pokreniNit() {
		
		povrce=100;
		notify();
		azurirajLabelu();
		radi=true;
	}
	public synchronized void zaustaviNit() {
		radi=false;
	}
	public synchronized void zaustavi() {
		nit.interrupt();
		radi=false;
	}


	@Override
	public void run() {
		
		
		try {
			while(!Thread.interrupted()) {
				synchronized(this) {
					while(!radi) wait();
				}
				
				
				
				Random vr=new Random();
				int v=vr.nextInt(brVrsta*brKolona-1);
				
				
					while(true) {
						if(proba.get(v)==null || v>brVrsta*brKolona-1){
					
						v=vr.nextInt();
					}
						else break;
					}
					proba.get(v).setZivotinja(new Krtica(proba.get(v)));
	
				Thread.sleep(ms);
				
				proba.get(v).stvoriNit();
				proba.get(v).pokreniNit();				
				azurirajLabelu();
				
			}		
		}catch(InterruptedException e) {}	
		
	}
	

	public synchronized void postIntervalCekanja(int ic) {
		ms=ic;
	}
	
	public synchronized int getPovrce() {
		return povrce;
	}
	
	public synchronized void obavestenjeRupaSlobodna(Rupa r) {
		r.setBrKoraka(brKoraka);
		notify();	
	}

	public void setLabel(Label l) {
		lpovrce=l;
	}
	
	public Label getLabel() {
		return lpovrce;
	}
	
}
