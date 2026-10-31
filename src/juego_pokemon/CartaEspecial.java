/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego_pokemon;


public class CartaEspecial extends Carta {

    public CartaEspecial(String nombrePokemon) {
        super(nombrePokemon);
    }

    @Override
    public int ganarPuntos() {
        return 2;
    }
}