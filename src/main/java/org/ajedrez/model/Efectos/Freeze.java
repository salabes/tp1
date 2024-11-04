package org.ajedrez.model.Efectos;

import javafx.scene.image.ImageView;

/**
 * Clase que representa un efecto de congelación en el juego de ajedrez.
 * Extiende la clase abstracta Efecto y define la lógica específica para el efecto Freeze.
 */
public class Freeze extends Efecto {

    /**
     * Constructor que inicializa el efecto Freeze.
     */
    public Freeze() {
        super(); // Llama al constructor de la clase base Efecto.
    }

    /**
     * Establece la cantidad de turnos que el efecto Freeze estará activo.
     * En este caso, el efecto permanecerá activo durante 4 turnos.
     */
    @Override
    public void setCantidadTurnosActivos() {
        this.cantidadTurnosActivos += 4; // Implementar la cantidad de turnos activos para el efecto Freeze.
    }

    /**
     * Obtiene el tipo de efecto.
     *
     * @return Un String que representa el tipo de efecto, en este caso "freeze".
     */
    @Override
    public TipoEfecto getTipo() {
        return TipoEfecto.FREEZE; // Implementar el tipo de efecto Freeze.// Implementar el tipo de efecto Freeze.
    }
}
