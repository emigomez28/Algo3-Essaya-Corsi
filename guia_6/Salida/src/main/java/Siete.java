public class Siete {
    public static void main(String[] args) {
        int[] arr = new int[4];
        try {
            int i = arr[4];
            System.out.println("Dentro del bloque try");
        } finally {
            System.out.println("Dentro del bloque finally");
        }
        System.out.println("Fin.");
    }
}

// Termina la ejecución antes de mostrar algo.

// Corrección: Muestra "Dentro del bloque finally" y luego la excepción
