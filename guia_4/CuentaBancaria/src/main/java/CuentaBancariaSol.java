public class CuentaBancariaSol {
    private int saldo;

    public void depositar(int cantidad) {
        saldo += cantidad;
    }

    public void retirar(int cantidad) {
        if (obtenerSaldo() >= cantidad) {
            saldo -= cantidad;
            System.out.println(cantidad + "retirados exitosamente");
        } else {
            System.out.println("Fondos insuficientes");
        }

    }

    public int obtenerSaldo() {
        return saldo;
    }
}
