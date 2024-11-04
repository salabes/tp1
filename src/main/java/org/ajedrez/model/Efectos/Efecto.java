package org.ajedrez.model.Efectos;

import javafx.scene.image.ImageView;

/**
 * Clase abstracta que representa un efecto en el juego de ajedrez.
 * Proporciona métodos para activar el efecto, descontar turnos, y aplicar efectos visuales.
 */
public abstract class Efecto {
    private boolean efectoactivo; // Indica si el efecto está activo.
    protected int cantidadTurnosActivos; // Número de turnos que el efecto estará activo.

    /**
     * Constructor que inicializa el efecto como inactivo.
     */
    public Efecto() {
        efectoactivo = false;
    }

    /**
     * Activa el efecto y establece la cantidad de turnos activos.
     */
    public void activarEfecto() {
        efectoactivo = true;
        setCantidadTurnosActivos();
    }

    /**
     * Descuenta un turno del efecto activo. Si se alcanzan 0 turnos activos,
     * el efecto se desactiva.
     *
     * @return true si el efecto sigue activo, false si se ha desactivado.
     */
    public boolean descontarTiempo() {
        if (efectoactivo) {
            cantidadTurnosActivos -= 1;

            if (cantidadTurnosActivos == 0) {
                efectoactivo = false; // Desactivar el efecto si los turnos llegan a 0.
            }
        }

        return efectoactivo; // Retorna el estado del efecto.
    }

    /**
     * Verifica si el efecto está activo.
     *
     * @return true si el efecto está activo, false en caso contrario.
     */
    public boolean isEfectoActivo() {
        return efectoactivo; // Método para verificar si el efecto está activo.
    }


    /**
     * Establece la cantidad de turnos que el efecto estará activo.
     */
    public abstract void setCantidadTurnosActivos();

    /**
     * Obtiene el tipo de efecto.
     *
     * @return Un String que representa el tipo de efecto.
     */
    public abstract TipoEfecto getTipo();
}
