import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mapa {
    private String tipoTerritorio;
    private String[][] tablero;
    private List<Ejercito> ejercitos;

    public Mapa() {
        this.tipoTerritorio = generarTipoTerritorio();
        this.tablero = new String[10][10];
        this.ejercitos = new ArrayList<>();
    }

    private String generarTipoTerritorio() {
        String[] territorios = {"BOSQUE", "CAMPO_ABIERTO", "MONTANA", "DESIERTO", "PLAYA"};
        return territorios[new Random().nextInt(territorios.length)];
    }

    public String getTipoTerritorio() {
        return tipoTerritorio;
    }

    public List<Ejercito> getEjercitos() {
        return ejercitos;
    }

    public void posicionarEjercito(Ejercito ejercito) {
        ejercitos.add(ejercito);
        Random rand = new Random();
        for (Soldado soldado : ejercito.getSoldados()) {
            while (true) {
                int fila = rand.nextInt(10);
                int columna = rand.nextInt(10);
                if (tablero[fila][columna] == null) {
                    tablero[fila][columna] = ejercito.getNombreReino().substring(0, 1) + soldado.getSimbolo();
                    soldado.setPosicion(fila, columna);
                    break;
                }
            }
        }
    }

    public boolean moverSoldado(Soldado soldado, int nuevaFila, int nuevaColumna, String ejercito) {
        if (nuevaFila < 0 || nuevaFila >= 10 || nuevaColumna < 0 || nuevaColumna >= 10) {
            return false;
        }

        if (tablero[nuevaFila][nuevaColumna] == null) {
            tablero[soldado.fila][soldado.columna] = null;
            tablero[nuevaFila][nuevaColumna] = ejercito + soldado.getSimbolo();
            soldado.setPosicion(nuevaFila, nuevaColumna);
            return true;
        }

        return false;
    }

    public Soldado getSoldado(int fila, int columna) {
        for (Ejercito ejercito : ejercitos) {
            for (Soldado soldado : ejercito.getSoldados()) {
                if (soldado.fila == fila && soldado.columna == columna) {
                    return soldado;
                }
            }
        }
        return null;
    }

    public void eliminarSoldado(Soldado soldado) {
        for (Ejercito ejercito : ejercitos) {
            ejercito.getSoldados().remove(soldado);
        }
        if (soldado.fila != -1 && soldado.columna != -1) {
            tablero[soldado.fila][soldado.columna] = null;
            soldado.setPosicion(-1, -1); 
        }
    }

    public void dibujarMapa() {
        System.out.print("  ");
        for (int i = 0; i < tablero[0].length; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();

        for (int i = 0; i < tablero.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == null) {
                    System.out.print("|  ");
                } else {
                    System.out.print("|" + tablero[i][j]);
                }
            }
            System.out.println("|");
        }
    }
}
