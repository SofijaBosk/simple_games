package igra2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Pom extends Frame {

	 Mreza m;
	 Polje tr=new Trava(m);
	 Polje zid=new Zid(m);
	
	
	
	
	public Pom() {
		super("Igra");
		setSize(800,600);
		//setBackground(Color.WHITE);
		Panel p=new Panel();
		p.add(tr,BorderLayout.WEST);
		p.add(zid,BorderLayout.EAST);
		tr.repaint();
		add(p);
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
			
		});
		
		setVisible(true);
	}




	public static void main(String[] args) {
		new Pom();
		//System.out.println("fdss");
	}

}
