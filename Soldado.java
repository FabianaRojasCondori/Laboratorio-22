public abstract class Soldado {
    protected String nombre;
    protected int nivelVida;
    protected int ataque;
    protected int defensa;
    protected int fila;
    protected int columna;

    public Soldado(String nombre, int nivelVida, int ataque, int defensa) {
        this.nombre = nombre;
        this.nivelVida = nivelVida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    public abstract void atacar();

    public abstract void defender();

    public void setPosicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public String getSimbolo() {
        return "S";
    }

    public int getNivelVida() {
        return nivelVida;
    }

    public void setNivelVida(int nivelVida) {
        this.nivelVida = nivelVida;
    }

    protected static int getRandomNivelVida(int min, int max) {
        return new java.util.Random().nextInt((max - min) + 1) + min;
    }

    protected static int getRandomValue(int min, int max) {
        return new java.util.Random().nextInt((max - min) + 1) + min;
    }
}
