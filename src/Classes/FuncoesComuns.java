package Classes;

import java.util.HashMap;
import java.util.Scanner;

public class FuncoesComuns {
    public static void atualizar_Ou_Adicionar_NovoRecorde(Jogador jogador, int pontos, String nomeJogo){
        //função que será utilizada sempre ao final de cada jogo
        HashMap<String, Recorde> recordes = jogador.getRecordes();
        Recorde recordeAtual = recordes.get(nomeJogo);
        if (recordeAtual != null) {
            if (recordeAtual.getPontuacao() < pontos) {
                System.out.println("Parabéns! Você bateu seu recorde anterior de " + recordeAtual.getPontuacao() +
                        " pontos!");
                recordeAtual.atualizarRecorde(pontos);
                System.out.println("Seu novo recorde é: " + pontos);
            } else {
                System.out.println("Sua pontuação: " + pontos);
                System.out.println("Seu recorde atual: " + recordeAtual.getPontuacao());
            }
        } else {
            Recorde record = new Recorde(pontos, jogador, nomeJogo);
            jogador.addRecord(nomeJogo, record);
            System.out.println("Seu novo recorde é: " + pontos);
        }
        pausarPrograma();
        limpar_tela();
    }
    public static void limpar_tela() {  //método que irá imprimir várias linhas vazias para limpar a tela do terminal
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    public static void pausarPrograma() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Pressione ENTER para continuar: ");
        teclado.nextLine();
        limpar_tela();
    }
}
