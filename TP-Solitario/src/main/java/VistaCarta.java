import Solitario.Carta;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class VistaCarta {
    protected static void inicializarCartas(HBox HBoxInferior, ArrayList<ArrayList<Carta>> cartas) {
        for (int i = 0; i < cartas.size(); i++) {
            var columna = (Pane) HBoxInferior.getChildren().get(i);
            var columnaCartas = cartas.get(i);

            var paneBase = AppMain.crearPane();
            paneBase.getChildren().add(new Canvas(AppMain.ANCHO_PANE, AppMain.ALTO_PANE));
            columna.getChildren().add(paneBase);

            for (int j = 0; j < columnaCartas.size(); j++) {
                var nuevoPane = new Pane();
                nuevoPane.setLayoutX(AppMain.LAYOUT_X_CARTAS);
                nuevoPane.setLayoutY(AppMain.ESPACIADO_CARTAS * (j + 1));

                Canvas nuevoCanvas = new Canvas(AppMain.ANCHO_PANE, AppMain.ALTO_PANE);
                dibujarCarta(nuevoCanvas, columnaCartas.get(j));
                nuevoPane.getChildren().add(nuevoCanvas);

                if (columnaCartas.get(j).estaBocaAbajo()) {
                    Canvas canvasDorso = new Canvas(AppMain.ANCHO_PANE, AppMain.ALTO_PANE);
                    dibujarCartaTrasera(canvasDorso);
                    nuevoPane.getChildren().add(canvasDorso);
                }

                columna.getChildren().add(nuevoPane);
            }
        }
    }

    protected static void dibujarCarta(Canvas canvas, Carta carta) {
        var gc = canvas.getGraphicsContext2D();

        gc.setFill(javafx.scene.paint.Color.WHITE);
        gc.setStroke(javafx.scene.paint.Color.BLACK);
        gc.setLineWidth(1);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

        var palo = switch (carta.getPalo()) {
            case DIAMANTES:
            case CORAZONES:
                gc.setFill(javafx.scene.paint.Color.RED);
                yield carta.getPalo().getSimbolo();
            default:
                gc.setFill(javafx.scene.paint.Color.BLACK);
                yield carta.getPalo().getSimbolo();
        };

        String num = switch (String.valueOf(carta.getNumero())) {
            case "1" -> "A";
            case "11" -> "J";
            case "12" -> "Q";
            case "13" -> "K";
            default -> String.valueOf(carta.getNumero());
        };

        gc.fillText(num, 5, 15);
        gc.fillText(palo, 20, 15);
    }

    protected static void dibujarCartaTrasera(Canvas canvas) {
        var gc = canvas.getGraphicsContext2D();

        // Fondo
        gc.setFill(javafx.scene.paint.Color.RED);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // Borde
        gc.setStroke(javafx.scene.paint.Color.WHITE);
        gc.setLineWidth(2);
        gc.strokeRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // Diagionales
        gc.setLineWidth(1);
        for (int i = 1; i <= 12; i++) {
            var y = i * (gc.getCanvas().getHeight() / 7);       // Espacio entre lineas
            var x = 0.7 * y;                                    // Inclinacion
            gc.strokeLine(1, y, x, 1);
        }
    }
}
