package GUI;

import java.awt.*;

import javax.swing.*;

public class Menu extends JFrame{
	
	// elementi della finestra (testo, bottoni, ecc...)
		// sono dichiarati come variabili d'istanza per essere
		// accessibili da altre classi (in particolare il
		// gestore degli eventi - AddizionatoreActionListener)
		public JLabel label1;
		public JTextField campo1;
		public JLabel label2;
		public JTextField campo2;
		public JLabel label3;
		public JTextField campo3;
		public JButton bottone;

		// il main semplicemente crea un oggetto di questa classe
		public static void main(String[] args) {
			Menu window = new Menu("Menu città");
		}
		
		// il costruttore fa tutto il lavoro
		public Menu(String titolo) {
			super(titolo); // crea il JFrame
			// imposta la dimensione della finestra
			this.setSize(400, 150);
			// specifica che la finestra deve essere centrata
			this.setLocationRelativeTo(null);
			// specifica che la chiusura della finestra
			// deve far terminare il programma
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// inizializza la prima casella di input
			label1 = new JLabel("Città 1");
			campo1 = new JTextField(25);
			
			// inizializza la seconda casella di input
			label2 = new JLabel("Città 2");
			campo2 = new JTextField(25);
			
			// inizializza una casella per visualizzare il risultato
			//label3 = new JLabel("  Risultato");
			//campo3 = new JTextField(25);
			//campo3.setEditable(false); // non modificabile dall'utente
			
			// crea un bottone che fa eseguire il calcolo
			bottone = new JButton("Esegue");
			// collega il bottone al suo gestore degli eventi
			bottone.addActionListener(new MenuActionListener(this));
			
			// inserisce tutti gli elementi in un panel
			JPanel panel = new JPanel();
			panel.add(label1);
			panel.add(campo1);
			panel.add(label2);
			panel.add(campo2);
			//panel.add(label3);
			//panel.add(campo3);
			panel.add(bottone);
			
			// inserisce il panel nella finestra
			this.add(panel);
			
			// visualizza la finestra
			this.setVisible(true);
		}
}
