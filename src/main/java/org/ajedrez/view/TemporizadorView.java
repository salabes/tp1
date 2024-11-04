package org.ajedrez.view;

import javafx.scene.control.Label;

public class TemporizadorView {
    private Label etiquetaTiempo;

    public TemporizadorView(Label etiquetaTiempo) {
        this.etiquetaTiempo = etiquetaTiempo;
    }

    public void actualizarTiempo(int tiempoRestante) {
        int minutos = tiempoRestante / 60;
        int segundos = tiempoRestante % 60;
        etiquetaTiempo.setText(String.format("%02d:%02d", minutos, segundos));
    }
}