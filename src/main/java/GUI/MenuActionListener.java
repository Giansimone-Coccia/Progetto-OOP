package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Pressure.model.*;

/**
 * Classe che implementa ActionListener per assegnare le istruzioni al bottone
 * @author Giasimone&Walter
 *
 */
public class MenuActionListener implements ActionListener{

		private Menu window;
		//City city1 = new City();
		//City city2 = new City();
		
		// costruttore
		public MenuActionListener(Menu window) {
			this.window = window;
		}
		
		// gestore dell'evento
		public void actionPerformed(ActionEvent e) {
			String c1 = (String)(window.campo1.getText());
			String c2 = (String)(window.campo2.getText());
			//city1.setName(c1);
			//city2.setName(c2);
		}
}
