import java.util.HashMap;

public class CuentaBancaria {
    private final String alias;
    private Integer sumaDinero;
    private HashMap<String, CuentaBancaria> contactos;

    public CuentaBancaria(String alias, Integer sumaDinero, HashMap<String, CuentaBancaria> contactos) {
        this.alias = alias;
        this.sumaDinero = sumaDinero;
        this.contactos = contactos;
    }

    public String getAlias() {
       return alias;
    }
    public void addContacto(CuentaBancaria cuentaBancaria) {
        contactos.put(cuentaBancaria.getAlias(), cuentaBancaria);
    }
}
