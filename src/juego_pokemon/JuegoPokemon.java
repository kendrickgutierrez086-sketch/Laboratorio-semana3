package juego_pokemon;

/**
 *
 * Esta clase calcula todo lo del juego y usa las demas clase para la logica
 */
import java.util.Random;

public class JuegoPokemon extends ControlJuego{

    private Carta[]tablero;
    private Jugador[]jugadores;
    private int turnoActual;
    private Random random;

    private String[] nombresPokemon={
        "Pikachu",
        "Bulbasaur",
        "Charmander",
        "Squirtle",
        "Eevee",
        "Gible",
        "Meowth",
        "Psyduck",
        "Snorlax",
        "Gengar",
        "Toxapex",
        "Geodude",
        "Mew",
        "Dratini",
        "Mimikyu",
        "Mewtwo",
        "Ditto",
        "Lapras"
    };

    public JuegoPokemon(String nombre1, String nombre2)
            throws NombreInvalido{

        validarNombres(nombre1, nombre2);

        jugadores=new Jugador[2];

        jugadores[0]=new Jugador(nombre1);
        jugadores[1]=new Jugador(nombre2);

        tablero=new Carta[36];

        turnoActual=0;

        random=new Random();
    }

    private void validarNombres(
            String nombre1,
            String nombre2)
            throws NombreInvalido {

        if(nombre1==null||nombre1.trim().isEmpty()) {
            throw new NombreInvalido( "El nombre del jugador 1 es obligatorio");
        }

        if(nombre2==null||nombre2.trim().isEmpty()) {
            throw new NombreInvalido("El nombre del jugador 2 es obligatorio");
        }

        if (nombre1.trim().length()>20||nombre2.trim().length()>20){
            throw new NombreInvalido( "Los nombres no pueden superar 20 caracteres");
        }

        if (nombre1.trim().equalsIgnoreCase(nombre2.trim())) {
            throw new NombreInvalido("Los jugadores deben tener nombres diferentes."
            );
        }}

    @Override
    public void iniciarJuego() {
        int posicion = 0;
        for (int i = 0; i < nombresPokemon.length; i++) {
            Carta carta;
            if (i >= 16) {
                carta = new CartaEspecial(nombresPokemon[i]);
            } else {
                carta = new CartaNormal(nombresPokemon[i]);
            }
            tablero[posicion]=carta;
            posicion++;
            try {
                Carta copia=carta.clone();
                copia.ocultar();

                tablero[posicion]=copia;
                posicion++;
            } catch (IllegalStateException e) {

                System.out.println("Error de clonar: " + e.getMessage()
                );
            }
        }
        mezclarTablero(tablero.length-1);
    }
    
    
    
    private void mezclarTablero(int posicion) {

        if (posicion<=0) {
            return;
        }
        int posicionAleatoria=random.nextInt(posicion + 1);
        Carta temporal=tablero[posicion];
        tablero[posicion]=tablero[posicionAleatoria];
        tablero[posicionAleatoria]=temporal;
        mezclarTablero(posicion-1);
    }

    @Override
    public void controlarTurno() {

        turnoActual=(turnoActual+1)%2;
    }
    
    
    
    @Override
    public boolean verificarPareja(int posicion1,int posicion2){
        if (posicion1<0||posicion1>=tablero.length||posicion2<0||posicion2>=tablero.length) {
            throw new IndexOutOfBoundsException("Posición fuera del tablero.");
        }

        if (posicion1==posicion2) {

            throw new IllegalArgumentException(
                "No puedes seleccionar la misma carta."
            );
        }
        Carta carta1 = tablero[posicion1];
        Carta carta2 = tablero[posicion2];
        
        if (carta1.estaDescubierta()||carta2.estaDescubierta()||carta1.estaEmparejada()||carta2.estaEmparejada()) {
            throw new IllegalArgumentException("No puede elegir una carta descubierta o emparejada");
        }
        carta1.mostrar();
        carta2.mostrar();
        if (carta1.getNombrePokemon().equals(carta2.getNombrePokemon())) {
            carta1.marcarEmparejada();
            carta2.marcarEmparejada();
            jugadores[turnoActual].sumarAcierto();
            jugadores[turnoActual].sumarPuntos(
                carta1.ganarPuntos()
            );
            return true;
        }
        return false;
    } 
    private int contarEmparejadas(int posicion) {
        if (posicion==tablero.length) {
            return 0;
        }
        int cantidad=0;
        if (tablero[posicion].estaEmparejada()) {
            cantidad=1;
        }
        return cantidad+contarEmparejadas(posicion+1);
    }

    @Override
    public boolean finalizarPartida() {
        return contarEmparejadas(0)==36;
    }

    public Carta[]getTablero() {
        return tablero;
    }

    public Jugador[]getJugadores() {
        return jugadores;
    }

    public int getTurnoActual() {
        return turnoActual;
    }
}

