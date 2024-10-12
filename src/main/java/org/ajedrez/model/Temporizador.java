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
    private Label etiquetaTiempo; // Etiqueta para mostrar el tiempo
    private Timeline timeline; // Línea de tiempo para gestionar el temporizador

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

    /**
     * Establece la etiqueta donde se mostrará el tiempo restante.
     *
     * @param etiquetaTiempo La etiqueta a actualizar con el tiempo.
     */
    public void setEtiquetaTiempo(Label etiquetaTiempo) {
        this.etiquetaTiempo = etiquetaTiempo;
        actualizarEtiquetaTiempo(); // Actualiza la etiqueta inmediatamente al establecerla
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
            actualizarEtiquetaTiempo(); // Actualiza la visualización
        } else {
            timeline.stop(); // Detiene el temporizador al llegar a cero
            // Manejar cuando el tiempo se agota (ej. fin del juego)
        }
    }

    /**
     * Actualiza la etiqueta con el tiempo restante en formato mm:ss.
     */
    private void actualizarEtiquetaTiempo() {
        int minutos = tiempoRestante / 60; // Calcula los minutos
        int segundos = tiempoRestante % 60; // Calcula los segundos
        etiquetaTiempo.setText(String.format("%02d:%02d", minutos, segundos)); // Establece el texto en formato mm:ss
    }
}
