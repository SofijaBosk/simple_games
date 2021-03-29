package igra3;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Scena extends Canvas implements Runnable {

	private Igra igra;
	private Thread nit=new Thread(this);
	private boolean radi=false;
	private boolean flag=true;
	
	private Igrac igrac;
	
	protected List<Balon> listaBalona=new ArrayList<>(); 
	
	public Scena(Igra igra) {
		this.igra = igra;
		setBackground(Color.WHITE);
	}
	
	
	public List<Balon> getListaBalona() {
		return listaBalona;
	}





	public synchronized void pokreniScenu() {
		stvoriIgraca();
		if(flag) {
			nit.start();
			flag=false;
		}
		else notify();
		radi=true;
		
		
	}
	private void stvoriIgraca() {
		Vektor centar=new Vektor(igra.getWidth()/2,igra.getHeight()*7/8);
		igrac=new Igrac(centar,30,new Vektor(0,0),this); 
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					igrac.setBrzina(new Vektor(-6,0));break;
				case KeyEvent.VK_RIGHT:
					igrac.setBrzina(new Vektor(+6,0));break;
				
			}
				igrac.promenaPolozaja();
		}});
		
	}

	public synchronized void zaustaviScenu() {
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
				
			Random ran=new Random();
				Thread.sleep(60);
				if(ran.nextDouble()<0.1) {
					Balon b=new Balon(new Vektor(ran.nextInt(igra.getWidth()),ran.nextInt(igra.getHeight()/5)),Color.RED,20,new Vektor(0,4),this);
					listaBalona.add(b);
				}
				
				pomeriBalone();
				repaint();
				preklapanja();
				
				//igrac.promenaPolozaja(); mi pomeramo igraca
				
				
			}		
		}catch(InterruptedException e) {}

	}



	private synchronized void preklapanja() {
		for(int i=0;i<listaBalona.size();i++) {
			if(listaBalona.get(i).preklapaSe(listaBalona.get(i), igrac))
				igrac.bioSudar();
		}		
	}


	private synchronized void pomeriBalone() {
		for(int i=0;i<listaBalona.size();i++) {
			listaBalona.get(i).promenaPolozaja();
			if(listaBalona.get(i).preklapaSe(listaBalona.get(i), igrac)) 
				igrac.bioSudar();
				
				
		}		
	}


	@Override
	public synchronized void paint(Graphics g) {
		
		
		for(int i=0;i<listaBalona.size();i++)
			listaBalona.get(i).crtajKrug(this);
		igrac.crtajKrug(this);
		
		
	}
	
}
