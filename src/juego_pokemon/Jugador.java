package juego_pokemon;

/**
 *
 * @author kendr
 */

public class Jugador{

    private String nombre;
    private int aciertos;
    private int puntaje;

    public Jugador(String nombre){
        this.nombre=nombre;
        this.aciertos=0;
        this.puntaje=0;
    }

    public String getNombre(){
        return nombre;
    }

    public int getAciertos(){
        return aciertos;
    }

    public int getPuntaje(){
        return puntaje;
    }

    public void sumarAcierto(){
        aciertos=aciertos+1;
    }

    public void sumarPuntos(int puntos){
        puntaje=puntaje+puntos;
    }

    public void reiniciar(){
        aciertos=0;
        puntaje=0;
    }
}
