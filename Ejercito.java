import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ejercito {
    private String nombreReino;
    private List<Soldado> soldados;
    private String tipoTerritorio;

    public Ejercito(String tipoTerritorio) {
        this.nombreReino = generarReino();
        this.tipoTerritorio = tipoTerritorio;
        this.soldados = new ArrayList<>();
        generarSoldados();
        aplicarBeneficiosTerritoriales();
    }

    private String generarReino() {
        String[] reinos = {"Inglaterra", "Francia", "Castilla-Aragon", "Moros", "Sacro Imperio Romano Germanico"};
        return reinos[new Random().nextInt(reinos.length)];
    }

    private void generarSoldados() {
        Random rand = new Random();
        int numSoldados = rand.nextInt(10) + 1; 

        for (int i = 0; i < numSoldados; i++) {
            int tipoSoldado = rand.nextInt(4); 
            switch (tipoSoldado) {
                case 0 -> soldados.add(new Espadachin("Espadachin" + (i + 1)));
                case 1 -> soldados.add(new Arquero("Arquero" + (i + 1)));
                case 2 -> soldados.add(new Caballero("Caballero" + (i + 1)));
                case 3 -> soldados.add(new Lancero("Lancero" + (i + 1)));
            }
        }

        if (nombreReino.equals("Moros")) {
            soldados.add(new CaballeroMoro("CaballeroMoro"));
        } else if (nombreReino.equals("Inglaterra")) {
            soldados.add(new EspadachinReal("EspadachinReal"));
        }
    }

    private void aplicarBeneficiosTerritoriales() {
        boolean aplicarBeneficio = false;

        if (nombreReino.equals("Inglaterra") && tipoTerritorio.equals("BOSQUE")) {
            aplicarBeneficio = true;
        } else if (nombreReino.equals("Francia") && tipoTerritorio.equals("CAMPO_ABIERTO")) {
            aplicarBeneficio = true;
        } else if (nombreReino.equals("Castilla-Aragon") && tipoTerritorio.equals("MONTANA")) {
            aplicarBeneficio = true;
        } else if (nombreReino.equals("Moros") && tipoTerritorio.equals("DESIERTO")) {
            aplicarBeneficio = true;
        } else if (nombreReino.equals("Sacro Imperio Romano Germanico") && 
                   (tipoTerritorio.equals("BOSQUE") || tipoTerritorio.equals("PLAYA") || tipoTerritorio.equals("CAMPO_ABIERTO"))) {
            aplicarBeneficio = true;
        }

        if (aplicarBeneficio) {
            for (Soldado soldado : soldados) {
                soldado.setNivelVida(soldado.getNivelVida() + 1);
            }
        }
    }

    public List<Soldado> getSoldados() {
        return soldados;
    }

    public String getNombreReino() {
        return nombreReino;
    }

    public void imprimirResumen() {
        int espadachines = 0;
        int arqueros = 0;
        int caballeros = 0;
        int lanceros = 0;
        int caballerosMoros = 0;
        int espadachinReales = 0;

        for (Soldado soldado : soldados) {
            if (soldado instanceof Espadachin && !(soldado instanceof EspadachinReal)) {
                espadachines++;
            } else if (soldado instanceof Arquero) {
                arqueros++;
            } else if (soldado instanceof Caballero && !(soldado instanceof CaballeroMoro)) {
                caballeros++;
            } else if (soldado instanceof Lancero) {
                lanceros++;
            } else if (soldado instanceof CaballeroMoro) {
                caballerosMoros++;
            } else if (soldado instanceof EspadachinReal) {
                espadachinReales++;
            }
        }

        System.out.println("Ejercito " + nombreReino);
        System.out.println("Cantidad total de soldados: " + soldados.size());
        System.out.println("Espadachines: " + espadachines);
        System.out.println("Arqueros: " + arqueros);
        System.out.println("Caballeros: " + caballeros);
        System.out.println("Lanceros: " + lanceros);
        System.out.println("Caballero Moro: " + caballerosMoros);
        System.out.println("Espadachin Real: " + espadachinReales);
        System.out.println("");
    }
}
