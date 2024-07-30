public class Cinco {
    public static void main (String[] args) {
        try {
            String str = "123";
            int num = Integer.parseInt(str);
            System.out.println("Dentro del bloque try");
        } catch (NumberFormatException ex) {
            System.out.println("catch block executed...");
        }
        System.out.println("Fin.");
    }
}


// La salida es: Dentro del bloque try y Fin

// Corrección: Todo ok
