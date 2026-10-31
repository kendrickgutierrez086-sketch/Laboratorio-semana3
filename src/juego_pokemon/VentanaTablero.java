package juego_pokemon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaTablero extends JFrame {
    private CartaUI[][] botonesTablero = new CartaUI[6][6];
    private JLabel lblTurno, lblJugador1, lblJugador2;
    private Timer temporizadorPausa;
    private boolean tableroBloqueado = false;
    private JuegoPokemon juego;
    
    private int seleccion1Fila = -1, seleccion1Col = -1;
    private int seleccion2Fila = -1, seleccion2Col = -1;
    private boolean esSegundaCarta = false;

    public VentanaTablero(String nombreJ1, String nombreJ2) {
        try {
            this.juego = new JuegoPokemon(nombreJ1, nombreJ2);
            this.juego.iniciarJuego();
        } catch (NombreInvalido e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLayout(new BorderLayout());
        this.setTitle("JUEGO DE MEMORIA POKEMON");

        JPanel panelSup = new JPanel(new GridLayout(1, 2, 10, 10));
        panelSup.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        lblJugador1 = new JLabel("<html><b>" + nombreJ1 + "</b><br>Aciertos: 0 | Puntos: 0</html>");
        lblJugador2 = new JLabel("<html><div style='text-align: right;'><b>" + nombreJ2 + "</b><br>Aciertos: 0 | Puntos: 0</div></html>", SwingConstants.RIGHT);
        
        lblJugador1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblJugador2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        panelSup.add(lblJugador1);
        panelSup.add(lblJugador2);

        lblTurno = new JLabel("Turno actual: " + nombreJ1, SwingConstants.CENTER);
        lblTurno.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTurno.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));

        add(panelSup, BorderLayout.NORTH);
        add(lblTurno, BorderLayout.SOUTH);

        JPanel panelGrid = new JPanel(new GridLayout(6, 6, 5, 5));
        panelGrid.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int indice = i * 6 + j;
                Carta cartaModelo = juego.getTablero()[indice];
                 boolean esEspecial = cartaModelo instanceof CartaEspecial;
                
                botonesTablero[i][j] = new CartaUI(cartaModelo.getNombrePokemon(), esEspecial);
                botonesTablero[i][j].setFont(new Font("SansSerif", Font.BOLD, 14));
                botonesTablero[i][j].setFocusPainted(false);

                final int fila = i;
                final int col = j;

                botonesTablero[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (tableroBloqueado) {
                            return;
                        }

                        try {
                            procesarSeleccion(fila, col);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(
                                VentanaTablero.this,
                                "Selección inválida: " + ex.getMessage(),
                                "Advertencia",
                                JOptionPane.WARNING_MESSAGE
                            );
                        }
                    }
                });

                panelGrid.add(botonesTablero[i][j]);
            }
        }

        add(panelGrid, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    private void procesarSeleccion(int fila, int col) {
        int indiceCarta = fila * 6 + col;
        Carta[] tableroModelo = juego.getTablero();
        Carta cartaSeleccionada = tableroModelo[indiceCarta];

        if (cartaSeleccionada.estaDescubierta() || cartaSeleccionada.estaEmparejada()) {
            return; 
        }
        if (esSegundaCarta && seleccion1Fila == fila && seleccion1Col == col) {
            return; 
        }

        if (!esSegundaCarta) {
            seleccion1Fila = fila;
            seleccion1Col = col;
            cartaSeleccionada.mostrar();
            botonesTablero[fila][col].setDescubierta(true);
            esSegundaCarta = true;
        } else {
            seleccion2Fila = fila;
            seleccion2Col = col;
            cartaSeleccionada.mostrar();
            botonesTablero[fila][col].setDescubierta(true);
            esSegundaCarta = false;

            int indice1 = seleccion1Fila * 6 + seleccion1Col;
            int indice2 = seleccion2Fila * 6 + seleccion2Col;

            try {
                boolean acierto = juego.verificarPareja(indice1, indice2);
                actualizarMarcadores();

                if (acierto) {
                    if (juego.finalizarPartida()) {
                        declararGanador();
                    }
                } else {
                    tableroBloqueado = true;
                    temporizadorPausa = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            tableroModelo[indice1].ocultar();
                            tableroModelo[indice2].ocultar();
                            botonesTablero[seleccion1Fila][seleccion1Col].setDescubierta(false);
                            botonesTablero[seleccion2Fila][seleccion2Col].setDescubierta(false);

                            juego.controlarTurno();
                            actualizarMarcadores();

                            tableroBloqueado = false;
                            temporizadorPausa.stop();
                        }
                    });
                    temporizadorPausa.setRepeats(false);
                    temporizadorPausa.start();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void actualizarMarcadores() {
        Jugador j1 = juego.getJugadores()[0];
        Jugador j2 = juego.getJugadores()[1];

        lblJugador1.setText("<html><b>" + j1.getNombre() + "</b><br>Aciertos: " + j1.getAciertos() + " | Puntos: " + j1.getPuntaje() + "</html>");
        lblJugador2.setText("<html><div style='text-align: right;'><b>" + j2.getNombre() + "</b><br>Aciertos: " + j2.getAciertos() + " | Puntos: " + j2.getPuntaje() + "</div></html>");

        Jugador jugadorActual = juego.getJugadores()[juego.getTurnoActual()];
        lblTurno.setText("Turno actual: " + jugadorActual.getNombre());
    }

    private void declararGanador() {
        Jugador j1 = juego.getJugadores()[0];
        Jugador j2 = juego.getJugadores()[1];
        String mensaje;

        if (j1.getPuntaje() > j2.getPuntaje()) {
            mensaje = "¡Ganador: " + j1.getNombre() + " con " + j1.getPuntaje() + " puntos!";
        } else if (j2.getPuntaje() > j1.getPuntaje()) {
            mensaje = "¡Ganador: " + j2.getNombre() + " con " + j2.getPuntaje() + " puntos!";
        } else {
            mensaje = "¡Empate entre ambos jugadores!";
        }

        JOptionPane.showMessageDialog(this, mensaje, "Fin de la Partida", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
    

