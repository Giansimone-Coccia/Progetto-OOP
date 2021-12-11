package GUI;

import java.awt.*;

import javax.swing.*;

/**
 * Classe che gestisce l'interfaccia grafica per gestire il menù nella scelta delle due città di 
 * cui si vogliono visualizzare le statistiche
 * @author Giansimone&Walter
 *
 */
public class Menu extends JFrame{
	
		public JLabel label1;
		public JTextField campo1;
		public JLabel label2;
		public JTextField campo2;
		public JLabel label3;
		public JTextField campo3;
		public JButton bottone;

		public static void main(String[] args) {
			Menu window = new Menu("Menu città");
		}
		
		public Menu(String titolo) {
			super(titolo); 
			this.setSize(400, 150);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// inizializza la prima casella di input
			label1 = new JLabel("Città 1");
			campo1 = new JTextField(30);
			
			// inizializza la seconda casella di input
			label2 = new JLabel("Città 2");
			campo2 = new JTextField(25);
			
			// crea un bottone
			bottone = new JButton("Esegue");
			// collega il bottone al suo gestore degli eventi
			bottone.addActionListener(new MenuActionListener(this));
			
			// inserisce tutti gli elementi in un panel
			JPanel panel = new JPanel();
			panel.add(label1);
			panel.add(campo1);
			panel.add(label2);
			panel.add(campo2);
			panel.add(bottone);
			
			// inserisce il panel nella finestra
			this.add(panel);
			
			// visualizza la finestra
			this.setVisible(true);
		}
}
