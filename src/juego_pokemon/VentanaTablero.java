package juego_pokemon;
import javax.swing.*;
import java.awt.*;
public class VentanaTablero extends JFrame{
    
    public VentanaTablero(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setLayout(new BorderLayout());
        this.setTitle("JUEGO DE MEMORIA POKEMON");
        
        JPanel panelSup=new JPanel();
        panelSup.setSize(600, 600);
        panelSup.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
    }
}