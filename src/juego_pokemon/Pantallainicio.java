/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego_pokemon;

/**
 *
 * @author marlo
 */
import juego_pokemon.NombreInvalido;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pantallainicio extends JFrame{
    private JTextField txtJugador1;
    private JTextField txtJugador2;
    private JButton btnIniciar;

    public Pantallainicio() {
        setTitle("Juego de memoria Pokemon - configuracion");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(4, 1, 10, 10));
        JPanel panelJ1 = new JPanel();
        panelJ1.add(new JLabel("Nombre jugador 1:"));
        txtJugador1 = new JTextField(15);
        panelJ1.add(txtJugador1);
        JPanel panelJ2 = new JPanel();
        panelJ2.add(new JLabel("Nombre jugador 2:"));
        txtJugador2 = new JTextField(15);
        panelJ2.add(txtJugador2);
        JPanel panelBtn = new JPanel();
        btnIniciar = new JButton("Iniciar juego");
        panelBtn.add(btnIniciar);
        add(new JLabel("configuracion de partida",SwingConstants.CENTER));
        add(panelJ1);
        add(panelJ2);
        add(panelBtn);
        btnIniciar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                intentarIniciarJuego();
            }
        });
    }
    private void intentarIniciarJuego(){
            try {
                String nombre1 = txtJugador1.getText().trim();
                String nombre2 = txtJugador2.getText().trim();
                validarNombres(nombre1, nombre2);
                VentanaTablero tablero = new VentanaTablero(nombre1, nombre2);
                tablero.setVisible(true);
                this.dispose();
            }
            catch (NombreInvalido ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage(),"Error de Validación",JOptionPane.ERROR_MESSAGE);
            }
    }
    private void validarNombres(String n1, String n2) throws NombreInvalido {
        if(n1.isEmpty()||n2.isEmpty()){
            throw new NombreInvalido("Los nombres no pueden estar vacíos ni compuestos solo por espacios");
        }
        if(n1.length()>15||n2.length()> 15){
            throw new NombreInvalido("Los nombres son excesivamente largos, el maximo es de 15 caracteres");
        }
        if(n1.equalsIgnoreCase(n2)){
            throw new NombreInvalido("Los nombres de los jugadores no pueden ser iguales");
        }
    }
}
