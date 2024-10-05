package org.ajedrez.model;

public class AdministradorDeTurnos {

    private int turnos = 0;

    public AdministradorDeTurnos(){
        this.turnos = 0;
    }

    public void siguienteTurno(){
        this.turnos += 1;
    }

    public boolean validarTurno(Color color){
        /*Si el movimiento viene del jugador de fichas blancas y el numero es par es valido
        Si el movimiento viene del jugador de fichas negras y el numero es impar es valido
        De lo contrario, es invalido*/

        if(((this.turnos % 2 == 0) && (color == Color.BLANCO)) || ((this.turnos % 2 != 0) && (color == Color.NEGRO))){
            return true;
        }
        return false;
    }
}
