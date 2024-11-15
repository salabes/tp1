package org.ajedrez.model;

/**
 * Clase que representa a un jugador en una partida de ajedrez.
 */
public class Jugador {
    private String nombre; // Nombre del jugador
    private Color color; // Color de las piezas del jugador
    private Temporizador tiempo; // Temporizador asociado al jugador

    /**
     * Constructor de la clase Jugador.
     * Inicializa el nombre, color y temporizador del jugador.
     *
     * @param nombre Nombre del jugador.
     * @param color Color de las piezas que utiliza el jugador.
     * @param tiempo Temporizador asociado al jugador.
     */
    public Jugador(String nombre, Color color, Temporizador tiempo) {
        this.nombre = nombre; // Asigna el nombre del jugador
        this.color = color; // Asigna el color del jugador
        this.tiempo = tiempo; // Asigna el temporizador del jugador
    }

    /**
     * Obtiene el nombre del jugador.
     * Si el nombre está vacío, devuelve un nombre predeterminado según el color del jugador.
     *
     * @return El nombre del jugador o un nombre predeterminado si está vacío.
     */
    public String getNombre() {
        if (this.nombre.isEmpty()) {
            if (getColor() == Color.BLANCO) {
                return "Jugador Blancas"; // Nombre predeterminado para jugador de piezas blancas
            }
            return "Jugador Negras"; // Nombre predeterminado para jugador de piezas negras
        }
        return nombre; // Retorna el nombre del jugador
    }

    /**
     * Obtiene el color del jugador.
     *
     * @return El color de las piezas del jugador.
     */
    public Color getColor() {
        return color; // Retorna el color del jugador
    }

    /**
     * Obtiene el temporizador del jugador.
     *
     * @return El temporizador asociado al jugador.
     */
    public Temporizador getTemporizador() {
        return tiempo; // Retorna el temporizador del jugador
    }

    /**
     * Inicia el temporizador del jugador.
     */
    public void iniciarTemporizador() {
        tiempo.iniciar(); // Inicia el temporizador
    }

    /**
     * Pausa el temporizador del jugador.
     */
    public void pausarTemporizador() {
        tiempo.pausar(); // Pausa el temporizador
    }
}
