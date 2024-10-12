package org.ajedrez.model;

/**
 * Clase que representa un casillero en el tablero de ajedrez.
 */
public class Casillero {
    private Pieza pieza; // Pieza que ocupa el casillero
    private boolean ocupado; // Indica si el casillero está ocupado por una pieza

    /**
     * Constructor de la clase Casillero.
     * Inicializa el casillero sin ninguna pieza y como no ocupado.
     */
    public Casillero() {
        pieza = null; // No hay pieza en el casillero
        ocupado = false; // El casillero no está ocupado
    }

    /**
     * Establece una pieza en el casillero.
     *
     * @param pieza Pieza que se quiere colocar en el casillero.
     */
    public void setPieza(Pieza pieza) {
        this.pieza = pieza; // Asigna la pieza al casillero
        ocupado = true; // Marca el casillero como ocupado
    }

    /**
     * Obtiene la pieza que ocupa el casillero.
     *
     * @return La pieza en el casillero, o null si no hay ninguna.
     */
    public Pieza getPieza() {
        return pieza; // Retorna la pieza en el casillero
    }

    /**
     * Verifica si el casillero está ocupado.
     *
     * @return true si el casillero está ocupado, false en caso contrario.
     */
    public boolean estaOcupada() {
        return ocupado; // Devuelve el estado de ocupación del casillero
    }

    /**
     * Limpia el casillero, eliminando la pieza y marcándolo como no ocupado.
     */
    public void limpiarCasillero() {
        pieza = null; // Elimina la pieza del casillero
        ocupado = false; // Marca el casillero como no ocupado
    }
}
