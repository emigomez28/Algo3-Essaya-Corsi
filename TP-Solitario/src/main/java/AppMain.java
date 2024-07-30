import Klondike.SolitarioKlondike;
import Solitario.Solitario;
import Spider.SolitarioSpider;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.List;

public class AppMain extends Application {
    protected static final int ANCHO_VENTANA = 640;
    protected static final int ALTO_VENTANA = 680;
    protected static final int LAYOUT_X_CARTAS = 10;
    protected static final int LAYOUT_Y_CARTAS = 20;
    protected static final int ESPACIADO_CARTAS = 20;
    protected static final int ANCHO_PANE = 47;
    protected static final int ALTO_PANE = 80;
    protected static final int HBOX_INF = 1;
    protected static final int HBOX_SUP = 0;
    protected static final String COLOR_BASES = "-fx-background-color: #034903;";
    protected static final String COLOR_FONDO = "-fx-background-color: #0f740f;";
    protected static final String RUTA_ARCHIVO = "solitario.txt";
    protected static boolean primerClic;
    protected static Pane anteriorCliqueado;
    private Application app;
    private Solitario juego;
    @FXML
    protected GridPane ventana;
    @FXML
    protected Pane escena;
    @FXML
    protected MenuBar menu;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            var fis = new FileInputStream(RUTA_ARCHIVO);
            reconstruirSolitario(stage, fis);
        } catch (FileNotFoundException ex) {
            pedirOpciones(stage);
        }
    }

    @Override
    public void stop() throws Exception {
        var fos = new FileOutputStream(RUTA_ARCHIVO);
        juego.serializar(fos);
    }

    protected void inicializarEscena(Stage stage, String juego) {
        escena.setStyle(COLOR_FONDO);
        var scene = new Scene(escena, ANCHO_VENTANA, ALTO_VENTANA);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle(juego);
        stage.show();
    }

    void inicializarBase(Stage stage, Solitario juego, int inicio, Juego tipoJuego) {
        HBox HBoxSuperior = (HBox) ventana.getChildren().get(HBOX_SUP);
        var pilones = HBoxSuperior.getChildren();
        var pilonesBase = juego.verCartasBase();

        for (int i = inicio; i < pilones.size() ; i++) {
            var pilonAct = (StackPane) pilones.get(i);
            var pilonBaseAct = pilonesBase.get(i-inicio);

            Pane nuevoPane = crearPane();
            if (pilonBaseAct.peek() != null) {
                var dibujo = new Canvas(ANCHO_PANE, ALTO_PANE);
                VistaCarta.dibujarCarta(dibujo, pilonBaseAct.peek());
                nuevoPane.getChildren().add(dibujo);
            }

            nuevoPane.setOnMouseClicked(event -> {

                switch (tipoJuego) {
                    case KLONDIKE -> AplicacionKlondike.accionClicBase(nuevoPane);
                    case SPIDER -> AplicacionSpider.accionClicBase(nuevoPane);
                }

                if (juego.juegoGanado()) {
                    stage.setScene(VistaGeneral.mostrarVictoria(stage));
                }
            });
            pilonAct.getChildren().add(nuevoPane);
        }
    }

    public void pedirOpciones(Stage stage) {
        var vbox = new VBox();
        var opciones = new ChoiceBox<String>();
        opciones.getItems().addAll("Klondike", "Spider");
        opciones.getSelectionModel().selectFirst();
        opciones.setMaxWidth(Double.MAX_VALUE);
        vbox.getChildren().add(opciones);

        var alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Selección juego");
        alerta.getDialogPane().setHeaderText("Selecciona el solitario");

        var btnAceptar = new ButtonType("Aceptar");
        var btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alerta.getButtonTypes().setAll(btnAceptar, btnCancelar);

        alerta.getDialogPane().setContent(vbox);
        var result = alerta.showAndWait();

        if (result.isPresent() && result.get() == btnAceptar) {
            if (opciones.getValue().equals("Klondike")) {
                var nuevoJuego = new SolitarioKlondike();
                this.juego = nuevoJuego;
                this.app = new AplicacionKlondike(nuevoJuego, this);
                try {
                    app.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else {
                var nuevoJuego = new SolitarioSpider();
                this.juego = nuevoJuego;
                this.app = new AplicacionSpider(nuevoJuego, this);
                try {
                    app.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void reconstruirSolitario(Stage stage, FileInputStream fis) throws Exception {
        try {
            var juego = SolitarioKlondike.deserializar(fis);
            this.juego = juego;
            this.app = new AplicacionKlondike(juego, this);
            app.start(stage);

        } catch (RuntimeException exKlondike) {
            try {
                fis.close();
                fis = new FileInputStream(RUTA_ARCHIVO);

                var juego = SolitarioSpider.deserializar(fis);
                this.juego = juego;
                this.app = new AplicacionSpider(juego, this);
                app.start(stage);
            } catch (RuntimeException exSpider) {
                pedirOpciones(stage);
            }
        }

        finally {
            fis.close();
        }
    }

    protected void accionPrimerClic(Pane paneActual) {
        primerClic = false;
        var sombreado = new DropShadow(20, javafx.scene.paint.Color.BLACK);
        paneActual.setEffect(sombreado);
        anteriorCliqueado = paneActual;
    }

    protected static List<Integer> buscarIndices(Pane paneBuscado) {
        var HBoxPadre = (HBox) paneBuscado.getParent().getParent();

        var filaOrigen = -1;
        var colOrigen = -1;
        for (var col : HBoxPadre.getChildren()) {
            var col1 = (Pane) col;
            var a = col1.getChildren().indexOf(paneBuscado);
            var b = HBoxPadre.getChildren().indexOf(col);
            if (a != -1) {
                filaOrigen = a-1;
                colOrigen = b;
            }
        }

        return List.of(filaOrigen, colOrigen);
    }

    protected static Pane crearPane() {
        Pane nuevoPane = new Pane();
        nuevoPane.setStyle(COLOR_BASES);
        nuevoPane.setMaxHeight(ALTO_PANE);
        nuevoPane.setMaxWidth(ANCHO_PANE);
        nuevoPane.setLayoutX(LAYOUT_X_CARTAS);
        nuevoPane.setLayoutY(LAYOUT_Y_CARTAS);
        return nuevoPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}