public class Carta {
    private String valor;
    private String naipe;
    private int forca;
    private String manilha;

    public Carta(String valor, String naipe) {
        this.valor = valor;
        this.naipe = naipe;
    }

    public void calcularForca(String manilha) {
        this.manilha = manilha;


        if (valor.equals(manilha)) {
            switch (naipe) {
                case "Paus": forca = 14; break;
                case "Copas": forca = 13; break;
                case "Espadas": forca = 12; break;
                case "Ouros": forca = 11; break;
            }
        } else {
            switch (valor) {
                case "4": forca = 1; break;
                case "5": forca = 2; break;
                case "6": forca = 3; break;
                case "7": forca = 4; break;
                case "Q": forca = 5; break;
                case "J": forca = 6; break;
                case "K": forca = 7; break;
                case "A": forca = 8; break;
                case "2": forca = 9; break;
                case "3": forca = 10; break;
                default: forca = 0; break;
            }
        }
    }

    public String getValor() {
        return valor;
    }

    public String getNaipe() {
        return naipe;
    }

    public int getForca() {
        return forca;
    }

    @Override
    public String toString() {
        return valor + " de " + naipe;
    }
}