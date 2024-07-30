// Se puede ver que el metodo setTemperatura() altera el estado interno
// del Sensor, lo cual puede llegar a mostrar comportamiento poco deseado.
// Como solución propongo usar el patrón Observer el cual se encargue de ver que pasa
// con la temperatura del sensor


public class SensorTemperatura {
    private double temperatura;

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
        if (temperatura > 100) {
            mostrarAlerta("temperatura alta");
        }
        if (temperatura > 150) {
            apagarEquipo();
        }
    }

    private void mostrarAlerta(String mensaje) {}
    private void apagarEquipo() {}
}
