public class Ocho {
    public static void main (String[] args) {
        try {
            String str = "123";
            int num = Integer.parseInt(str);
            System.out.println("Dentro del bloque try");
        } finally {
            System.out.println("Dentro del bloque finally");
        }
        System.out.println("Fin.");
    }
}

// La salida es: "Dentro del bloque try", "Dentro del bloque finally" y "Fin."

// Corrección: Todo ok
