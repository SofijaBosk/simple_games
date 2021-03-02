package igra3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Igra extends Frame {
	
	Scena scena=new Scena(this);
	
	public Igra() {
		super("Baloni");
		setSize(600,600);
		setBackground(Color.WHITE);

		//System.out.println(getWidth());
		
		add(scena,BorderLayout.CENTER);
		scena.pokreniScenu();

		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				scena.zaustaviScenu(); dispose(); 
			}
			
		});	
		
		
		setVisible(true);
		
		
		
	}
	

	public static void main(String[] args) {
		new Igra();

	}

}
