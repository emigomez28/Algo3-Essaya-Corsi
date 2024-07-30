public class CajeroAutomatico {
    private CuentaBancaria cuenta;

    public void retirarDinero(int cantidad) {
        if (cuenta.obtenerSaldo() >= cantidad) {
            cuenta.retirar(cantidad);
            System.out.println(cantidad + " retirados exitosamente.");
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }
}

// retirarDinero deberia ser un metodo de cuentaBancaria, y el algoritmo se tendria
// que resolver dentro del mismo

// Viola el principio TDA y/o SOC