public class Lancero extends Soldado {
    private int longitudLanza;

    public Lancero(String nombre) {
        super(nombre, getRandomNivelVida(5, 8), 5, 10);
        this.longitudLanza = getRandomValue(30, 100);
    }

    @Override
    public void atacar() {
    }

    @Override
    public void defender() {
    }

    public void schiltrom() {
    }

    @Override
    public String getSimbolo() {
        return "L";
    }
}
