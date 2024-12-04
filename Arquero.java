public class Arquero extends Soldado {
    private int numeroFlechas;

    public Arquero(String nombre) {
        super(nombre, getRandomNivelVida(3, 5), 7, 3);
        this.numeroFlechas = getRandomValue(10, 50);
    }

    @Override
    public void atacar() {
    }

    @Override
    public void defender() {
    }

    @Override
    public String getSimbolo() {
        return "A";
    }
}
