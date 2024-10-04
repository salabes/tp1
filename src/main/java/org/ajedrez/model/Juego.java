package org.ajedrez.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Juego {
    private List<Jugador> jugadores;
    private Tablero tablero;
    private AdministradorDeMovimientos administradordemovimientos;
    private AdministradorDeTurnos administradordeturnos;

    public Juego(String nombreJugador1, String nombreJugador2){
        List<Color> colores = new ArrayList<>();
        Collections.shuffle(colores);
        colores.add(Color.NEGRO);
        colores.add(Color.BLANCO);

        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();

        this.tablero = new Tablero();
        this.administradordeturnos = new AdministradorDeTurnos();
        this.administradordemovimientos = new AdministradorDeMovimientos();
    }

    public Tablero getTablero(){
        return this.tablero;
    }

}
