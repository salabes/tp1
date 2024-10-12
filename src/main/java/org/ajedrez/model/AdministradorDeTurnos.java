package org.ajedrez.model;

import java.util.List;

/**
 * Clase que gestiona los turnos de los jugadores en el juego de ajedrez.
 */
public class AdministradorDeTurnos {

    private int turnos = 0; // Contador de turnos, inicia en 0
    private List<Jugador> jugadores; // Lista de jugadores en el juego

    /**
     * Constructor de la clase AdministradorDeTurnos.
     *
     * @param jugadores Lista de jugadores que participarán en el juego.
     */
    public AdministradorDeTurnos(List<Jugador> jugadores) {
        this.turnos = 0; // Inicializa el contador de turnos a 0
        this.jugadores = jugadores; // Asigna la lista de jugadores
    }

    /**
     * Avanza al siguiente turno. Reinicia el contador de turnos si supera 1.
     */
    public void siguienteTurno() {
        this.turnos += 1; // Incrementa el contador de turnos

        // Si turnos es mayor que 1, reinícialo a 0
        if (this.turnos > 1) {
            this.turnos = 0; // Reinicia el contador de turnos
        }
    }

    /**
     * Obtiene el jugador cuyo turno es el actual.
     *
     * @return El jugador que tiene el turno actual.
     */
    public Jugador getTurnoActual() {
        return this.jugadores.get(this.turnos); // Devuelve el jugador en el índice del turno actual
    }

    /**
     * Obtiene el jugador actual, considerando que los turnos alternan entre los jugadores.
     *
     * @return El jugador que está en turno actualmente (0 o 1).
     */
    public Jugador getJugadorActual() {
        return this.jugadores.get(this.turnos % 2); // Devuelve el jugador actual alternando entre los dos
    }
}
