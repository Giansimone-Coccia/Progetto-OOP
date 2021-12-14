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
<<<<<<< HEAD
		//City city1 = new City();
		//City city2 = new City();
=======
		City city1 = new City();
		City city2 = new City();
>>>>>>> 873d73f99a662bb3a51afe998ee45786868b230e
		
		// costruttore
		public MenuActionListener(Menu window) {
			this.window = window;
		}
		
		// gestore dell'evento
		public void actionPerformed(ActionEvent e) {
			String c1 = (String)(window.campo1.getText());
			String c2 = (String)(window.campo2.getText());
<<<<<<< HEAD
			//city1.setName(c1);
			//city2.setName(c2);
=======
			city1.setName(c1);
			city2.setName(c2);
>>>>>>> 873d73f99a662bb3a51afe998ee45786868b230e
		}
}
