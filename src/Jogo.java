import java.util.Scanner;

public class Jogo {
    private Baralho baralho;
    private Jogador jogador;
    private Jogador maquina;
    private int rodadasJogador;
    private int rodadasMaquina;
    private int apostaAtual;
    private Scanner scanner;

    public Jogo() {
        baralho = new Baralho();
        jogador = new Jogador("Jogador");
        maquina = new Jogador("Máquina");
        rodadasJogador = 0;
        rodadasMaquina = 0;
        apostaAtual = 1; // Aposta inicial (1 ponto)
        scanner = new Scanner(System.in);
    }

    public void iniciarJogo() {
        baralho.embaralhar();
        distribuirCartas();

        while (rodadasJogador < 2 && rodadasMaquina < 2) {
            exibirPlacar();
            System.out.println("------------------------------");
            System.out.println("\nInício da próxima rodada!");
            System.out.println("Aposta atual: " + apostaAtual + " ponto(s)");
            System.out.println("------------------------------");

            // Vira uma nova carta e define a manilha
            Carta vira = baralho.virarCarta();
            String manilha = baralho.getManilha();
            System.out.println("Carta virada: " + vira);
            System.out.println("Manilha: " + manilha);

            jogador.recalcularForcaCartas(manilha);
            maquina.recalcularForcaCartas(manilha);

            rodada();
        }

        if (rodadasJogador == 2) {
            System.out.println("Você venceu o jogo! Parabéns!");
            System.out.println("------------------------------");
        } else {
            System.out.println("A máquina venceu o jogo! Tente novamente.");
            System.out.println("------------------------------");
        }
    }

    // Método para distribuir as cartas para os jogadores
    private void distribuirCartas() {
        for (int i = 0; i < 3; i++) {
            jogador.receberCarta(baralho.distribuirCarta());
            maquina.receberCarta(baralho.distribuirCarta());
        }
    }

    private void exibirPlacar() {
        System.out.println("Placar:");
        System.out.println("Jogador: " + rodadasJogador + " | Máquina: " + rodadasMaquina);
    }

    private void rodada() {
        System.out.println("\nSuas cartas: ");
        System.out.println(jogador.mostrarCartas());

        System.out.println("Deseja gritar 'Truco'? (s/n)");
        String resposta = scanner.next();
        if (resposta.equalsIgnoreCase("s")) {
            System.out.println("Você gritou 'Truco'!");

            if (maquina.decidirAceitarTruco()) {
                System.out.println("A máquina aceitou o 'Truco'!");
                apostaAtual = 3; // Aumenta a aposta para 3 pontos
            } else {
                System.out.println("A máquina recusou o 'Truco'!");
                System.out.println("Você ganhou 1 ponto!");
                System.out.println("------------------------------");
                rodadasJogador += 1;
                return;
            }
        }
        if (maquina.decidirGritarTruco()) {
            System.out.println("A máquina gritou 'Truco'!");

            System.out.println("Deseja aceitar o 'Truco'? (s/n)");
            String respostaJogador = scanner.next();
            if (respostaJogador.equalsIgnoreCase("s")) {
                System.out.println("Você aceitou o 'Truco'!");
                apostaAtual = 3;
            } else {
                System.out.println("Você recusou o 'Truco'!");
                System.out.println("A máquina ganhou 1 ponto!");
                System.out.println("------------------------------");
                rodadasMaquina += 1;
                return;
            }
        }

        int escolhaJogador = -1;
        while (escolhaJogador < 0 || escolhaJogador >= jogador.getCartas().size()) {
            System.out.println("Escolha uma carta (1, 2 ou 3): ");
            escolhaJogador = scanner.nextInt() - 1;
            if (escolhaJogador < 0 || escolhaJogador >= jogador.getCartas().size()) {
                System.out.println("Escolha inválida! Tente novamente.");
            }
        }

        Carta cartaJogador = jogador.jogarCarta(escolhaJogador);
        Carta cartaMaquina = maquina.jogarCarta();

        System.out.println("Você jogou: " + cartaJogador);
        System.out.println("A máquina jogou: " + cartaMaquina);

        if (cartaJogador.getForca() > cartaMaquina.getForca()) {
            System.out.println("Você venceu a rodada!");
            rodadasJogador += apostaAtual;
        } else {
            System.out.println("A máquina venceu a rodada!");
            rodadasMaquina += apostaAtual;
        }
        apostaAtual = 1;
    }

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.iniciarJogo();
    }
}