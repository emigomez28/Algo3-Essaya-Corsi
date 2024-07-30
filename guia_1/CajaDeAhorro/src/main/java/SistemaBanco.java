import java.util.HashMap;

public class SistemaBanco {
    private HashMap<String, CuentaBancaria> cuentasRegistradas;

    public SistemaBanco(HashMap<String, CuentaBancaria> cuentasRegistradas) {
        this.cuentasRegistradas = cuentasRegistradas;
    }
}
