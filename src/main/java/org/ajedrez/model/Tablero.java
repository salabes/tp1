package org.ajedrez.model;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Clase que representa un tablero de ajedrez.
 * Contiene una matriz de casilleros y una lista de piezas.
 */
public class Tablero {
    private Casillero[][] casilleros;  // Matriz que representa los casilleros del tablero
    private ArrayList<Pieza> piezas;     // Lista que contiene las piezas en el tablero
    private int cantPiezas;              // Cantidad total de piezas en el tablero

    /**
     * Constructor de la clase Tablero.
     * Inicializa los casilleros y las piezas del tablero.
     */
    public Tablero() {
        piezas = new ArrayList<Pieza>();
        casilleros = new Casillero[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                casilleros[i][j] = new Casillero(); // Crea un nuevo casillero
            }
        }
        inicializarTablero(); // Llama al método para inicializar el tablero con piezas
    }

    /**
     * Método privado que inicializa el tablero con las piezas en sus posiciones iniciales.
     */
    private void inicializarTablero() {
        // Inicializa las piezas negras
        casilleros[0][0].setPieza(new Torre(Color.NEGRO, 0, 0));
        this.piezas.add(casilleros[0][0].getPieza());
        casilleros[0][1].setPieza(new Caballo(Color.NEGRO, 0, 1));
        this.piezas.add(casilleros[0][1].getPieza());
        casilleros[0][2].setPieza(new Alfil(Color.NEGRO, 0, 2));
        this.piezas.add(casilleros[0][2].getPieza());
        casilleros[0][3].setPieza(new Reina(Color.NEGRO, 0, 3));
        this.piezas.add(casilleros[0][3].getPieza());
        casilleros[0][4].setPieza(new Rey(Color.NEGRO, 0, 4));
        this.piezas.add(casilleros[0][4].getPieza());
        casilleros[0][5].setPieza(new Alfil(Color.NEGRO, 0, 5));
        this.piezas.add(casilleros[0][5].getPieza());
        casilleros[0][6].setPieza(new Caballo(Color.NEGRO, 0, 6));
        this.piezas.add(casilleros[0][6].getPieza());
        casilleros[0][7].setPieza(new Torre(Color.NEGRO, 0, 7));
        this.piezas.add(casilleros[0][7].getPieza());

        // Inicializa los peones negros
        for (int i = 0; i < 8; i++) {
            casilleros[1][i].setPieza(new Peon(Color.NEGRO, 1, i));
            this.piezas.add(casilleros[1][i].getPieza());
        }

        // Inicializa los peones blancos
        for (int i = 0; i < 8; i++) {
            casilleros[6][i].setPieza(new Peon(Color.BLANCO, 6, i));
            this.piezas.add(casilleros[6][i].getPieza());
        }

        // Inicializa las piezas blancas
        casilleros[7][0].setPieza(new Torre(Color.BLANCO, 7, 0));
        this.piezas.add(casilleros[7][0].getPieza());
        casilleros[7][1].setPieza(new Caballo(Color.BLANCO, 7, 1));
        this.piezas.add(casilleros[7][1].getPieza());
        casilleros[7][2].setPieza(new Alfil(Color.BLANCO, 7, 2));
        this.piezas.add(casilleros[7][2].getPieza());
        casilleros[7][3].setPieza(new Reina(Color.BLANCO, 7, 3));
        this.piezas.add(casilleros[7][3].getPieza());
        casilleros[7][4].setPieza(new Rey(Color.BLANCO, 7, 4));
        this.piezas.add(casilleros[7][4].getPieza());
        casilleros[7][5].setPieza(new Alfil(Color.BLANCO, 7, 5));
        this.piezas.add(casilleros[7][5].getPieza());
        casilleros[7][6].setPieza(new Caballo(Color.BLANCO, 7, 6));
        this.piezas.add(casilleros[7][6].getPieza());
        casilleros[7][7].setPieza(new Torre(Color.BLANCO, 7, 7));
        this.piezas.add(casilleros[7][7].getPieza());

        this.cantPiezas = 32; // Establece la cantidad total de piezas en el tablero
    }

    /**
     * Método que obtiene una pieza en una posición específica del tablero.
     *
     * @param fila    Fila de la posición deseada (0-7)
     * @param columna Columna de la posición deseada (0-7)
     * @return Un objeto Optional que contiene la pieza si existe, o vacío si no.
     */
    public Optional<Pieza> getPieza(int fila, int columna) {
        if (fila < 0 || fila > 7 || columna < 0 || columna > 7) {
            return Optional.empty(); // Devuelve vacío si la posición está fuera de límites
        }
        return Optional.ofNullable(casilleros[fila][columna].getPieza());
    }

    /**
     * Método que mueve una pieza de una posición original a una nueva posición.
     *
     * @param filaOriginal   Fila de la posición originalA
     * @param columnaOriginal Columna de la posición original
     * @param filaNueva      Fila de la nueva posición
     * @param columnaNueva   Columna de la nueva posición
     */
    public void moverPieza(int filaOriginal, int columnaOriginal, int filaNueva, int columnaNueva) {
        Pieza piezaAMover = casilleros[filaOriginal][columnaOriginal].getPieza();
        casilleros[filaNueva][columnaNueva].setPieza(casilleros[filaOriginal][columnaOriginal].getPieza());
        casilleros[filaOriginal][columnaOriginal].setPieza(null); // Elimina la pieza de la posición original
        piezaAMover.setColumna(columnaNueva);
        piezaAMover.setFila(filaNueva);
    }

    /**
     * Método que verifica si un casillero está ocupado por una pieza.
     *
     * @param filaDestino   Fila del casillero a verificar
     * @param columnaDestino Columna del casillero a verificar
     * @return true si el casillero está ocupado, false si está vacío.
     */
    public boolean estaOcupada(int filaDestino, int columnaDestino) {
        return getPieza(filaDestino, columnaDestino).isPresent(); // Comprueba si hay una pieza en el casillero
    }

    /**
     * Método que agrega una nueva pieza en una posición específica del tablero.
     *
     * @param pieza   La pieza a agregar
     * @param fila    Fila de la posición donde se agregará la pieza
     * @param columna Columna de la posición donde se agregará la pieza
     */
    public void agregarPieza(Pieza pieza, int fila, int columna) {
        casilleros[fila][columna].setPieza(pieza); // Establece la pieza en el casillero
        pieza.setColumna(columna);
        pieza.setFila(fila);
        piezas.add(pieza); // Agrega la pieza a la lista de piezas
        this.cantPiezas++; // Incrementa la cantidad de piezas
    }

    /**
     * Método que reemplaza una pieza existente en una posición específica del tablero.
     *
     * @param piezaNueva La nueva pieza que reemplazará a la existente
     * @param fila       Fila de la posición de la pieza a reemplazar
     * @param columna    Columna de la posición de la pieza a reemplazar
     */
    public void reemplazarPieza(Pieza piezaNueva, int fila, int columna) {
        eliminarPieza(fila, columna); // Elimina la pieza existente
        agregarPieza(piezaNueva, fila, columna); // Agrega la nueva pieza
    }

    /**
     * Método que elimina una pieza de una posición específica del tablero.
     *
     * @param fila    Fila de la posición de la pieza a eliminar
     * @param columna Columna de la posición de la pieza a eliminar
     */
    public void eliminarPieza(int fila, int columna) {
        this.casilleros[fila][columna].setPieza(null); // Elimina la pieza del casillero
        piezas.removeIf(pieza -> pieza.getFila() == fila && pieza.getColumna() == columna); // Elimina la pieza de la lista
        this.cantPiezas--; // Decrementa la cantidad de piezas
    }

    /**
     * Método que obtiene la cantidad total de piezas en el tablero.
     *
     * @return Cantidad total de piezas en el tablero.
     */
    public int getCantPiezas() {
        return cantPiezas; // Devuelve la cantidad total de piezas
    }

    /**
     * Método que obtiene la lista de piezas en el tablero.
     *
     * @return Un ArrayList que contiene todas las piezas del tablero.
     */
    public ArrayList<Pieza> getPiezas() {
        return piezas; // Devuelve la lista de piezas
    }


}

