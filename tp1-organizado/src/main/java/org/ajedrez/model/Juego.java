package org.ajedrez.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Juego {
    private List<Jugador> jugadores;
    private Tablero tablero;
    private AdministradorDeMovimientos administradordemovimientos;
    private AdministradorDeTurnos administradordeturnos;

    public Juego(List<Jugador> jugadores){
        this.jugadores = jugadores;
        this.tablero = new Tablero();
        this.administradordeturnos = new AdministradorDeTurnos();
        this.administradordemovimientos = new AdministradorDeMovimientos();
    }

    public Tablero getTablero(){
        return this.tablero;
    }

}