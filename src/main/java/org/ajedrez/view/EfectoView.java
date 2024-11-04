package org.ajedrez.view;
import javafx.scene.image.ImageView;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import org.ajedrez.model.Efectos.Efecto;
import org.ajedrez.model.Efectos.TipoEfecto;

public class EfectoView {
    public void aplicarEfectoView(Efecto efecto, ImageView vista) {
        if (efecto.isEfectoActivo()) {
            switch (efecto.getTipo()) {
                case TipoEfecto.VOLAR:
                    aplicarEfectoViewVolar(vista);
                    break;
                case TipoEfecto.FREEZE:
                    aplicarEfectoViewFreeze(vista);
                    break;
                case TipoEfecto.PROTECT:
                    aplicarEfectoProtect(vista);
                    break;
                // Agregar más casos según otros tipos de efecto
            }
        }
    }
    public void aplicarEfectoProtect(ImageView vista) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.YELLOW);
        dropShadow.setRadius(25);
        vista.setEffect(dropShadow);
    }
    public void aplicarEfectoViewVolar(ImageView vista) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLUE);
        dropShadow.setRadius(25);
        vista.setEffect(dropShadow);
    }
    public void aplicarEfectoViewFreeze(ImageView vista) {
        vista.setOpacity(0.5);
    }
}
