public class Uno {
    public static void main (String[] args) {
        int[] arr = new int[4];
        try {
            int i = arr[4];
            System.out.println("Dentro del bloque try");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Excepción atrapada en el bloque catch");
        }
        System.out.println("Fin.");
    }

}

// La salida será: Excepción atrapada en el bloque catch y Fin

// Corrección: Todo ok
