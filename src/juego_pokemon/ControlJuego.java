package juego_pokemon;
/**
 *
 * @author kendr
 */
public abstract class ControlJuego {

    public abstract void iniciarJuego();

    public abstract void controlarTurno();

    public abstract boolean verificarPareja(
        int posicion1,
        int posicion2
    );

    public abstract boolean finalizarPartida();
}