import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class UtilidadGUI {

    protected static void moverATableau(Pane paneOrigen, Pane paneDestino) {
        var colOrigen = (Pane) paneOrigen.getParent();
        var indexColOrigen = colOrigen.getChildren().indexOf(paneOrigen);
        var colDestino = (Pane) paneDestino.getParent();
        var indexColDestino = colDestino.getChildren().size() - 1;
        var posUltimoNodo = colDestino.getChildren().get(indexColDestino).getLayoutY();

        moverElementos(colOrigen, colDestino, indexColOrigen, posUltimoNodo);
    }

    protected static void moverElementos(Pane paneOrigen, Pane paneDestino, int desde, double posUltimoNodo) {
        int i = 0;
        if (paneDestino.getChildren().size() == 1) {
            i = -1;
        }

        while (desde < paneOrigen.getChildren().size()) {
            var pane = (Pane) paneOrigen.getChildren().remove(desde);
            paneDestino.getChildren().add(pane);
            pane.setLayoutX(AppMain.LAYOUT_X_CARTAS);
            pane.setLayoutY(posUltimoNodo + AppMain.ESPACIADO_CARTAS * (i + 1));
            i++;
        }

        var nuevoTope = (Pane) paneOrigen.getChildren().get(desde-1);
        if (nuevoTope.getChildren().size() > 1) {
            var canvasFrente = (Canvas) nuevoTope.getChildren().get(0);
            var canvasDorso = (Canvas) nuevoTope.getChildren().get(1);
            canvasDorso.setVisible(false);
            canvasFrente.setVisible(true);
        }
    }
}
