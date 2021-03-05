package igra;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout.Alignment;


public class Igra extends Frame{

	private Basta basta=new Basta(4,4);
	private Button kreni=new Button("Kreni");
	Label l=new Label("Povrce:"+basta.getPovrce());

	CheckboxGroup tezina= new CheckboxGroup();

	Checkbox lako=new Checkbox("Lako",false,tezina);
	Checkbox srednje=new Checkbox("Srednje",true,tezina);
	Checkbox tesko=new Checkbox("Tesko",false,tezina);
	
	
	public Igra() {
		super("Igra");
		setSize(600,500);
		setBackground(Color.white);
		
		dodajOsluskivace();
		dodajKomponente();
		setVisible(true);
	}
	
	
	private void dodajKomponente() {
		
		
		
		
		Panel desno=new Panel();
		
		Panel tezinaPanel=new Panel();
		Label lab=new Label("Tezina");
		lab.setFont(new Font(lab.getName(),Font.BOLD,16));
		tezinaPanel.add(lab,BorderLayout.CENTER);
		
		Panel tezinaPanel2=new Panel();
		tezinaPanel2.setLayout(new GridLayout(3,1));
		
		tezinaPanel2.add(lako);
		tezinaPanel2.add(srednje);
		tezinaPanel2.add(tesko);

		//tezinaPanel2.add(lab);
		
		desno.setLayout(new GridLayout(3,1));
		desno.add(tezinaPanel);
		desno.add(tezinaPanel2);
		desno.add(kreni);
		
		Panel glavnaDesno=new Panel();
		Panel dole=new Panel();
		glavnaDesno.setLayout(new GridLayout(2,1));
		glavnaDesno.add(desno);
		
		basta.setLabel(l);
		basta.getLabel().setFont(new Font(l.getName(),Font.BOLD,16));
		glavnaDesno.add(basta.getLabel(),BorderLayout.CENTER);
		
		add(glavnaDesno,BorderLayout.EAST);
		
		
		switch(tezina.getSelectedCheckbox().getLabel()) {
		case "Lako": 
			basta.setBrKoraka(10);
			basta.postIntervalCekanja(1000);
			  break;
		case "Srednje":
			basta.postIntervalCekanja(750);
			basta.setBrKoraka(8);break;
		case "Tesko":
			basta.postIntervalCekanja(500);
			basta.setBrKoraka(6);break;
		
		}
		
		
		add(basta,BorderLayout.CENTER);
	}


	private void dodajOsluskivace() {
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				basta.zaustavi();dispose();
			}
			
		});
		
		kreni.addActionListener(e->{
			if(kreni.getLabel()=="Kreni") {
			basta.pokreniNit();
			kreni.setLabel("Stani");
			lako.setEnabled(false);
			srednje.setEnabled(false);
			tesko.setEnabled(false);
			}
			else {
				basta.zaustaviNit();
				kreni.setLabel("Kreni");
				lako.setEnabled(true);
				srednje.setEnabled(true);
				tesko.setEnabled(true);
			}
		});
		
		
		
		 
		
	}
	
	
	
	public static void main(String[] args) {
		new Igra();

	}

}
