package org.ajedrez.view;

import javafx.scene.image.ImageView;
import org.ajedrez.controller.TableroController;
import org.ajedrez.model.Pieza;
import org.ajedrez.model.Tablero;
import org.ajedrez.model.Torre;

import java.util.Optional;

public class MovimientosEspecialesView {

    private TableroView tableroView;

    public MovimientosEspecialesView(TableroView tableroView){
        this.tableroView = tableroView;
    }

    public void comerPieza(Tablero tablero, Pieza piezaComida,int filaDestino,int columnaDestino){
        // Eliminar la pieza de la interfaz gráfica
        tableroView.eliminarPiezaView(piezaComida);  // Usamos el nuevo método para eliminar la vista
    }

    public PiezaView coronar(Pieza peon, Pieza piezaElejida,int filaDestino,int columnaDestino){
        PiezaView piezaView = tableroView.reemplazarPiezaView(peon,piezaElejida,filaDestino,columnaDestino);
        return piezaView;
    }

    public void enrocar(ImageView torreView, int fila, int columnaDestino){
        tableroView.getGridPane().setColumnIndex(torreView, columnaDestino);
        tableroView.getGridPane().setRowIndex(torreView, fila);
        torreView.setTranslateX(0);
        torreView.setTranslateY(0);
    }
}

