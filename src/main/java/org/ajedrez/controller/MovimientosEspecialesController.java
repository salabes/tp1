package org.ajedrez.controller;

import javafx.scene.image.ImageView;
import org.ajedrez.model.*;
import org.ajedrez.view.MovimientosEspecialesView;
import org.ajedrez.view.PiezaView;



import java.util.Optional;

import org.ajedrez.model.Pieza;
import org.ajedrez.model.Tablero;


/**
 * Controlador para manejar movimientos especiales en el ajedrez, tales como enroque y coronación de peones.
 * Se encarga de la interacción entre la vista, el tablero y las piezas para realizar estos movimientos.
 */
public class MovimientosEspecialesController {
    private MovimientosEspecialesView movimientosEspecialesView;
    private TableroController tableroController;
    private Pieza pieza;
    private int filaDestino;
    private int columnaDestino;

    /**
     * Constructor que inicializa el controlador con el controlador del tablero.
     * @param tableroController El controlador del tablero que contiene la lógica del juego.
     */
    public MovimientosEspecialesController(TableroController tableroController) {
        this.movimientosEspecialesView = new MovimientosEspecialesView(tableroController.getTableroView());
        this.tableroController = tableroController;
    }

    /**
     * Asigna un nuevo controlador de tablero.
     * @param tableroController El nuevo controlador de tablero.
     */
    public void setTablero(TableroController tableroController){
        this.tableroController =  tableroController;
    }

    /**
     * Verifica si un movimiento especial ha ocurrido (enroque o coronación de peón).
     * Si el movimiento es de un peón y llega a la fila final, se coronará.
     * Si el movimiento es de un rey y cumple las condiciones de enroque, se realiza el enroque.
     * @param filaOrigen La fila de origen del movimiento.
     * @param columnaOrigen La columna de origen del movimiento.
     * @param filaDestino La fila de destino del movimiento.
     * @param columnaDestino La columna de destino del movimiento.
     * @return true si hubo coronación de peón, false en otro caso.
     * @throws Exception Si ocurre algún problema durante la coronación.
     */
    public boolean chequearMovimientoEspecial(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) throws Exception{
        // Verificar si hay una pieza en la nueva posición (casilla de destino)
        Pieza pieza = tableroController.getTablero().getPieza(filaOrigen, columnaOrigen).get();

        this.pieza = pieza;
        this.filaDestino = filaDestino;
        this.columnaDestino = columnaDestino;
        boolean huboCoronacion = false;

        // Verifica si alguna pieza fue comida
        chequearPiezaComida(tableroController.getTablero());

        // Si es un peón, verificar si llega a la coronación
        if(pieza.getTipo() == TipoPieza.PEON){
            Peon peon = (Peon) pieza;
            huboCoronacion = chequearCoronar(peon);
        }

        // Si es un rey, verificar si puede realizar enroque
        if (pieza.getTipo() == TipoPieza.REY) {
            int deltaColumna = this.columnaDestino - columnaOrigen;
            if (Math.abs(deltaColumna) == 2) {
                chequearEnroque(deltaColumna);
            }
            ((Rey) pieza).marcarComoMovido();
        }

        return huboCoronacion;
    }

    /**
     * Verifica si hay una pieza en la posición de destino y la elimina si es necesario.
     * Además, mueve la pieza comida al área correspondiente de piezas eliminadas.
     * @param tablero El tablero donde se encuentra la pieza.
     */
/*     public void chequearPiezaComida(Tablero tablero){
        Optional<Pieza> piezaDestinoOpt = tablero.getPieza(this.filaDestino, this.columnaDestino);

        if (piezaDestinoOpt.isPresent()) {
            Pieza pieza = piezaDestinoOpt.get();
            // Elimina la pieza de la vista
            movimientosEspecialesView.comerPieza(tablero,pieza,this.filaDestino,this.columnaDestino);

            // Elimina la pieza del modelo
            tablero.eliminarPieza(this.filaDestino,this.columnaDestino);

            // Agrega la pieza eliminada al área correspondiente en la vista
            PiezaView piezaView = new PiezaView(pieza.getImagen());
            tableroController.getPiezasEliminadasController().agregarPiezaEliminadaAGridPane(piezaView.getVistaPieza(),pieza.getColor());
        }
    } */
    public void chequearPiezaComida(Tablero tablero) {
        Optional<Pieza> piezaDestinoOpt = tablero.getPieza(this.filaDestino, this.columnaDestino);
    
        if (piezaDestinoOpt.isPresent()) {
            Pieza pieza = piezaDestinoOpt.get();
            movimientosEspecialesView.comerPieza(tablero, pieza, this.filaDestino, this.columnaDestino);
    
            // Elimina la pieza del modelo
            tablero.eliminarPieza(this.filaDestino, this.columnaDestino);
    
            // Usa `crearPiezaView` para obtener la vista específica de la pieza eliminada
            PiezaView piezaView = tableroController.getTableroView().crearPiezaView(pieza);
            tableroController.getPiezasEliminadasController().agregarPiezaEliminadaAGridPane(
                piezaView.getVistaPieza(), pieza.getColor()
            );
        }
    }
    


    /**
     * Verifica si un peón ha llegado a la última fila para coronarlo.
     * Si es así, se abre una ventana para que el jugador elija la pieza a la que coronar el peón.
     * @param peon El peón que se está moviendo.
     * @return true si el peón debe coronarse, false en otro caso.
     * @throws Exception Si ocurre algún error al abrir la ventana de coronación.
     */
    public boolean chequearCoronar(Peon peon) throws Exception {
        if(peon.haLlegadoAFilaFinal(this.filaDestino)){
            VentanaController ventanaController = new VentanaController();
            ventanaController.abrirVentanaCoronacion(null,peon.getColor(),this.filaDestino,this.columnaDestino,this);
            return true;
        }
        return false;
    }

    /**
     * Realiza el proceso de coronar un peón, reemplazando la pieza en el tablero con la elegida.
     * @param piezaElejida La pieza a la que se coronará el peón.
     */
    public void coronarPeon(Pieza piezaElejida){
        // Actualiza la vista con la pieza coronada
        PiezaView piezaView = movimientosEspecialesView.coronar(this.pieza,piezaElejida,this.filaDestino,this.columnaDestino);

        // Reemplaza la pieza en el modelo
        tableroController.getTablero().reemplazarPieza(piezaElejida,this.filaDestino,this.columnaDestino);

        // Asigna los eventos de la nueva pieza
        tableroController.setearEventos(piezaView,this.filaDestino,this.columnaDestino);

        // Actualiza los temporizadores tras la coronación
        tableroController.cambiarTurnos();
    }

    /**
     * Realiza el enroque entre el rey y la torre, moviendo la torre a su nueva posición.
     * @param deltaColumna La diferencia de columnas entre la posición actual del rey y la posición de destino.
     */
    public void chequearEnroque(int deltaColumna){
        int columnaOrigen;
        int columnaDestino;

        // Determina la dirección del enroque
        if(deltaColumna > 0){
            columnaOrigen = 7;  // Enroque corto
            columnaDestino = 5;
        } else {
            columnaOrigen = 0;  // Enroque largo
            columnaDestino = 3;
        }

        Optional<Pieza> torreOpt = tableroController.getTablero().getPieza(this.filaDestino, columnaOrigen);

        // Verificar que hay una torre para enrocar
        if (torreOpt.isPresent() && torreOpt.get().getTipo() == TipoPieza.TORRE) {
            ImageView torreView = tableroController.getTableroView().obtenerVistaDePieza(torreOpt.get());
            if (torreView != null){
                // Mueve la torre en la vista
                movimientosEspecialesView.enrocar(torreView,this.filaDestino,columnaDestino);

                // Mueve la torre en el modelo
                tableroController.getTablero().moverPieza(this.filaDestino, columnaOrigen, filaDestino, columnaDestino);

                // Actualiza el evento de soltar la torre
                tableroController.resetearSoltarMouse(torreView, filaDestino, columnaDestino);
            }
        }
    }
}
