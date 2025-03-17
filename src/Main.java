import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("------ TRUCO PAULISTA ------");
            System.out.println("1 - Jogar Truco Paulista");
            System.out.println("2 - Sair");
            System.out.println("------------------------------");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    Jogo jogo = new Jogo();
                    jogo.iniciarJogo();
                    break;
                case 2:
                    System.out.println("Saindo... Obrigado por jogar!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        } while (opcao != 2);

        scanner.close();
    }
}