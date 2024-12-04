import java.util.Scanner;

import java.util.Scanner;

public class Juego {
    private Mapa mapa;
    private Ejercito ejercito1;
    private Ejercito ejercito2;
    private Scanner scanner;

    public Juego() {
        this.mapa = new Mapa();
        System.out.println("Tipo de Territorio: " + mapa.getTipoTerritorio());
        this.ejercito1 = new Ejercito(mapa.getTipoTerritorio());
        this.ejercito2 = new Ejercito(mapa.getTipoTerritorio());
        this.mapa.posicionarEjercito(ejercito1);
        this.mapa.posicionarEjercito(ejercito2);
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        ejercito1.imprimirResumen();
        ejercito2.imprimirResumen();
        
        boolean juegoTerminado = false;
        while (!juegoTerminado) {
            mapa.dibujarMapa();
            System.out.println("Turno del jugador 1 (" + ejercito1.getNombreReino() + ")");
            juegoTerminado = turnoJugador(ejercito1, ejercito1.getNombreReino().substring(0, 1));
            if (!juegoTerminado) {
                mapa.dibujarMapa();
                System.out.println("Turno del jugador 2 (" + ejercito2.getNombreReino() + ")");
                juegoTerminado = turnoJugador(ejercito2, ejercito2.getNombreReino().substring(0, 1));
            }
        }
    }

    private boolean turnoJugador(Ejercito ejercito, String simboloEjercito) {
        System.out.println("Opciones:");
        System.out.println("1. Mover soldado");
        System.out.println("2. Terminar juego");
        System.out.print("Seleccione una opcion: ");
        int opcion = scanner.nextInt();

        if (opcion == 2) {
            terminarJuego();
            return true;
        }

        System.out.println("Ingrese la fila y columna del soldado a mover (Ejemplo: 3 4):");
        int fila = scanner.nextInt();
        int columna = scanner.nextInt();
        Soldado soldado = mapa.getSoldado(fila, columna);

        if (soldado == null || !ejercito.getSoldados().contains(soldado)) {
            System.out.println("Movimiento invalido, intente de nuevo.");
            return false;
        }

        System.out.println("Opciones de movimiento:");
        System.out.println("1. Arriba");
        System.out.println("2. Abajo");
        System.out.println("3. Izquierda");
        System.out.println("4. Derecha");
        System.out.print("Seleccione una opcion (1-4): ");
        int direccion = scanner.nextInt();

        int nuevaFila = fila, nuevaColumna = columna;
        switch (direccion) {
            case 1:
                nuevaFila--;
                break;
            case 2:
                nuevaFila++;
                break;
            case 3:
                nuevaColumna--;
                break;
            case 4:
                nuevaColumna++;
                break;
            default:
                System.out.println("Direccion invalida, intente de nuevo.");
                return false;
        }

        Soldado rival = mapa.getSoldado(nuevaFila, nuevaColumna);
        if (rival == null) {
            boolean movimientoExitoso = mapa.moverSoldado(soldado, nuevaFila, nuevaColumna, simboloEjercito);
            if (!movimientoExitoso) {
                System.out.println("Movimiento invalido, intente de nuevo.");
                return false;
            }
        } else {
            batalla(soldado, rival);
            mapa.eliminarSoldado(rival);
            mapa.moverSoldado(soldado, nuevaFila, nuevaColumna, simboloEjercito);
        }

        return verificarJuegoTerminado();
    }


    private void batalla(Soldado soldado1, Soldado soldado2) {
        int totalVida = soldado1.getNivelVida() + soldado2.getNivelVida();
        double probabilidad1 = (double) soldado1.getNivelVida() / totalVida;
        double probabilidad2 = (double) soldado2.getNivelVida() / totalVida;

        double aleatorio = Math.random();
        if (aleatorio <= probabilidad1) {
            System.out.println(soldado1.nombre + " ha ganado la batalla contra " + soldado2.nombre);
            soldado1.setNivelVida(soldado1.getNivelVida() + 1);
            eliminarSoldado(soldado2);
        } else {
            System.out.println(soldado2.nombre + " ha ganado la batalla contra " + soldado1.nombre);
            soldado2.setNivelVida(soldado2.getNivelVida() + 1);
            eliminarSoldado(soldado1);
        }
    }

    private void eliminarSoldado(Soldado soldado) {
        for (Ejercito ejercito : mapa.getEjercitos()) {
            ejercito.getSoldados().remove(soldado);
        }
    }

    private boolean verificarJuegoTerminado() {
        if (ejercito1.getSoldados().isEmpty() || ejercito2.getSoldados().isEmpty()) {
            terminarJuego();
            return true;
        }
        return false;
    }

    private void terminarJuego() {
        if (ejercito1.getSoldados().isEmpty()) {
            System.out.println(ejercito2.getNombreReino() + " ha ganado el juego!");
        } else if (ejercito2.getSoldados().isEmpty()) {
            System.out.println(ejercito1.getNombreReino() + " ha ganado el juego!");
        } else {
            System.out.println("El juego ha terminado!");
        }
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciar();
    }
}





