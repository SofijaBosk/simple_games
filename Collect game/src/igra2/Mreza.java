package igra2;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import igra2.Mreza.Smer;

public class Mreza extends Panel implements Runnable {

	public enum Smer{
		LEVO,DESNO,GORE,DOLE;
	}
	private Label lpoeni;
	//Figura[] figure; //Lista figura iste vrste ?????
	List<Novcic> listaNovcica=new ArrayList<>();
	List<Tenk> listaTenkova=new ArrayList<>();
	protected Figura igrac;
	protected Polje[][] mreza;
	//List<Polje> mreza=new ArrayList<>();
	//List<Polje> mreza=new List<Polje>();
	int om=1;
	private Igra igra;
	protected int dimenzija=17;

	private Random ran=new Random();
	private int brojac;
	private int ms=40;
	
	Thread nit=new Thread(this);
	boolean radi=false;
	
	private int poeni=0;
	
	MouseAdapter ma;
	//int d; //Pomocni obrisi kasnije
	

	public void setLpoeni(Label lpoeni) {
		this.lpoeni = lpoeni;
	}

	public int getPoeni() {
		return poeni;
	}

	public List<Novcic> getListaNovcica() {
		return listaNovcica;
	}

	public List<Tenk> getListaTenkova() {
		return listaTenkova;
	}


	public Figura getIgrac() {
		return igrac;
	}

	public Polje[][] getMreza() {
		return mreza;
	}

	

	public Mreza(Igra igra) {
		
		
		//super();
		this.igra = igra;
		mreza=new Polje[dimenzija][dimenzija];
		dodavanjePolja();
		
		
	}//Ovako je kad se podrazumeva da je dimenzija 17



	public Mreza(Igra igra, int dimenzija) {
		super();
		this.igra = igra;
		this.dimenzija = dimenzija;
		mreza=new Polje[dimenzija][dimenzija];
		dodavanjePolja();

		
	}

	
	
	
	

	private void dodavanjePolja() {
		brojac=(dimenzija*dimenzija)*80/100;
		while(brojac>0) {
			
			
			//Stavljamo 80% polja da su trava
			int vr=ran.nextInt(dimenzija);
			int kol=ran.nextInt(dimenzija);
			
			if(mreza[vr][kol]==null) {
				mreza[vr][kol]=new Trava(this);
				mreza[vr][kol].setPoziciju(vr,kol);
				brojac--;
			}
			
			
		}
		
		
		setLayout(new GridLayout(dimenzija,dimenzija));
		//Stavimo da su ostatak zidovi
		for(int i=0;i<dimenzija;i++)
			for(int j=0;j<dimenzija;j++) {
				if(mreza[i][j]==null) mreza[i][j]=new Zid(this);
				int vr=i;int kol=j;
				mreza[vr][kol].setPoziciju(vr,kol);
				ma=new MouseAdapter() {
					
					public void mouseClicked(MouseEvent e) {
						Mreza mr=mreza[vr][kol].getMreza();
						remove(mreza[vr][kol]);
						mreza[vr][kol]=null;
						//mreza[vr][kol].validate();
						switch(igra.podloga.getSelectedCheckbox().getLabel()) {
						case "Trava": mreza[vr][kol]=new Trava(mr); mreza[vr][kol].setPoziciju(vr, kol);break;
						case "Zid": mreza[vr][kol]=new Zid(mr);mreza[vr][kol].setPoziciju(vr, kol); break;
						}
						add(mreza[vr][kol],vr*dimenzija+kol);
						mreza[vr][kol].repaint();
						mreza[vr][kol].addMouseListener(this);
						repaint();
						revalidate();//Uradi se na mrezi
					}};
				mreza[i][j].addMouseListener(ma);
				
				mreza[i][j].addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						
						switch(e.getKeyCode()) {
						case KeyEvent.VK_LEFT:
							pomeri(Smer.LEVO);break;
						case KeyEvent.VK_RIGHT:
							pomeri(Smer.DESNO);break;
						case KeyEvent.VK_DOWN:
							pomeri(Smer.DOLE); break;
						case KeyEvent.VK_UP:
							pomeri(Smer.GORE);break;
							
					}
				}});
				
				mreza[i][j].setFocusable(true);
				mreza[i][j].requestFocusInWindow();
				
				add(mreza[i][j]);
			}
		//Iscrtavanje
		
		//for(int i=0;i<dimenzija;i++)
		//	for(int j=0;j<dimenzija;j++)
				
		//Mozda je ovde greska proveri
	}
	
	
	
	
	public void dodajNovcice(int b) {
		while(b>0) {
			int vr=ran.nextInt(dimenzija);
			int kol=ran.nextInt(dimenzija);
			boolean flag=true;
			if(mreza[vr][kol]!=null && mreza[vr][kol].dozvoljenaFigura()) {
				Novcic f=new Novcic(mreza[vr][kol]);
				for(int i=0;i<listaNovcica.size();i++) {
					if(f.equals(listaNovcica.get(i))) {
						//Na istom polju je kao drugi novcic
						flag=false;
						break;
					}
						
				}
				if(flag) {
					listaNovcica.add(f);
					b--;
				}
				
				
			
			}
		}
		
		
		
	
	}

	public void dodajTenkove(int b) {
		while(b>0) {
			int vr=ran.nextInt(dimenzija);
			int kol=ran.nextInt(dimenzija);
			
			if(mreza[vr][kol]!=null && mreza[vr][kol].dozvoljenaFigura()) {
					listaTenkova.add(new Tenk(mreza[vr][kol])); //Kasnije ih startujemo
					b--;
			}
			for(int i=0;i<listaTenkova.size();i++)
			listaTenkova.get(i).setDimenzije(dimenzija);
		}
		
	}

	//STAVLJAMO IGRACA
	public void staviIgraca() {	
				while(true) {
				int vr=ran.nextInt(dimenzija);
				int kol=ran.nextInt(dimenzija);
				
				if(mreza[vr][kol]!=null && mreza[vr][kol].dozvoljenaFigura()) {
					igrac=new Igrac(mreza[vr][kol]);
					break;
				}
				}
		
				
			
				
	}
	
	


	@Override
	public void run() {
		
		try {
			while(!Thread.interrupted()) {
				synchronized(this) {
					
					while(!radi) wait();
				}
				
				Thread.sleep(ms);
				//System.out.println("Radi"+(d++));
				azuriraj();
				iscrtaj();
				//lpoeni.setText("Poeni: "+poeni);
				if(listaNovcica.isEmpty()) zavrsi();
				
				//repaint();

			}		
		}catch(InterruptedException e) {}
		

	}

	private synchronized void azuriraj() {
		//Proverava da li se igrac nalazi na istom polju kao i novcic
		for(int i=0;i<listaNovcica.size();i++) {
			if(listaNovcica.get(i).equals(igrac)) {
				listaNovcica.remove(i);
				poeni++;
				lpoeni.setText("Poeni: "+poeni);
				igra.repaint();
				igra.revalidate();
			}
		}
		for(int i=0;i<listaTenkova.size();i++) {
			if(listaTenkova.get(i).equals(igrac)) {
				//igrac=null;
				zavrsi();
			}
		}
		
		
		
	}

	
	public synchronized void iscrtaj() {
		
		//for(int i=0;i<dimenzija;i++)
		//	for(int j=0;j<dimenzija;j++)
			//	mreza[i][j].repaint();
		
		for(int i=0;i<listaNovcica.size();i++) {
			listaNovcica.get(i).crtajFiguru(listaNovcica.get(i).getPolje());}
			
		for(int i=0;i<listaTenkova.size();i++)
			listaTenkova.get(i).crtajFiguru(listaTenkova.get(i).getPolje());
		
		igrac.crtajFiguru(igrac.getPolje());

		//System.out.println(igrac.getPolje().vrsta +" "+igrac.getPolje().kolona);
	}
	
	
	
	
	public synchronized void stani() {
		nit.interrupt();
		radi=false;
			
		
		
		//repaint();
		
		
	}
	public synchronized void zavrsi() {
		radi=false;
	for(int i=0;i<listaTenkova.size();i++) {
		listaTenkova.get(i).zaustaviNit();
	}
	}
	public synchronized void kreni() {
		//Sve se postavi na null da bi moglo ponovo da se pokrene
		//repaint();
		poeni=0;
		lpoeni.setText("Poeni: "+poeni);
		if(om==1) {
			nit.start();
			om--;
		}
		 notify();
		
		radi=true; 
		for(int i=0;i<getListaTenkova().size();i++) {
			getListaTenkova().get(i).pokreniTenk();
		}
		
}

	public synchronized void ukloniSaTable() {
			listaNovcica.clear();
			listaTenkova.clear();
		
		igrac=null;
		
		for(int i=0;i<dimenzija;i++)
			for(int j=0;j<dimenzija;j++)
				mreza[i][j].repaint();
		
		repaint();
		revalidate();
		
	}

	public void pomeri(Smer smer) {
		
		int[]pom =igrac.polje.getPozicija();
		if(pom[1]<0 || pom[1]>=dimenzija || pom[0]<0 || pom[0]>=dimenzija) {
			return;	//igrac.polje.repaint();		
		}
		
		//System.out.println("Pre:" +pom[0]+" "+pom[1]);
		switch(smer) {
		case LEVO:
			pom[1]-=1;break;
		case DESNO:
			pom[1]+=1;;break;
		case DOLE:
			pom[0]+=1;break;
		case GORE:
			pom[0]-=1;break;
			
		}
		
		if(!(pom[1]<0 || pom[1]>=dimenzija || pom[0]<0 || pom[0]>=dimenzija)) {
			//System.out.println("Posle:"+pom[0]+" "+pom[1]);
			Polje pr=igrac.polje.pomeraj(pom);
			if(pr!=null && pr.dozvoljenaFigura()) {
				igrac.polje.repaint();
				igrac.pomeriFiguru(pr);
				igrac.crtajFiguru(igrac.polje);
				igrac.polje.setPoziciju(pom[0], pom[1]);
				
			}
		}
		
		
		
	}
	
	
	
	
	
	

}
