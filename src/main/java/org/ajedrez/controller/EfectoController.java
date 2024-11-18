package org.ajedrez.controller;

import javafx.scene.image.ImageView;
import org.ajedrez.model.*;
import org.ajedrez.model.Efectos.Efecto;
import org.ajedrez.view.TableroView;
import org.ajedrez.view.EfectoView;
import java.util.Optional;

/**
 * El controlador responsable de gestionar la aplicación y el manejo de los efectos en las piezas del ajedrez.
 * Los efectos pueden ser temporales y afectan tanto la lógica como la visualización de las piezas.
 */
public class EfectoController {

    /** El efecto que ha sido seleccionado para aplicarse en una pieza. */
    private Efecto efectoElejido;
    private EfectoView efectoView = new EfectoView();

    /**
     * Establece el efecto que será aplicado a una pieza.
     *
     * @param efectoElejido El efecto seleccionado.
     */
    public void setEfectoElejido(Efecto efectoElejido) {
        this.efectoElejido = efectoElejido;
    }

    /**
     * Obtiene el efecto actualmente seleccionado.
     *
     * @return El efecto seleccionado.
     */
    public Efecto getEfectoElejido() {
        return efectoElejido;
    }

    /**
     * Aplica el efecto seleccionado a una pieza de ajedrez y actualiza su visualización.
     *
     * @param vistaImagen La vista de la imagen de la pieza en el tablero.
     * @param pieza La pieza sobre la que se aplicará el efecto.
     */
    public void aplicarEfecto(ImageView vistaImagen, Pieza pieza) {
        // Asigna el efecto a la pieza
        pieza.setEfecto(efectoElejido);
        // Activa el efecto en la pieza
        pieza.getEfecto().activarEfecto();
        // Aplica los cambios visuales del efecto en la vista de la pieza
        efectoView.aplicarEfectoView(pieza.getEfecto(), vistaImagen);
    }

    /**
     * Descuenta el tiempo restante de los efectos aplicados a las piezas del tablero.
     * Si el tiempo del efecto ha expirado, elimina el efecto de la pieza y restablece su apariencia.
     *
     * @param juego El estado actual del juego.
     * @param tableroView La vista del tablero que se debe actualizar visualmente.
     */
    public static void descontarEfectos(Juego juego, TableroView tableroView) {
        // Recorre cada posición del tablero para verificar si alguna pieza tiene un efecto activo
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Optional<Pieza> piezaOpt = juego.getTablero().getPieza(i, j);

                if (piezaOpt.isPresent()) {
                    Pieza pieza = piezaOpt.get();

                    // Si la pieza tiene un efecto activo
                    if (pieza.getEfecto() != null) {
                        // Descuenta el tiempo restante del efecto
                        if (!pieza.getEfecto().descontarTiempo()) { // Si el tiempo ha terminado
                            // Quita los efectos visuales de la pieza
                            tableroView.obtenerVistaDePieza(pieza).setEffect(null);
                            tableroView.obtenerVistaDePieza(pieza).setOpacity(1);

                            // Elimina el efecto de la pieza
                            pieza.setEfecto(null);
                        }
                    }
                }
            }
        }
    }
}
