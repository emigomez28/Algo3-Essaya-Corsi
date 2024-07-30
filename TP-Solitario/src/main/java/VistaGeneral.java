import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class VistaGeneral {
    protected static Scene mostrarVictoria(Stage stage) {
        Pane root = new Pane();
        root.setPrefSize(AppMain.ANCHO_VENTANA, AppMain.ALTO_VENTANA);

        Group animacionesGrupo = new Group();

        agregarFuegosArtificiales(animacionesGrupo);

        Text mensajeVictoria = new Text("¡Has ganado el juego!");
        mensajeVictoria.setFont(Font.font("Arial", 30));
        mensajeVictoria.setFill(javafx.scene.paint.Color.BLACK);
        mensajeVictoria.setTranslateX((640 - mensajeVictoria.getBoundsInLocal().getWidth()) / 2);
        mensajeVictoria.setTranslateY((480 - mensajeVictoria.getBoundsInLocal().getHeight()) / 2);
        animacionesGrupo.getChildren().add(mensajeVictoria);

        root.getChildren().addAll(animacionesGrupo);

        ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(1), mensajeVictoria);
        scaleIn.setFromX(0.5);
        scaleIn.setFromY(0.5);
        scaleIn.setToX(1);
        scaleIn.setToY(1);
        scaleIn.setCycleCount(1);
        scaleIn.setAutoReverse(true);

        ScaleTransition scaleOut = new ScaleTransition(Duration.seconds(1), mensajeVictoria);
        scaleOut.setFromX(1);
        scaleOut.setFromY(1);
        scaleOut.setToX(0.5);
        scaleOut.setToY(0.5);
        scaleOut.setCycleCount(1);
        scaleOut.setAutoReverse(true);

        SequentialTransition loopScale = new SequentialTransition(scaleIn, scaleOut);
        loopScale.setCycleCount(Animation.INDEFINITE);
        loopScale.play();

        root.setOnMouseClicked(event -> {
            stage.close();
            var app = new AppMain();
            app.pedirOpciones(new Stage());
        });

        return new Scene(root, AppMain.ANCHO_VENTANA, AppMain.ALTO_VENTANA);
    }

    private static void agregarFuegosArtificiales(Group group) {
        int numFuegosArtificiales = 30;

        for (int i = 0; i < numFuegosArtificiales; i++) {
            double x = new Random().nextDouble() * 800;
            double y = new Random().nextDouble() * 600;

            for (int j = 0; j < 20; j++) {
                Circle fuego = new Circle(2, javafx.scene.paint.Color.rgb(
                        new Random().nextInt(255),
                        new Random().nextInt(255),
                        new Random().nextInt(255)
                ));

                fuego.setTranslateX(x);
                fuego.setTranslateY(y);

                group.getChildren().add(fuego);

                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1.5 + Math.random() * 1.5), fuego);
                translateTransition.setToX(new Random().nextDouble() * 400 - 200);
                translateTransition.setToY(new Random().nextDouble() * 400 - 200);
                translateTransition.setCycleCount(Animation.INDEFINITE); // Repetir en bucle

                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5 + Math.random() * 1.5), fuego);
                fadeTransition.setToValue(0);
                fadeTransition.setCycleCount(1);

                RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), fuego);
                rotateTransition.setByAngle(360);
                rotateTransition.setCycleCount(1);

                ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), fuego);
                scaleTransition.setToX(0);
                scaleTransition.setToY(0);
                scaleTransition.setCycleCount(1);

                ParallelTransition parallelTransition = new ParallelTransition(translateTransition, fadeTransition, rotateTransition, scaleTransition);
                parallelTransition.setOnFinished(event -> group.getChildren().remove(fuego));
                parallelTransition.play();
            }
        }
    }
}
