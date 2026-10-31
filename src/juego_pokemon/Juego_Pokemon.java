package juego_pokemon;

import javax.swing.SwingUtilities;

/**31/10/2026
 *
 * Grupo 5, laboratorio de programacion II
 */
public class Juego_Pokemon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
            
            VentanaTablero tablero = new VentanaTablero("Jugador 1", "Jugador 2");
            tablero.setVisible(true);
        });
    }
    
}
