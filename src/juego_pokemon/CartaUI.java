/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego_pokemon;
import javax.swing.*;
import java.awt.*;

public class CartaUI extends JButton {
    private String nombrePokemon;
    private boolean esEspecial;
    private boolean descubierta;

    public CartaUI(String nombrePokemon, boolean esEspecial) {
        this.nombrePokemon = nombrePokemon;
        this.esEspecial = esEspecial;
        this.descubierta = false;
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    public void setDescubierta(boolean descubierta) {
        this.descubierta = descubierta;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();

        if (!descubierta) {
            // Reverso de la carta (Estilo Pokéball / Azul elegante)
            g2d.setColor(new Color(24, 110, 210));
            g2d.fillRoundRect(4, 4, ancho - 8, alto - 8, 12, 12);
            g2d.setColor(new Color(255, 255, 255, 180));
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRoundRect(6, 6, ancho - 12, alto - 12, 10, 10);
            
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("SansSerif", Font.BOLD, 22));
            g2d.drawString("?", ancho / 2 - 6, alto / 2 + 8);
        } else {
            // Frente de la carta (Fondo blanco o dorado si es especial)
            if (esEspecial) {
                g2d.setColor(new Color(255, 250, 230));
                g2d.fillRoundRect(2, 2, ancho - 4, alto - 4, 10, 10);
                g2d.setColor(new Color(218, 165, 32)); // Borde Dorado
                g2d.setStroke(new BasicStroke(2.5f));
                g2d.drawRoundRect(2, 2, ancho - 4, alto - 4, 10, 10);
            } else {
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(2, 2, ancho - 4, alto - 4, 10, 10);
                g2d.setColor(new Color(210, 210, 210));
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.drawRoundRect(2, 2, ancho - 4, alto - 4, 10, 10);
            }

            int cx = ancho / 2;
            int cy = alto / 2 - 12;

            // --- DISEÑOS MEJORADOS Y PROPORCIONADOS PARA CADA POKÉMON ---
            switch (nombrePokemon) {
                case "Pikachu":
                    // Orejas
                    g2d.setColor(new Color(255, 210, 0));
                    g2d.fillPolygon(new int[]{cx - 16, cx - 26, cx - 6}, new int[]{cy - 6, cy - 30, cy - 16}, 3);
                    g2d.fillPolygon(new int[]{cx + 16, cx + 6, cx + 26}, new int[]{cy - 6, cy - 16, cy - 30}, 3);
                    // Cabeza
                    g2d.fillRoundRect(cx - 18, cy - 14, 36, 28, 14, 14);
                    // Mejillas rojas
                    g2d.setColor(new Color(230, 40, 40));
                    g2d.fillOval(cx - 16, cy + 2, 7, 7);
                    g2d.fillOval(cx + 9, cy + 2, 7, 7);
                    // Ojos y boca
                    g2d.setColor(Color.BLACK);
                    g2d.fillOval(cx - 9, cy - 4, 4, 6);
                    g2d.fillOval(cx + 5, cy - 4, 4, 6);
                    g2d.drawArc(cx - 4, cy + 4, 8, 4, 0, -180);
                    break;

                case "Charmander":
                    g2d.setColor(new Color(245, 130, 50));
                    g2d.fillOval(cx - 16, cy - 14, 32, 28);
                    g2d.setColor(Color.BLACK);
                    g2d.fillOval(cx - 9, cy - 4, 4, 6);
                    g2d.fillOval(cx + 5, cy - 4, 4, 6);
                    g2d.drawArc(cx - 5, cy + 4, 10, 5, 0, -180);
                    break;

                case "Bulbasaur":
                    g2d.setColor(new Color(110, 195, 130));
                    g2d.fillOval(cx - 16, cy - 14, 32, 28);
                    g2d.setColor(new Color(80, 150, 100)); // Brote en la cabeza
                    g2d.fillOval(cx - 8, cy - 20, 16, 10);
                    g2d.setColor(new Color(200, 40, 40));
                    g2d.fillOval(cx - 9, cy - 4, 5, 5);
                    g2d.fillOval(cx + 4, cy - 4, 5, 5);
                    break;

                case "Squirtle":
                    g2d.setColor(new Color(85, 165, 235));
                    g2d.fillOval(cx - 16, cy - 14, 32, 28);
                    g2d.setColor(Color.BLACK);
                    g2d.fillOval(cx - 9, cy - 4, 4, 6);
                    g2d.fillOval(cx + 5, cy - 4, 4, 6);
                    g2d.drawArc(cx - 6, cy + 4, 12, 6, 0, -180);
                    break;

                case "Eevee":
                    g2d.setColor(new Color(170, 120, 75));
                    g2d.fillOval(cx - 16, cy - 14, 32, 28);
                    g2d.setColor(Color.WHITE); // Collar de pelo
                    g2d.fillOval(cx - 14, cy + 6, 28, 10);
                    g2d.setColor(Color.BLACK);
                    g2d.fillOval(cx - 9, cy - 4, 4, 5);
                    g2d.fillOval(cx + 5, cy - 4, 4, 5);
                    break;

                case "Snorlax":
                    g2d.setColor(new Color(55, 95, 125));
                    g2d.fillRoundRect(cx - 18, cy - 14, 36, 28, 12, 12);
                    g2d.setColor(new Color(245, 225, 190));
                    g2d.fillOval(cx - 12, cy - 2, 24, 14);
                    g2d.setColor(Color.BLACK);
                    g2d.drawLine(cx - 7, cy - 2, cx - 3, cy - 2);
                    g2d.drawLine(cx + 3, cy - 2, cx + 7, cy - 2);
                    break;

                case "Gengar":
                    g2d.setColor(new Color(130, 90, 170));
                    g2d.fillOval(cx - 16, cy - 14, 32, 28);
                    g2d.setColor(new Color(255, 80, 80));
                    g2d.fillOval(cx - 9, cy - 4, 5, 4);
                    g2d.fillOval(cx + 4, cy - 4, 5, 4);
                    g2d.setColor(Color.BLACK);
                    g2d.drawArc(cx - 7, cy + 2, 14, 6, 0, 180);
                    break;

                case "Psyduck":
                    g2d.setColor(new Color(255, 220, 80));
                    g2d.fillOval(cx - 16, cy - 14, 32, 28);
                    g2d.setColor(new Color(230, 120, 40));
                    g2d.fillOval(cx - 10, cy, 20, 8);
                    g2d.setColor(Color.BLACK);
                    g2d.fillOval(cx - 7, cy - 5, 4, 4);
                    g2d.fillOval(cx + 3, cy - 5, 4, 4);
                    break;

                case "Meowth":
                    g2d.setColor(new Color(220, 200, 140));
                    g2d.fillOval(cx - 16, cy - 14, 32, 28);
                    g2d.setColor(new Color(255, 215, 0)); // Moneda en la frente
                    g2d.fillOval(cx - 5, cy - 14, 10, 8);
                    g2d.setColor(Color.BLACK);
                    g2d.fillOval(cx - 8, cy - 2, 4, 4);
                    g2d.fillOval(cx + 4, cy - 2, 4, 4);
                    break;

                case "Gible":
                    g2d.setColor(new Color(30, 110, 170));
                    g2d.fillOval(cx - 16, cy - 14, 32, 28);
                    g2d.setColor(new Color(210, 50, 50));
                    g2d.fillArc(cx - 10, cy, 20, 12, 0, -180);
                    break;

                case "Toxapex":
                    g2d.setColor(new Color(170, 50, 130));
                    g2d.fillOval(cx - 16, cy - 14, 32, 28);
                    g2d.setColor(new Color(40, 190, 210));
                    g2d.fillOval(cx - 7, cy - 7, 14, 14);
                    break;

                case "Geodude":
                    g2d.setColor(new Color(145, 145, 145));
                    g2d.fillRoundRect(cx - 16, cy - 14, 32, 28, 10, 10);
                    g2d.setColor(Color.BLACK);
                    g2d.fillOval(cx - 8, cy - 4, 4, 4);
                    g2d.fillOval(cx + 4, cy - 4, 4, 4);
                    break;

                case "Mew":
                    g2d.setColor(new Color(255, 175, 190));
                    g2d.fillOval(cx - 15, cy - 13, 30, 26);
                    g2d.setColor(new Color(50, 100, 220));
                    g2d.fillOval(cx - 7, cy - 3, 4, 5);
                    g2d.fillOval(cx + 3, cy - 3, 4, 5);
                    break;

                case "Dratini":
                    g2d.setColor(new Color(100, 160, 215));
                    g2d.fillOval(cx - 15, cy - 13, 30, 26);
                    g2d.setColor(Color.WHITE);
                    g2d.fillOval(cx - 7, cy - 3, 4, 4);
                    g2d.fillOval(cx + 3, cy - 3, 4, 4);
                    break;

                case "Mimikyu":
                    g2d.setColor(new Color(210, 200, 170));
                    g2d.fillOval(cx - 16, cy - 14, 32, 28);
                    g2d.setColor(Color.BLACK);
                    g2d.fillOval(cx - 9, cy - 4, 3, 5);
                    g2d.fillOval(cx + 6, cy - 4, 3, 5);
                    break;

                case "Mewtwo":
                    g2d.setColor(new Color(170, 150, 195));
                    g2d.fillOval(cx - 16, cy - 14, 32, 28);
                    g2d.setColor(new Color(140, 40, 180));
                    g2d.fillOval(cx - 8, cy - 4, 5, 3);
                    g2d.fillOval(cx + 3, cy - 4, 5, 3);
                    break;

                case "Ditto":
                    g2d.setColor(new Color(200, 120, 170));
                    g2d.fillOval(cx - 16, cy - 10, 32, 20);
                    g2d.setColor(Color.BLACK);
                    g2d.drawLine(cx - 5, cy - 2, cx - 3, cy - 2);
                    g2d.drawLine(cx + 3, cy - 2, cx + 5, cy - 2);
                    break;

                case "Lapras":
                    g2d.setColor(new Color(75, 135, 195));
                    g2d.fillOval(cx - 16, cy - 14, 32, 28);
                    g2d.setColor(new Color(240, 220, 190));
                    g2d.fillOval(cx - 10, cy, 20, 10);
                    g2d.setColor(Color.BLACK);
                    g2d.fillOval(cx - 7, cy - 4, 3, 4);
                    g2d.fillOval(cx + 4, cy - 4, 3, 4);
                    break;

                default:
                    g2d.setColor(new Color(190, 190, 190));
                    g2d.fillOval(cx - 15, cy - 13, 30, 26);
                    break;
            }

            // --- ETIQUETA DE TEXTO INFERIOR ---
            g2d.setColor(new Color(50, 50, 50));
            g2d.setFont(new Font("SansSerif", Font.BOLD, 10));
            FontMetrics metrics = g2d.getFontMetrics();
            int x = (ancho - metrics.stringWidth(nombrePokemon)) / 2;
            int y = alto - 8;
            g2d.drawString(nombrePokemon, x, y);
        }
    }
}
