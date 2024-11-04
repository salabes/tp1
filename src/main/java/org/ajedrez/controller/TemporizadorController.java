package org.ajedrez.controller;

import javafx.scene.control.Label;
import org.ajedrez.model.Color;
import org.ajedrez.model.Jugador;
import org.ajedrez.model.Temporizador;
import org.ajedrez.view.TemporizadorView;

public class TemporizadorController {
    private TemporizadorView temporizadorViewBlancas;
    private TemporizadorView temporizadorViewNegras;

    public TemporizadorController(Temporizador temporizadorBlancas, Temporizador temporizadorNegras, Label etiquetaTemporizadorBlancas, Label etiquetaTemporizadorNegras)
    {
        this.temporizadorViewBlancas = new TemporizadorView(etiquetaTemporizadorBlancas);
        this.temporizadorViewNegras = new TemporizadorView(etiquetaTemporizadorNegras);

        // Configura el listener para actualizar la vista
        temporizadorBlancas.setListener(tiempoRestante -> temporizadorViewBlancas.actualizarTiempo(tiempoRestante));
        temporizadorNegras.setListener(tiempoRestante -> temporizadorViewNegras.actualizarTiempo(tiempoRestante));

        temporizadorViewBlancas.actualizarTiempo(temporizadorBlancas.TiempoRestante());
        temporizadorViewNegras.actualizarTiempo(temporizadorNegras.TiempoRestante());

    }

    public void iniciar(Jugador jugador){
        jugador.iniciarTemporizador();
    }

    public void parar(Jugador jugador){
        jugador.pausarTemporizador();
    }

}