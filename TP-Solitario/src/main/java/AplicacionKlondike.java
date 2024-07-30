import Klondike.SolitarioKlondike;
import Solitario.Carta;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;


public class AplicacionKlondike extends AppMain {
    private static final int INICIO_BASE = 3;
    private static final String RUTA_FXML = "klondike.fxml";
    private static SolitarioKlondike juego;
    private final AppMain padre;
    private static Origen origenAntClic;

    public AplicacionKlondike(SolitarioKlondike solitario, AppMain appMain) {
        juego = solitario;
        padre = appMain;
    }

    @Override
    public void start(Stage stage) throws Exception {
        var loaderKlondike = new FXMLLoader(getClass().getResource(RUTA_FXML));
        loaderKlondike.setController(this);
        loaderKlondike.load();
        inicializarEscena(stage, "Klondike");

        var tableau = juego.verCartasTableau();
        var mazo = juego.verCartasMazo();
        inicializarMazo(mazo);
        inicializarBase(stage, juego, INICIO_BASE, Juego.KLONDIKE);
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
                        origenAntClic = Origen.TABLEAU;
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

        StackPane waste = (StackPane) HBoxSup.getChildren().get(1);
        Pane paneBase = crearPane();

        paneBase.setOnMouseClicked(event -> {
            waste.getChildren().clear();
            juego.rellenarMazo();
            inicializarMazo(juego.verCartasMazo());
        });

        mazo.getChildren().add(paneBase);

        var cartas = new ArrayDeque<>(mazoCartas);
        var iterator = cartas.descendingIterator();

        while (iterator.hasNext()) {
            Pane nuevoPane = new Pane();

            var cartaActual = iterator.next();

            Canvas nuevoCanvas = new Canvas(ANCHO_PANE, ALTO_PANE);
            VistaCarta.dibujarCarta(nuevoCanvas, cartaActual);
            nuevoPane.getChildren().add(nuevoCanvas);
            Canvas canvasDorso = new Canvas(ANCHO_PANE, ALTO_PANE);
            VistaCarta.dibujarCartaTrasera(canvasDorso);
            nuevoPane.getChildren().add(canvasDorso);

            nuevoPane.setMaxHeight(ALTO_PANE);
            nuevoPane.setMaxWidth(ANCHO_PANE);

            nuevoPane.setOnMouseClicked(event -> {
                juego.pedirCarta();
                moverAWaste(nuevoPane, waste, mazo);
            });
            mazo.getChildren().add(nuevoPane);
        }
    }

    private void moverAWaste(Pane paneActual, StackPane waste, StackPane mazo) {
        mazo.getChildren().remove(paneActual);
        var canvasFrente = (Canvas) paneActual.getChildren().get(0);
        var nuevoPane = crearPane();
        nuevoPane.getChildren().add(canvasFrente);

        nuevoPane.setOnMouseClicked(event -> {
            if (primerClic) {
                origenAntClic = Origen.MAZO;
                accionPrimerClic(nuevoPane);
            } else {
                accionSegundoClic(anteriorCliqueado, nuevoPane);
            }
        });
        waste.getChildren().add(nuevoPane);
    }

    private void moverMazoATableau(StackPane mazo, Pane paneOrigen, Pane paneDestino) {
        int indexUltimoNodo = ((Pane) paneDestino.getParent()).getChildren().size() - 1;
        double posUltimoNodo = ((Pane) paneDestino.getParent()).getChildren().get(indexUltimoNodo).getLayoutY();
        mazo.getChildren().remove(paneOrigen);
        redefinirEvento(paneOrigen, posUltimoNodo, paneDestino);
    }

    private void accionSegundoClic(Pane origen, Pane destino) {
        if (origen != destino) {
            var indices = buscarIndices(origen);
            var filaOrigen = indices.get(0);
            var colOrigen = indices.get(1);

            var indices_ = buscarIndices(destino);
            var colDestino = indices_.get(1);

            switch (origenAntClic) {
                case TABLEAU -> {
                    if (juego.moverTableauATableau(colOrigen, filaOrigen, colDestino)) {
                        UtilidadGUI.moverATableau(origen, destino);
                    }
                }
                case MAZO -> {
                    if (juego.moverMazoATableau(colDestino)) {
                        StackPane waste = (StackPane) ((HBox) ventana.getChildren().get(HBOX_SUP)).getChildren().get(1);
                        moverMazoATableau(waste, origen, destino);
                    }
                }
            }
        }
        primerClic = true;

        // Le saco el efecto se seleccion a las cartas
        var sombreado = new DropShadow(0, javafx.scene.paint.Color.BLACK);
        origen.setEffect(sombreado);
    }

    static void accionClicBase(Pane pane) {
        if (!primerClic) {
            var indices = buscarIndices(anteriorCliqueado);
            var colOrigen = indices.get(1);
            var indices_ = buscarIndices(pane);
            var colDestino = indices_.get(1);

            switch (origenAntClic) {
                case TABLEAU -> {
                    if (juego.moverTableauABase(colOrigen, colDestino - 3)) {
                        var columna = (Pane) anteriorCliqueado.getParent();
                        var cartasCol = columna.getChildren();
                        cartasCol.remove(cartasCol.size() - 1);
                        var nuevoTope = (Pane) cartasCol.get(cartasCol.size() - 1);

                        if (nuevoTope.getChildren().size() > 1) {
                            var canvasFrente = (Canvas) nuevoTope.getChildren().get(0);
                            var canvasDorso = (Canvas) nuevoTope.getChildren().get(1);
                            canvasDorso.setVisible(false);
                            canvasFrente.setVisible(true);
                        }
                        Canvas canvas = (Canvas) anteriorCliqueado.getChildren().get(0);
                        pane.getChildren().add(canvas);
                    }
                }
                case MAZO -> {
                    if (juego.moverMazoABase(colDestino - 3)) {
                        var waste = (StackPane) anteriorCliqueado.getParent();
                        var cartasCol = waste.getChildren();
                        cartasCol.remove(cartasCol.size() - 1);
                        Canvas canvas = (Canvas) anteriorCliqueado.getChildren().get(0);
                        pane.getChildren().add(canvas);
                    }
                }
            }
            primerClic = true;
            var sombreado = new DropShadow(0, javafx.scene.paint.Color.BLACK);
            anteriorCliqueado.setEffect(sombreado);
        }
    }

    private void redefinirEvento(Pane origen, double posUltimoNodo, Pane destino) {
        var canvasFrente = (Canvas) origen.getChildren().get(0);
        var nuevoPane = new Pane();
        nuevoPane.setMaxHeight(ALTO_PANE);
        nuevoPane.setMaxWidth(ANCHO_PANE);
        nuevoPane.setLayoutX(LAYOUT_X_CARTAS);

        var columna = ((Pane) destino.getParent()).getChildren();
        var espaciado = ESPACIADO_CARTAS;
        if (columna.size() == 1) {
            espaciado = 0;
        }

        nuevoPane.setLayoutY(posUltimoNodo + espaciado);
        nuevoPane.getChildren().add(canvasFrente);

        var paneDestino = (Pane) (destino.getParent());

        nuevoPane.setOnMouseClicked(event -> {
            if (primerClic) {
                origenAntClic = Origen.TABLEAU;
                accionPrimerClic(nuevoPane);
            } else {
                accionSegundoClic(anteriorCliqueado, nuevoPane);
            }
        });
        paneDestino.getChildren().add(nuevoPane);
    }
}

