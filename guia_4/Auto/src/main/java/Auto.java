public class Auto {
   private boolean moviendo;

   public void cambiarEstadoMovimiento() { // Este metodo antes se llamaba mover,
       moviendo = !moviendo;               // lo que violaba el principio de
   }                                       // PoLA, uno espera que mover mueva el auto

   public boolean enMovimiento() {
       return moviendo;
   }


}
