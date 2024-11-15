package org.ajedrez.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import org.ajedrez.model.*;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * El controlador principal que maneja la inicialización y configuración de un nuevo juego de ajedrez.
 * Gestiona la selección de colores de los jugadores, la creación de los jugadores y el inicio del temporizador.
 */
public class JuegoController {

    /** Instancia del juego en curso. */
    private Juego juego;

    /** Color del jugador 1. */
    private Color colorJugador1;

    /** Color del jugador 2. */
    private Color colorJugador2;

    @FXML
    private TextField Jugador1;  // Campo de texto para ingresar el nombre del Jugador 1

    @FXML
    private TextField Jugador2;  // Campo de texto para ingresar el nombre del Jugador 2

    @FXML
    private RadioButton btnBlancas;  // Botón para seleccionar piezas Blancas

    @FXML
    private RadioButton btnNegras;   // Botón para seleccionar piezas Negras

    @FXML
    private ToggleGroup grupoPiezas; // Grupo que contiene las opciones de colores para las piezas

    /**
     * Método inicializador que se ejecuta automáticamente al cargar el FXML.
     * Establece los colores predeterminados para los jugadores.
     */
    @FXML
    public void initialize() {
        InicializarEleccionColor();  // Configura las opciones de selección de color
        this.colorJugador1 = Color.BLANCO;  // El Jugador 1 usa Blanco por defecto
        this.colorJugador2 = Color.NEGRO;  // El Jugador 2 usa Negro por defecto
    }

    /**
     * Inicia la partida de ajedrez cuando se dispara el evento de inicio.
     *
     * @param event El evento que dispara el inicio del juego.
     * @throws Exception Si ocurre algún error al abrir la ventana del tablero.
     */
    public void iniciarPartida(ActionEvent event) throws Exception {
        // Inicia el juego con los nombres ingresados
        iniciarJuego(List.of(Jugador1.getText(), Jugador2.getText()));

        // Abre la ventana del tablero
        VentanaController ventanaController = new VentanaController();
        TableroController tablerocontroller = ventanaController.abrirVentanaTablero(event);

        // Configura el juego y actualiza las etiquetas del tablero
        tablerocontroller.setJuego(this.juego);
        tablerocontroller.setLabels();
        tablerocontroller.cargarTablero();

        // Inicia el temporizador del jugador con las piezas Blancas
        this.juego.getJugadorSegunColor(Color.BLANCO).iniciarTemporizador();
    }

    /**
     * Inicializa un nuevo juego de ajedrez con los nombres de los jugadores proporcionados.
     *
     * @param nombres Los nombres de los jugadores.
     */
    public void iniciarJuego(List<String> nombres) {
        Temporizador temporizadorJugador1 = new Temporizador(10);  // Temporizador de 10 minutos para el jugador 1
        Temporizador temporizadorJugador2 = new Temporizador(10);  // Temporizador de 10 minutos para el jugador 2

        // Inicializa los jugadores según el color seleccionado
        Jugador jugador1;
        Jugador jugador2;

        if (this.colorJugador1 == Color.NEGRO) {
            jugador1 = new Jugador(nombres.get(0), this.colorJugador1, temporizadorJugador1);
            jugador2 = new Jugador(nombres.get(1), this.colorJugador2, temporizadorJugador2);
        } else {
            jugador2 = new Jugador(nombres.get(0), this.colorJugador1, temporizadorJugador2);
            jugador1 = new Jugador(nombres.get(1), this.colorJugador2, temporizadorJugador1);
        }

        // Crea una lista de jugadores y los añade
        List<Jugador> jugadores = new ArrayList<>();
        if (this.colorJugador1 == Color.BLANCO) {
            jugadores.add(jugador2);  // El jugador que usa las piezas Blancas va primero
            jugadores.add(jugador1);
        } else {
            jugadores.add(jugador1);  // El jugador que usa las piezas Negras va primero
            jugadores.add(jugador2);
        }

        // Inicializa el juego con los jugadores
        this.juego = new Juego(jugadores);
    }

    /**
     * Inicializa la selección de colores de las piezas entre Blancas y Negras.
     * Asocia los RadioButtons al grupo y define el comportamiento cuando se selecciona un color.
     */
    public void InicializarEleccionColor() {
        // Inicializa el grupo de selección de piezas
        this.grupoPiezas = new ToggleGroup();
        this.btnBlancas.setToggleGroup(this.grupoPiezas);
        this.btnNegras.setToggleGroup(this.grupoPiezas);

        // Listener para cambiar los colores de los jugadores según la selección
        grupoPiezas.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (btnBlancas.isSelected()) {
                this.colorJugador1 = Color.BLANCO;
                this.colorJugador2 = Color.NEGRO;
            } else if (btnNegras.isSelected()) {
                this.colorJugador1 = Color.NEGRO;
                this.colorJugador2 = Color.BLANCO;
            }
        });
    }
}
