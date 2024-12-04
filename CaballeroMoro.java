public class CaballeroMoro extends Caballero {

    public CaballeroMoro(String nombre) {
        super(nombre);
        this.nivelVida = 13;
    }

    @Override
    public void atacar() {
    }

    @Override
    public void defender() {
    }

    public void lanzarFlechas() {
    }

    @Override
    public String getSimbolo() {
        return "M";
    }
}
