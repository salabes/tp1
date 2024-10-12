package org.ajedrez.model.Efectos;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;

/**
 * Clase que representa un efecto de volar en el juego de ajedrez.
 * Extiende la clase abstracta Efecto y define la lógica específica para el efecto Volar.
 */
public class Volar extends Efecto {

    /**
     * Constructor que inicializa el efecto Volar y activa el efecto al instante.
     */
    public Volar() {
        super(); // Llama al constructor de la clase base Efecto.
        activarEfecto(); // Activa el efecto al momento de instanciar la clase.
    }

    /**
     * Establece la cantidad de turnos que el efecto Volar estará activo.
     * En este caso, el efecto permanecerá activo durante 1 turno.
     */
    @Override
    public void setCantidadTurnosActivos() {
        this.cantidadTurnosActivos = 1; // Establece la cantidad de turnos activos para el efecto Volar.
    }

    /**
     * Obtiene el tipo de efecto.
     *
     * @return Un String que representa el tipo de efecto, en este caso "volar".
     */
    @Override
    public String getTipo() {
        return "volar"; // Tipo de efecto.
    }

    /**
     * Aplica el efecto de volar visualmente en el componente proporcionado.
     * Añade un efecto de sombra azul alrededor de la vista para indicar que puede volar.
     *
     * @param vista El componente de vista (ImageView) donde se aplicará el efecto.
     */
    @Override
    public void aplicarEfectoView(ImageView vista) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(javafx.scene.paint.Color.BLUE); // Color del resplandor.
        dropShadow.setRadius(25); // Tamaño del resplandor.
        vista.setEffect(dropShadow); // Aplica el efecto de sombra a la vista.
    }
}
