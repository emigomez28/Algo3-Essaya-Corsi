import java.util.HashMap;
import java.util.ArrayList;
public class Biblioteca {
   private ArrayList<Cliente> clientes;
   private HashMap<Libro, Integer> stock;
   private HashMap<Cliente, ArrayList<Libro>> prestados;

   public Biblioteca(ArrayList<Cliente> clientes, HashMap<Libro, Integer> stock, HashMap<Cliente, ArrayList<Libro>> prestados) {
       this.clientes = clientes;
       this.stock = stock;
       this.prestados = prestados;
   }

   public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
   }

   public void setStock(HashMap<Libro, Integer> stock) {
        this.stock = stock;
   }

   public void setPrestados(HashMap<Cliente, ArrayList<Libro>> prestados) {
        this.prestados = prestados;
   }

   private void agregarLibro(Libro nuevo) {
       if (stock.get(nuevo) == null) {
           stock.put(nuevo, 1);
       } else {
           stock.put(nuevo, stock.get(nuevo) +1);
       }
   }

   private void borrarLibro(Libro libro) {
       if (stock.get(libro) == null) {
           System.out.println("El libro no pertenece a la biblioteca");
       } else if (stock.get(libro) - 1 == 0) {
           stock.remove(libro);
       } else {
           stock.put(libro, stock.get(libro) -1);
       }
   }

   public void prestarLibro(Libro libro, Cliente cliente) {
       prestados.computeIfAbsent(cliente, k -> new ArrayList<Libro>());
        prestados.get(cliente).add(libro);
       stock.put(libro, stock.get(libro) -1);
   }

   public void devolverLibro(Libro libro, Cliente cliente) {
       prestados.remove(cliente);
       stock.put(libro, stock.get(libro) +1);
       cliente.borrarLibro(libro);
   }
}
