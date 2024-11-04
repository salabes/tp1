package org.ajedrez.model.Efectos;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;

/**
 * Clase que representa un efecto de protección en el juego de ajedrez.
 * Extiende la clase abstracta Efecto y define la lógica específica para el efecto Protect.
 */
public class Protect extends Efecto {

    /**
     * Constructor que inicializa el efecto Protect.
     */
    public Protect() {
        super(); // Llama al constructor de la clase base Efecto.
    }

    /**
     * Establece la cantidad de turnos que el efecto Protect estará activo.
     * En este caso, el efecto permanecerá activo durante 4 turnos.
     */
    @Override
    public void setCantidadTurnosActivos() {
        this.cantidadTurnosActivos += 4; // Establece la cantidad de turnos activos para el efecto Protect.
    }

    /**
     * Obtiene el tipo de efecto.
     *
     * @return Un String que representa el tipo de efecto, en este caso "protect".
     */
    @Override
    public TipoEfecto getTipo() {
        return TipoEfecto.PROTECT; // Implementar el tipo de efecto Protect.
    }


}
