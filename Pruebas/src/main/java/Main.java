import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static final java.util.Scanner teclado = new java.util.Scanner(System.in);
    public static final java.io.PrintStream pantalla = new java.io.PrintStream(System.out);

    public static void asd(String[] args) {
        try {
            aMethod();
            System.out.println("B");
        } catch (Exception ex) {
            System.out.println("C");
        } finally {
            System.out.println("D");
        }
        System.out.println("E");
    }

    public static void aMethod() {
        System.out.println("A" + 1 / 0);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        pantalla.println("Ingrese el nombre de un archivo (si es necesario con su ruta)");
        pantalla.println("Por ejemplo: src/main/java/ejercicio2/Main.java ");
        pantalla.println("Archivo: ");
        String nombreArchivo = teclado.nextLine();
        FileReader fileReader = new FileReader(nombreArchivo);
        LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
        String renglon;
        while ((renglon = lineNumberReader.readLine()) != null) {
            pantalla.printf("%3d) %s \n", lineNumberReader.getLineNumber(), renglon);
        }
    }
}

// La salida será C D E

// Corrección: La salida es una ArithmeticException -> No se ejecuta nada porque nunca se atrapa el error