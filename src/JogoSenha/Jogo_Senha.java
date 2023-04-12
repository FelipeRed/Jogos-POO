package JogoSenha;
import java.util.ArrayList;
import java.util.Scanner;
/* ALGORITMO
enquanto (tentativa < 10) {
    digite um palpite
    verificar quantos números estão nas posições certas (x)
    verificar quantos números estão certos, mas nas posições erradas (y)
    se (x == 4) {
        Jogador j venceu
        pontuação = 100 - (n_tentativas * 10)
    } se não {
        imprime o histórico de tentativas informando x e y
    }
}*/

public class Jogo_Senha {
    private final Senha senha;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Jogo_Senha jogo = new Jogo_Senha();

        jogo.imprimir_regras();

        ArrayList<String> historico = new ArrayList<>(); //lista com o histórico de tentativas do jogador
        int pontuacao = 0;
        int i = 0;
        while (i < 10) {
            System.out.print("Digite seu palpite: ");
            int palpite = input.nextInt();
            if (palpite < 1000) {
                System.out.println("Favor inserir um número inteiro com 4 dígitos maiores que 1 e menores que 4.");
            } else {
                int[] acertos = jogo.analisar_palpite(palpite);
                if (acertos[0] == 4) {
                    pontuacao = 100 - (i * 10);
                    break;
                } else {
                    add_Historico(acertos, palpite, historico);
                }
                limpar_tela();
                print_Historico(historico, i);
                i++;
            }
        }
        resultado_Partida(pontuacao, jogo.getSenha().toString());
    }

    public Jogo_Senha() {
        senha = new Senha();
    }

    public Senha getSenha() {
        return senha;
    }

    public void imprimir_regras() {
        System.out.println("Regras do jogo:");
        System.out.println(" - A senha possui 4 dígitos.");
        System.out.println(" - Os números podem repetir.");
        System.out.println(" - Números possíveis: 1, 2, 3, 4.\n");
    }

    private int[] get_posicoes(int palpite) {  //irá retornar um array com o valor de cada posição do palpite
        int primeira_posicao = (palpite / 1000) % 10;
        int segunda_posicao = (palpite / 100) % 10;
        int terceira_posicao = (palpite / 10) % 10;
        int quarta_posicao = palpite % 10;
        return new int[] {primeira_posicao, segunda_posicao, terceira_posicao, quarta_posicao};
    }

    public int[] analisar_palpite(int palpite) {
        /* irá analisar o palpite dado como parâmetro e retornar o array analise onde:
            - análise[0] conterá quantos números o jogador acertou tanto a posição quanto o valor
            - análise[1] conterá quantos números o jogador acertou, mas estão na posição errada */
        int[] p_senha = this.senha.get_posicoes();
        int[] p_palpite = get_posicoes(palpite);
        int num_e_pos_certa = 0;
        int num_certos = 0;
        //laço que irá contar quantos números estão na posição certa
        for (int i = 0; i < p_palpite.length; i++) {
            if (p_palpite[i] == p_senha[i]) {
                num_e_pos_certa += 1;
                p_palpite[i] = 0;
                p_senha[i] = 0;
            }
        }
        //laço que irá contar quantos números estão certos, mas na posição errada
        for (int i = 0; i < p_palpite.length; i++) {
            if (p_palpite[i] != 0) {
                for (int j = 0; j < p_palpite.length; j++) {
                    if ((p_palpite[i] == p_senha[j])) {
                        num_certos += 1;
                        p_senha[j] = 0;
                        break;
                    }
                }
            }
        }
        return new int[] {num_e_pos_certa, num_certos};
    }
    public static void limpar_tela() {  //método que irá imprimir várias linhas vazias para limpar a tela do terminal
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void add_Historico(int[] acertos, int palpite, ArrayList<String> historico) {
        //será gerado uma String explicando a tentativa do jogador e adicionada a seu histórico
        String tentativa = palpite + " | Números certos nas posições certas: " + acertos[0] +
                "  -  Números certos nas posições erradas: " + acertos[1];
        historico.add(tentativa);
    }

    public static void print_Historico(ArrayList<String> historico , int i) {
        //laço que irá imprimir o histórico de todas as jogadas do jogador
        for (String item : historico) {
            System.out.println(item);
        }
        System.out.println("Tentativas restantes: " + (9 - i));
    }

    public static void resultado_Partida(int pontuacao, String senha) {
        if (pontuacao > 0) {
            System.out.println("Parabéns você descobriu a senha!");
            System.out.println("Sua pontuação: " + pontuacao);
        } else {
            System.out.println("Você falhou... A senha era " + senha);
        }
    }
}
