package org.ajedrez.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Clase que representa un temporizador para controlar el tiempo restante en un juego de ajedrez.
 */
public class Temporizador {
    private int tiempoRestante; // Tiempo en segundos
    private Timeline timeline;
    private TemporizadorListener listener;// Línea de tiempo para gestionar el temporizador

    /**
     * Constructor que inicializa el temporizador con un tiempo en minutos.
     *
     * @param minutosIniciales Tiempo inicial del temporizador en minutos.
     */
    public Temporizador(int minutosIniciales) {
        this.tiempoRestante = minutosIniciales * 60; // Convertir minutos a segundos

        // Crear el Timeline que ejecuta cada segundo
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> decrementarTiempo()));
        timeline.setCycleCount(Timeline.INDEFINITE); // Repetir indefinidamente
    }

    /**
     * Obtiene el tiempo restante en segundos.
     *
     * @return Tiempo restante en segundos.
     */
    public int TiempoRestante() {
        return tiempoRestante;
    }

    public void setListener(TemporizadorListener listener) {
        this.listener = listener;
    }


    /**
     * Inicia el temporizador.
     */
    public void iniciar() {
        timeline.play();
    }

    /**
     * Pausa el temporizador.
     */
    public void pausar() {
        timeline.pause();
    }

    /**
     * Decrementa el tiempo en 1 segundo y actualiza la etiqueta.
     */
    private void decrementarTiempo() {
        if (tiempoRestante > 0) {
            tiempoRestante--; // Disminuye el tiempo restante
            if (listener != null) {
                listener.onTiempoActualizado(tiempoRestante);
            } // Actualiza la visualización
        } else {
            timeline.stop(); // Detiene el temporizador al llegar a cero
            // Manejar cuando el tiempo se agota (ej. fin del juego)
        }
    }
}
