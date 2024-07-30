import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Main extends Application {
    public void start(Stage stage) throws Exception {
        var cargar = new Button("Cargar libro");
        var prestar = new Button("Prestar libro");
        var salir = new Button("Salir");
        var controlador = new VBox(8);
        var volver = new Button("Volver");
        var biblioteca = new Biblioteca();
        //var vista = new StackPane();
        controlador.getChildren().addAll(cargar, prestar, salir);
        controlador.setAlignment(Pos.CENTER);

        var scene = new Scene(controlador, 500, 500);
        stage.setScene(scene);

        cargar.setOnAction(e -> stage.setScene(sceneCargar(volver, biblioteca)));

        salir.setOnAction(e -> stage.close());

        volver.setOnAction(e -> stage.setScene(scene));

        stage.show();
    }

    private Scene sceneCargar(Button volver, Biblioteca biblioteca) {
        var titulo = new Label("Título:");
        var codigo = new Label("Código ISBN:");
        var fecha = new Label("Fecha de publicación:");
        var autores = new Label("Autores:");
        var texto_1 = new TextField();
        var texto_2 = new TextField();
        var texto_3 = new TextField();
        var texto_4 = new TextField();
        var guardar = new Button("Guardar");
        var controlador = new VBox(8);
        var guardar_autores = new ArrayList<String>();
        if (!texto_4.toString().isEmpty()) {
            var autor = texto_4.toString().split(",");
            guardar_autores.addAll(Arrays.asList(autor));
        }
        guardar.setOnAction(e -> biblioteca.agregarLibro(
                new Libro(texto_2.toString(), texto_1.toString(), texto_3.toString(), guardar_autores)
        ));
        guardar.setOnAction(e -> texto_1.clear());
        guardar.setOnAction(e -> texto_2.clear());
        guardar.setOnAction(e -> texto_3.clear());
        guardar.setOnAction(e -> texto_4.clear());
        controlador.getChildren().addAll(titulo, texto_1, codigo, texto_2,fecha, texto_3, autores, texto_4, guardar, volver);
        controlador.setAlignment(Pos.CENTER);
        return new Scene(controlador, 500, 500);
    }


}
