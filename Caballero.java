public class Caballero extends Soldado {

    public Caballero(String nombre) {
        super(nombre, getRandomNivelVida(10, 12), 13, 7);
    }

    @Override
    public void atacar() {
    }

    @Override
    public void defender() {
    }

    public void montar() {
    }

    public void desmontar() {
    }

    @Override
    public String getSimbolo() {
        return "C";
    }
}
