import Spider.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.fxml.FXMLLoader;
import Solitario.*;
import java.util.*;

public class AplicacionSpider extends AppMain {
    // Constantes
    private static final int CANT_COLUMNAS = 10;
    private static final int CANT_CARTAS_BASE = 13;
    private static final int INICIO_BASE = 2;
    private static final String RUTA_FXML = "spider.fxml";
    private final AppMain padre;
    private static SolitarioSpider juego;

    public AplicacionSpider(SolitarioSpider nuevoJuego, AppMain appMain) {
        juego = nuevoJuego;
        this.padre = appMain;
    }

    @Override
    public void start(Stage stage) throws Exception {
        var loaderSpider = new FXMLLoader(getClass().getResource(RUTA_FXML));
        loaderSpider.setController(this);
        loaderSpider.load();
        inicializarEscena(stage, "Spider");

        var tableau = juego.verCartasTableau();
        var mazo = juego.verCartasMazo();
        inicializarMazo(mazo);
        inicializarBase(stage, juego, INICIO_BASE, Juego.SPIDER);
        inicializarTableau(tableau);
        primerClic = true;

        var nuevoJuegoBtn = menu.getMenus().get(0).getItems().get(0);
        nuevoJuegoBtn.setOnAction(actionEvent -> padre.pedirOpciones(stage));
    }

    private void inicializarTableau(ArrayList<ArrayList<Carta>> cartas) {
        HBox HBoxInferior = (HBox) ventana.getChildren().get(HBOX_INF);
        VistaCarta.inicializarCartas(HBoxInferior, cartas);

        for (int i = 0; i < cartas.size(); i++) {
            var columna = (Pane) HBoxInferior.getChildren().get(i);

            var paneBase = (Pane) columna.getChildren().get(0);
            paneBase.setOnMouseClicked(event -> {
                if (!primerClic) {
                    accionSegundoClic(anteriorCliqueado, paneBase);
                }
            });

            for (var pane : columna.getChildren()) {
                var nuevoPane = (Pane) pane;
                nuevoPane.setOnMouseClicked(event -> {
                    if (primerClic) {
                        accionPrimerClic(nuevoPane);
                    } else {
                        accionSegundoClic(anteriorCliqueado, nuevoPane);
                    }
                });
            }
        }
    }

    private void inicializarMazo(Deque<Carta> mazoCartas) {
        HBox HBoxSup = (HBox) ventana.getChildren().get(HBOX_SUP);
        StackPane mazo = (StackPane) HBoxSup.getChildren().get(0);
        HBox HBoxInf = (HBox) ventana.getChildren().get(HBOX_INF);

        Pane paneBase = crearPane();
        mazo.getChildren().add(paneBase);

        for (int i = 0; i < mazoCartas.size(); i++) {                              //cambia el tope, en Klondike es 24
            Pane nuevoPane = new Pane();
            Canvas nuevoCanvas = new Canvas(ANCHO_PANE, ALTO_PANE);
            VistaCarta.dibujarCartaTrasera(nuevoCanvas);
            nuevoPane.setMaxHeight(ALTO_PANE);
            nuevoPane.setMaxWidth(ANCHO_PANE);

            nuevoPane.setOnMouseClicked(event -> {
                if (juego.pedirCarta()) {
                    moverMazoATableau(mazo, HBoxInf, mazoCartas);
                }
            });

            nuevoPane.getChildren().add(nuevoCanvas);
            mazo.getChildren().add(nuevoPane);
        }
    }

    private void moverMazoATableau(StackPane mazo, HBox hbox, Deque<Carta> cartas) {
        int indexUltimaCartaMazo = mazo.getChildren().size() - 1;
        if (indexUltimaCartaMazo >= CANT_COLUMNAS) {
            for (int i = 0; i < CANT_COLUMNAS; i++) {
                Pane paneActual = (Pane) mazo.getChildren().remove(indexUltimaCartaMazo - i);

                Canvas dibujo = (Canvas) paneActual.getChildren().get(0);
                VistaCarta.dibujarCarta(dibujo, cartas.pop());

                Pane paneDestino = (Pane) hbox.getChildren().get(i);
                int indexUltimoNodo = paneDestino.getChildren().size() - 1;
                double posUltimoNodo = paneDestino.getChildren().get(indexUltimoNodo).getLayoutY();
                paneDestino.getChildren().add(paneActual);
                paneActual.setLayoutX(LAYOUT_X_CARTAS);
                if (indexUltimoNodo == 0) {
                    paneActual.setLayoutY(posUltimoNodo);
                } else {
                    paneActual.setLayoutY(posUltimoNodo + ESPACIADO_CARTAS);
                }

                paneActual.setOnMouseClicked(event -> {
                    if (primerClic) {
                        accionPrimerClic(paneActual);
                    } else {
                        accionSegundoClic(anteriorCliqueado, paneActual);
                    }
                });
            }
        }
    }

    private void accionSegundoClic(Pane origen, Pane destino) {
        var indices = buscarIndices(origen);
        var filaOrigen = indices.get(0);
        var colOrigen = indices.get(1);

        var indices_ = buscarIndices(destino);
        var colDestino = indices_.get(1);

        if (juego.moverTableauATableau(colOrigen, filaOrigen, colDestino)) {
            UtilidadGUI.moverATableau(origen, destino);
        }

        primerClic = true;

        // Le saco el efecto se seleccion a las cartas
        var sombreado = new DropShadow(0, javafx.scene.paint.Color.BLACK);
        origen.setEffect(sombreado);
    }

    static void accionClicBase(Pane pane) {
        if (!primerClic) {
            var indices = buscarIndices(anteriorCliqueado);
            var filaOrigen = indices.get(0);
            var colOrigen = indices.get(1);

            var cartas = juego.verCartasTableau();
            var cartaOrigen = cartas.get(colOrigen).get(filaOrigen);

            if (juego.moverTableauABase(colOrigen, filaOrigen)) {
                var parent = ((Pane) anteriorCliqueado.getParent());
                var hijos = parent.getChildren();
                if (filaOrigen + CANT_CARTAS_BASE + 1 > filaOrigen) {
                    hijos.subList(filaOrigen + 1, filaOrigen + CANT_CARTAS_BASE + 1).clear();
                }

                Canvas nuevoCanvas = new Canvas(ANCHO_PANE, ALTO_PANE);
                VistaCarta.dibujarCarta(nuevoCanvas, new Carta(cartaOrigen.getColor(), cartaOrigen.getPalo(),1,false));
                pane.getChildren().add(nuevoCanvas);
            }

            primerClic = true;
            var sombreado = new DropShadow(0, javafx.scene.paint.Color.BLACK);
            anteriorCliqueado.setEffect(sombreado);
        }
    }
}