package org.ajedrez.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import org.ajedrez.model.*;
import org.ajedrez.model.Efectos.Freeze;
import org.ajedrez.model.Efectos.Protect;
import org.ajedrez.model.Efectos.Volar;
import org.ajedrez.view.PiezaView;
import org.ajedrez.view.TableroView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.ajedrez.controller.EfectoController.descontarEfectos;

/**
 * Clase controladora que gestiona las interacciones del tablero de ajedrez.
 * Se encarga de inicializar el tablero y las piezas, y maneja los eventos del mouse
 * para el movimiento de las piezas en la interfaz gráfica.
 */
public class TableroController {

    private Juego juego;
    private Boolean coronacion = false;
    private TableroView tableroView;
    private Boolean seAplicoEfecto = false;
    private MovimientosEspecialesController movimientosEspeciales;
    private EfectoController efectoController;// Vista del tablero
    private TemporizadorController temporizadorController;
    private List<int[]> casillasResaltadas = new ArrayList<>();
    private ImageView imagenPiezaSeleccionada;  // Imagen de la pieza actualmente seleccionada
    private double desplazamientoX;  // Desplazamiento del mouse en el eje X al presionar la pieza
    private double desplazamientoY;  // Desplazamiento del mouse en el eje Y al presionar la pieza
    private Boolean protection = false;
    private Boolean freezar = false;
    private Boolean volar = false;


    @FXML
    private GridPane panelCuadriculado;  // Panel que contiene el tablero de ajedrez
    @FXML
    private GridPane piezasEliminadasNegras; // Panel de piezas Negras Eliminadas
    private PiezasEliminadasController piezasEliminadasController;

    @FXML
    private GridPane piezasEliminadasBlancas; // Panel de piezas Blancas Eliminadas/

    @FXML
    private Label jugadorBlancas;
    @FXML
    private Label jugadorNegras;
    @FXML
    private Label jugadorTurnoActual;
    @FXML
    private Label tiempoJugadorBlancas;
    @FXML
    private Label tiempoJugadorNegras;
    @FXML
    private Button congeladorJugadorNegras;
    @FXML
    private Button congeladorJugadorBlancas;
    @FXML
    private Button proteccionJugadorBlancas;
    @FXML
    private Button proteccionJugadorNegras;
    @FXML
    private Button volarJugadorBlancas;
    @FXML
    private Button volarJugadorNegras;


    /**
     * Inicializa el controlador. Se llama automáticamente al cargar el FXML.
     * Crea una nueva instancia de Tablero y llama a los métodos para inicializar el tablero y las piezas.
     */
    @FXML
    public void initialize() {

        InicializarTablero();
        this.movimientosEspeciales = new MovimientosEspecialesController(this);
        this.piezasEliminadasController = new PiezasEliminadasController(this.piezasEliminadasBlancas,this.piezasEliminadasNegras);
        this.efectoController = new EfectoController();
    }

    public void InicializarTablero() {
        this.tableroView = new TableroView(this.panelCuadriculado);
    }

    @FXML
    public void cargarTablero() {
        Tablero tablero = juego.getTablero();
        tableroView.setTablero(tablero);
        tableroView.inicializarPiezas(this);
        this.jugadorTurnoActual.setText(juego.getJugadorTurnoActual().getNombre());
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    /**
     * Actualiza las etiquetas que muestran los nombres de los jugadores y sus temporizadores en la interfaz gráfica.
     * Este método obtiene la información de los jugadores desde el objeto de juego actual y actualiza las etiquetas
     * correspondientes en la vista del tablero.
     */
    public void setLabels() {
        Jugador jugadorBlancas = this.juego.getJugadorSegunColor(Color.BLANCO); // Obtiene el jugador de las piezas blancas.
        Jugador jugadorNegras = this.juego.getJugadorSegunColor(Color.NEGRO); // Obtiene el jugador de las piezas negras.

        // Actualiza la etiqueta del jugador blanco con su nombre y el temporizador correspondiente.
        this.jugadorBlancas.setText(jugadorBlancas.getNombre());
        this.jugadorNegras.setText(jugadorNegras.getNombre());

        this.temporizadorController = new TemporizadorController(jugadorBlancas.getTemporizador(),jugadorNegras.getTemporizador(),tiempoJugadorBlancas,tiempoJugadorNegras);
    }

    public Tablero getTablero() {
        return juego.getTablero();
    }

    public TableroView getTableroView() {
        return this.tableroView;
    }

    public PiezasEliminadasController getPiezasEliminadasController(){
        return this.piezasEliminadasController;
    }

    @FXML
    public void rendirse(ActionEvent event) throws Exception {
        VentanaController ventanaController = new VentanaController();
        String mensaje = "  WIN NEGRAS  ";
        if(juego.getColorJugadorTurnoActual() == Color.NEGRO ){
            mensaje = "  WIN BLANCAS  ";
        }
        ventanaController.reutilizarVentanaInicial(event, mensaje, jugadorBlancas);
    }

    @FXML
    public void tablas(ActionEvent event) throws Exception {
        VentanaController ventanaController = new VentanaController();
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText("¿Estás seguro?");
        alerta.setContentText("¿Deseas aceptar las tablas?");
        // Mostrar la alerta y esperar por la respuesta del usuario
        Optional<ButtonType> resultado = alerta.showAndWait();

        // Verificar la respuesta del usuario
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            String mensaje = "EMPATE";
            ventanaController.reutilizarVentanaInicial(event, mensaje, jugadorBlancas);
        } else {
            System.out.println("El usuario ha cancelado la acción.");
        }
    }

    public void verificarMovimientoEspecial(int filaOriginal, int columnaOriginal,int filaDestino, int columnaDestino) throws Exception {
        Optional<Pieza> piezaOptOrigen = getTablero().getPieza(filaOriginal, columnaOriginal);
        this.coronacion = movimientosEspeciales.chequearMovimientoEspecial(filaOriginal, columnaOriginal, filaDestino, columnaDestino);
        if(piezaOptOrigen.get().getTipo() == TipoPieza.TORRE){
            Torre pieza = (Torre) piezaOptOrigen.get();
            pieza.marcarComoMovido();
        }
    }


    public void congelarRival(Color colorJugador) {
        if (juego.getColorJugadorTurnoActual() == colorJugador) {
            efectoController.setEfectoElejido(new Freeze());
            this.freezar = true;
        }
    }

    @FXML
    public void protegerPropias(Color colorJugador) {
        if (juego.getColorJugadorTurnoActual() == colorJugador) {
            efectoController.setEfectoElejido(new Protect());
            this.protection = true;
        }
    }

    public void permitirVuelo(Color colorJugador) {
        if (juego.getColorJugadorTurnoActual() == colorJugador) {
            efectoController.setEfectoElejido(new Volar());
            this.volar = true; // Marca que el efecto de volar está activo
        }
    }

    @FXML
    public void chequearColorVolar(ActionEvent event) throws Exception {
        Button boton = (Button) event.getSource();
        Color colorJugador;

        if (boton.getId().equals("volarJugadorNegras")) {
            colorJugador = Color.NEGRO;
        } else {
            colorJugador = Color.BLANCO;
        }
        permitirVuelo(colorJugador);
    }


    @FXML
    public void chequearColorCongelar(ActionEvent event) throws Exception {
        Button boton = (Button) event.getSource();
        Color colorJugador;

        if (boton.getId().equals("congeladorJugadorNegras")) {
            colorJugador = Color.NEGRO;
        } else {
            colorJugador = Color.BLANCO;
        }
        congelarRival(colorJugador);

    }

    @FXML
    public void chequearColorProteger(ActionEvent event) throws Exception {
        Button boton = (Button) event.getSource();
        Color colorJugador;

        if (boton.getId().equals("proteccionJugadorNegras")) {
            colorJugador = Color.NEGRO;
        } else {
            colorJugador = Color.BLANCO;
        }
        protegerPropias(colorJugador);

    }

    /**
     * Maneja el evento de presionar el mouse sobre una pieza.
     *
     * @param eventoMouse El evento de mouse que contiene información sobre la acción.
     * @param vistaImagen La imagen de la pieza que fue presionada.
     */

    public void alPresionarConMouse(MouseEvent eventoMouse, ImageView vistaImagen) {
        // Obtiene la fila y columna originales de la pieza seleccionada
        int filaOriginal = GridPane.getRowIndex(vistaImagen);
        int columnaOriginal = GridPane.getColumnIndex(vistaImagen);

        Pieza piezaSeleccionada = juego.getTablero().getPieza(filaOriginal, columnaOriginal).get();

        if(!seAplicoEfecto && !piezaSeleccionada.isefectoactivo()){
            aplicarEfecto(piezaSeleccionada,vistaImagen);
        }

        if (piezaSeleccionada.getColor() != juego.getColorJugadorTurnoActual()){
            return;
        }

        imagenPiezaSeleccionada = vistaImagen;
        resaltarCasillas(piezaSeleccionada, filaOriginal, columnaOriginal, true);
        desplazamientoX = eventoMouse.getSceneX() - vistaImagen.getTranslateX();
        desplazamientoY = eventoMouse.getSceneY() - vistaImagen.getTranslateY();
        vistaImagen.setMouseTransparent(true);  // Evita que se reciban eventos de mouse mientras se arrastra
        vistaImagen.toFront();
    }

    /**
     * Maneja el evento de arrastrar una pieza con el mouse.
     *
     * @param eventoMouse El evento de mouse que contiene información sobre la acción.
     * @param vistaImagen La imagen de la pieza que se está arrastrando.
     */

    public void alArrastrarConMouse(MouseEvent eventoMouse, ImageView vistaImagen) {
        if (imagenPiezaSeleccionada != null) {
            vistaImagen.toFront();
            vistaImagen.setTranslateX(eventoMouse.getSceneX() - desplazamientoX);
            vistaImagen.setTranslateY(eventoMouse.getSceneY() - desplazamientoY);
        }
    }

    /**
     * Maneja el evento de soltar una pieza con el mouse.
     *
     * @param vistaImagen     La imagen de la pieza que se está soltando.
     * @param filaOriginal    La fila original de la pieza.
     * @param columnaOriginal La columna original de la pieza.
     */

    public void alSoltarMouse(ImageView vistaImagen, int filaOriginal, int columnaOriginal) throws Exception {
        vistaImagen.setMouseTransparent(false);
        // Calcula la nueva fila y columna según las coordenadas del mouse
        int nuevaColumna = (int) Math.round((vistaImagen.getLayoutX() + vistaImagen.getTranslateX()) / 90);
        int nuevaFila = (int) Math.round((vistaImagen.getLayoutY() + vistaImagen.getTranslateY()) / 90);

        // Validar que la posición esté dentro de los límites del tablero
        if (!this.juego.movimientoInvalido(filaOriginal, columnaOriginal, nuevaFila, nuevaColumna)) {
            verificarMovimientoEspecial( filaOriginal, columnaOriginal, nuevaFila, nuevaColumna);

            // Realizar el movimiento en el modelo
            getTablero().moverPieza(filaOriginal, columnaOriginal, nuevaFila, nuevaColumna);
            // Realizar el movimiento en la vista
            tableroView.moverPieza(vistaImagen, filaOriginal, columnaOriginal, nuevaFila, nuevaColumna);

            // Obtén el color del jugador contrario
            Color color = getTablero().getPieza(nuevaFila, nuevaColumna).get().getColor();
            Color colorEnemigo = (color == Color.BLANCO) ? Color.NEGRO : Color.BLANCO;

            // Verificar si es jaque mate después de realizar el movimiento
            if (esJaqueMate(getTablero(), colorEnemigo)) {
                terminarJacqueMate(null, juego.getColorJugadorTurnoActual());
            }
            resetearSoltarMouse(vistaImagen, nuevaFila, nuevaColumna);

            if (!coronacion) {
                cambiarTurnos();
            }
        }

        vistaImagen.setTranslateX(0);
        vistaImagen.setTranslateY(0);
        desresaltarCasillas(filaOriginal, columnaOriginal);
        imagenPiezaSeleccionada = null;
    }

    public void terminarJacqueMate(ActionEvent event, Color color) throws Exception {


        String mensaje = (color == Color.BLANCO) ? "  WIN BLANCAS  " : "  WIN NEGRAS  ";

        VentanaController ventanaController = new VentanaController();
        ventanaController.reutilizarVentanaInicial(event, mensaje, jugadorBlancas);
    }



    public void setearEventos(PiezaView piezaView, int filaFinal, int columnaFinal) {
        // Agregar manejadores de eventos del mouse
        ImageView vistaImagen = piezaView.getVistaPieza();
        vistaImagen.setOnMousePressed(eventoMouse -> alPresionarConMouse(eventoMouse, vistaImagen));
        vistaImagen.setOnMouseDragged(eventoMouse -> alArrastrarConMouse(eventoMouse, vistaImagen));
        vistaImagen.setOnMouseReleased(eventoMouse -> {
            try {
                alSoltarMouse(vistaImagen, filaFinal, columnaFinal);
            } catch (Exception e) {
                // Manejar la excepción localmente
                System.err.println("Error al soltar el mouse: " + e.getMessage());
                // Puedes mostrar un mensaje al usuario o registrar el error
            }
        });
    }

    public void cambiarTurnos() {
        Jugador jugadorTurnoActual = juego.getJugadorTurnoActual();
        temporizadorController.parar(jugadorTurnoActual);

        if (jugadorTurnoActual.getTemporizador().TiempoRestante() <= 0) {
            VentanaController ventanaController = new VentanaController();
            String mensaje = " GANAN ";
            // Si el tiempo del jugador actual llegó a 0, gana el oponente
            if (jugadorTurnoActual.getColor() == Color.BLANCO) {
                mensaje += "NEGRAS";
            } else {
                mensaje += "BLANCAS";
            }
            try {
                // Mostrar mensaje de victoria por tiempo
                ventanaController.reutilizarVentanaInicial(null, mensaje, jugadorBlancas);
            } catch (Exception e) {
                e.printStackTrace(); // Manejo de excepción si hay un problema al cambiar la ventana
            }
            return;
        }

        seAplicoEfecto = false;
        juego.siguienteTurno();
        descontarEfectos(this.juego,this.tableroView);

        jugadorTurnoActual = juego.getJugadorTurnoActual();
        temporizadorController.iniciar(jugadorTurnoActual);
        this.jugadorTurnoActual.setText(jugadorTurnoActual.getNombre());
    }


    private void resaltarCasillas(Pieza piezaSeleccionada, int filaOriginal, int columnaOriginal, boolean resaltar) {
        List<int[]> posicionesValidas = obtenerPosicionesValidas(piezaSeleccionada, filaOriginal, columnaOriginal);
        tableroView.resaltarCasilla(filaOriginal, columnaOriginal, true);//Casilla seleccionada
        // Resalta las casillas válidas a las que se puede mover
        for (int[] posicion : posicionesValidas) {
            int filaDestino = posicion[0];
            int columnaDestino = posicion[1];
            tableroView.resaltarCasilla(filaDestino, columnaDestino, true);
            casillasResaltadas.add(new int[]{filaDestino, columnaDestino});
        }
    }

    // Método para desresaltar todas las casillas previamente resaltadas
    private void desresaltarCasillas(int filaOriginal, int columnaOriginal) {
        tableroView.resaltarCasilla(filaOriginal, columnaOriginal, false);
        for (int[] posicion : casillasResaltadas) {
            int fila = posicion[0];
            int columna = posicion[1];
            tableroView.resaltarCasilla(fila, columna, false);  // Desactiva el resaltado
        }
        // Limpiar la lista de casillas resaltadas
        casillasResaltadas.clear();
    }

    private List<int[]> obtenerPosicionesValidas(Pieza pieza, int filaOriginal, int columnaOriginal) {
        List<int[]> posicionesValidas = new ArrayList<>();

        // Lógica para obtener las posiciones válidas, dependiendo de la pieza seleccionada
        // Supongamos que cada Pieza tiene un método validarMovimiento(filaDestino, columnaDestino)
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                if (pieza.validarMovimiento(getTablero(), filaOriginal, columnaOriginal, fila, columna)) {
                    posicionesValidas.add(new int[]{fila, columna});  // Añade la posición válida
                }
            }
        }

        return posicionesValidas;
    }

    public void resetearSoltarMouse(ImageView vistaImagen, int nuevaFila, int nuevaColumna) {
        vistaImagen.setOnMouseReleased(eventoMouse -> {
            try {
                alSoltarMouse(vistaImagen, nuevaFila, nuevaColumna);
            } catch (Exception e) {
                e.printStackTrace();
            }// Manejar el error (puedes mostrar una alerta o loguear el error)
        });
    }

    private void aplicarEfecto(Pieza pieza,ImageView vistaImagen){
        if(freezar){
            if((pieza.getColor() != juego.getColorJugadorTurnoActual()) && pieza.getTipo() != TipoPieza.REY){
                efectoController.aplicarEfecto(vistaImagen,pieza);
                seAplicoEfecto = true;

                desactivarBotones(pieza);
            }
            freezar = false;

        } else if(protection || volar){
            if((pieza.getColor() == juego.getColorJugadorTurnoActual()) && pieza.getTipo() != TipoPieza.REY){
                efectoController.aplicarEfecto(vistaImagen,pieza);
                seAplicoEfecto = true;

                if(protection){
                    desactivarBotones(pieza,proteccionJugadorBlancas,proteccionJugadorNegras);
                }

                if(volar){
                    desactivarBotones(pieza,volarJugadorBlancas,volarJugadorNegras);
                }
            }
            protection = false;
            volar = false;

        }
    }

    private void desactivarBotones(Pieza pieza,Button botonBlancas, Button botonNegras) {
        if(pieza.getColor() == Color.BLANCO){
            botonBlancas.setDisable(true);
        } else {
            botonNegras.setDisable(true);
        }
    }
    private void desactivarBotones(Pieza pieza){
        if(pieza.getColor() == Color.BLANCO){
            congeladorJugadorNegras.setDisable(true);
        } else {
            congeladorJugadorBlancas.setDisable(true);
        }
    }

    public boolean esJaqueMate(Tablero tablero, Color color) {
        // Verifica si el rey del color especificado está en jaque
        if (!AdministradorDeMovimientos.hayJaque(tablero, color)) {
            return false; // No está en jaque, por lo tanto no es jaque mate
        }

        // Crea una copia de las piezas del tablero para evitar modificaciones concurrentes
        List<Pieza> piezasJugador = new ArrayList<>(tablero.getPiezas());

        // Recorre todas las piezas del color que está en jaque
        for (Pieza pieza : piezasJugador) {
            if (pieza.getColor() == color) {
                int filaOriginal = pieza.getFila();
                int columnaOriginal = pieza.getColumna();

                // Obtiene todos los movimientos válidos de la pieza
                List<int[]> movimientosPosibles = obtenerPosicionesValidas(pieza, filaOriginal, columnaOriginal);
                for (int[] movimiento : movimientosPosibles) {
                    int filaDestino = movimiento[0];
                    int columnaDestino = movimiento[1];

                    // Simula el movimiento temporalmente
                    Optional<Pieza> piezaDestinoOpt = tablero.getPieza(filaDestino, columnaDestino);
                    tablero.eliminarPieza(filaDestino, columnaDestino);
                    tablero.moverPieza(filaOriginal, columnaOriginal, filaDestino, columnaDestino);

                    // Verifica si el movimiento elimina el jaque
                    boolean sigueEnJaque = AdministradorDeMovimientos.hayJaque(tablero, color);

                    // Revierte el movimiento
                    tablero.moverPieza(filaDestino, columnaDestino, filaOriginal, columnaOriginal);
                    piezaDestinoOpt.ifPresent(p -> tablero.agregarPieza(p, filaDestino, columnaDestino));

                    // Si algún movimiento posible elimina el jaque, no es jaque mate
                    if (!sigueEnJaque) {
                        return false;
                    }
                }
            }
        }
        // Si todos los movimientos posibles siguen en jaque, es jaque mate
        return true;
    }

}