public class Espadachin extends Soldado {
    private int longitudEspada;

    public Espadachin(String nombre) {
        super(nombre, getRandomNivelVida(8, 10), 10, 8);
        this.longitudEspada = getRandomValue(30, 100);
    }

    @Override
    public void atacar() {
    }

    @Override
    public void defender() {
    }

    public void muroEscudos() {
    }

    @Override
    public String getSimbolo() {
        return "E";
    }
}
