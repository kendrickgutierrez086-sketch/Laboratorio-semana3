package juego_pokemon;
/**
 *
 * clase para las cartas
 */
public abstract class Carta implements Cloneable {

    protected String nombrePokemon;
    protected boolean descubierta;
    protected boolean emparejada;

    public Carta(String nombrePokemon) {
        this.nombrePokemon=nombrePokemon;
        this.descubierta=false;
        this.emparejada=false;
    }

    public String getNombrePokemon() {
        return nombrePokemon;
    }

    public boolean estaDescubierta() {
        return descubierta;
    }

    public boolean estaEmparejada() {
        return emparejada;
    }

    public void mostrar() {
        descubierta=true;
    }

    public void ocultar() {
        if (!emparejada) {
            descubierta=false;
        }
    }

    public void marcarEmparejada() {
        emparejada=true;
        descubierta=true;
    }

    public abstract int ganarPuntos();

    
    
    
    
    
    
    @Override
    public Carta clone() {
        try {
            return (Carta) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(
                "No se pudo clonar la carta, error"
            );
        }
    }
}