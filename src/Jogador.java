import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private List<Carta> cartas;

    public Jogador(String nome) {
        this.nome = nome;
        this.cartas = new ArrayList<>();
    }

    public void receberCarta(Carta carta) {
        cartas.add(carta);
    }

    public void recalcularForcaCartas(String manilha) {
        for (Carta carta : cartas) {
            carta.calcularForca(manilha);
        }
    }

    public String mostrarCartas() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cartas.size(); i++) {
            sb.append((i + 1) + ". " + cartas.get(i) + "\n");
        }
        return sb.toString();
    }

    public Carta jogarCarta(int indice) {
        return cartas.remove(indice);
    }

    public Carta jogarCarta() {
        if (!cartas.isEmpty()) {
            cartas.sort((c1, c2) -> Integer.compare(c2.getForca(), c1.getForca()));
            return cartas.remove(0);
        }
        return null;
    }

    public boolean decidirGritarTruco() {
        for (Carta carta : cartas) {
            if (carta.getForca() >= 10) {
                return true;
            }
        }
        return false;
    }

    public boolean decidirAceitarTruco() {
        for (Carta carta : cartas) {
            if (carta.getForca() >= 10) {
                return true;
            }
        }
        return false;
    }

    public List<Carta> getCartas() {
        return cartas;
    }
}