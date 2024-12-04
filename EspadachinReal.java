public class EspadachinReal extends Espadachin {

    public EspadachinReal(String nombre) {
        super(nombre);
        this.nivelVida = 12;
    }

    @Override
    public void atacar() {
    }

    @Override
    public void defender() {
    }

    public void lanzarCuchillos() {
    }

    @Override
    public String getSimbolo() {
        return "R";
    }
}
