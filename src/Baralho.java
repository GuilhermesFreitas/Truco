import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
    private List<Carta> cartas;
    private Carta vira;

    public Baralho() {
        this.cartas = new ArrayList<>();
        gerarCartas();
    }

    // Método para gerar todas as cartas do baralho
    private void gerarCartas() {
        String[] valores = {"4", "5", "6", "7", "Q", "J", "K", "A", "2", "3"};
        String[] naipes = {"Ouros", "Espadas", "Copas", "Paus"};

        for (String naipe : naipes) {
            for (String valor : valores) {
                Carta carta = new Carta(valor, naipe);
                cartas.add(carta);
            }
        }
    }

    public void embaralhar() {
        Collections.shuffle(cartas);
    }

    public Carta distribuirCarta() {
        return cartas.isEmpty() ? null : cartas.remove(0);
    }

    // Manilha
    public Carta virarCarta() {
        vira = distribuirCarta();
        return vira;
    }


    public String getManilha() {
        if (vira == null) {
            return "Nenhuma manilha definida ainda.";
        }

        String valorVira = vira.getValor();
        String[] valores = {"4", "5", "6", "7", "Q", "J", "K", "A", "2", "3"};
        int index = 0;

        // Encontra o índice do valor da vira
        for (int i = 0; i < valores.length; i++) {
            if (valores[i].equals(valorVira)) {
                index = i;
                break;
            }
        }


        String valorManilha;
        if (index == valores.length - 1) {
            valorManilha = valores[0];
        } else {
            valorManilha = valores[index + 1];
        }

        return valorManilha;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public Carta getVira() {
        return vira;
    }
}