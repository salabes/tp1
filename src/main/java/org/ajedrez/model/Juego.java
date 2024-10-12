package org.ajedrez.model;

import java.util.List;

/**
 * Clase que representa una partida de ajedrez.
 */
public class Juego {
    private List<Jugador> jugadores; // Lista de jugadores en el juego
    private Tablero tablero; // Tablero de ajedrez
    private AdministradorDeMovimientos administradordemovimientos; // Administra los movimientos de las piezas
    private AdministradorDeTurnos administradordeturnos; // Administra los turnos de los jugadores

    /**
     * Constructor de la clase Juego.
     * Inicializa los jugadores, el tablero, y los administradores de movimientos y turnos.
     *
     * @param jugadores Lista de jugadores que participan en el juego.
     */
    public Juego(List<Jugador> jugadores) {
        this.jugadores = jugadores; // Asigna la lista de jugadores
        this.tablero = new Tablero(); // Crea un nuevo tablero
        this.administradordeturnos = new AdministradorDeTurnos(jugadores); // Crea un administrador de turnos
        this.administradordemovimientos = new AdministradorDeMovimientos(); // Crea un administrador de movimientos
    }

    /**
     * Obtiene el tablero de ajedrez.
     *
     * @return El tablero de ajedrez.
     */
    public Tablero getTablero() {
        return this.tablero; // Retorna el tablero
    }

    /**
     * Obtiene el jugador según su color.
     *
     * @param color Color del jugador que se quiere obtener.
     * @return El jugador correspondiente al color especificado.
     */
    public Jugador getJugadorSegunColor(Color color) {
        if (this.jugadores.get(0).getColor() == color) {
            return this.jugadores.get(0); // Retorna el primer jugador si su color coincide
        }
        return this.jugadores.get(1); // Retorna el segundo jugador si el color no coincide
    }

    /**
     * Verifica si un movimiento es inválido.
     *
     * @param filaOriginal Fila de la posición original de la pieza.
     * @param columnaOriginal Columna de la posición original de la pieza.
     * @param filaDestino Fila de la posición de destino de la pieza.
     * @param columnaDestino Columna de la posición de destino de la pieza.
     * @return true si el movimiento es inválido, false en caso contrario.
     */
    public boolean movimientoInvalido(int filaOriginal, int columnaOriginal, int filaDestino, int columnaDestino) {
        return this.administradordemovimientos.movimientoInValido(this.tablero, filaOriginal, columnaOriginal, filaDestino, columnaDestino);
    }

    /**
     * Avanza al siguiente turno en el juego.
     */
    public void siguienteTurno() {
        this.administradordeturnos.siguienteTurno(); // Cambia al siguiente turno
    }

    /**
     * Obtiene el color del jugador cuyo turno es actual.
     *
     * @return El color del jugador que tiene el turno actual.
     */
    public Color getColorJugadorTurnoActual() {
        return this.administradordeturnos.getTurnoActual().getColor(); // Retorna el color del jugador actual
    }

    /**
     * Obtiene el jugador que tiene el turno actual.
     *
     * @return El jugador que tiene el turno actual.
     */
    public Jugador getJugadorTurnoActual() {
        return this.administradordeturnos.getTurnoActual(); // Retorna el jugador actual
    }

    /**
     * Obtiene el administrador de turnos.
     *
     * @return El administrador de turnos.
     */
    public AdministradorDeTurnos getAdministradorDeTurnos() {
        return administradordeturnos; // Retorna el administrador de turnos
    }
}
