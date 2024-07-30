import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private ArrayList<Usuario> contactos;
    private ArrayList<Chat> chats;

    public Usuario(String nombre, ArrayList<Usuario> contactos, ArrayList<Chat> chats) {
        this.nombre = nombre;
        this.contactos = contactos;
        this.chats = chats;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Usuario> getContactos() {
        return contactos;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContactos(ArrayList<Usuario> contactos) {
        this.contactos = contactos;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }
}
