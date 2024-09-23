package org.ajedrez.model;

public class Juego {
    Tablero tablero;
    list<Jugador> jugadores;
    enumTurno turno;
    enumEstado estado;

    public Juego() {
        tablero = new Tablero();
        jugadores = new list<Jugador>();
        turno = enumTurno.JUGADOR1;
        estado = enumEstado.EN_CURSO;
    }

    public void IniciarJuego() {

    }

    public void terminarJuego() {

    }

    public void CambiarTurno() {

    }

    public void VeridicarEstado() {

    }
}
