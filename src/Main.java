import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Colaborador> colaboradores = new ArrayList<>();
        int opcao;

        do {
            System.out.println("\n===== FOLHA DE PAGAMENTO =====");
            System.out.println("1 - Cadastrar Colaborador Padrao");
            System.out.println("2 - Cadastrar Colaborador Comissionado");
            System.out.println("3 - Cadastrar Colaborador Producao");
            System.out.println("4 - Gerar Folha de Pagamento");
            System.out.println("5 - Exibir Resumo da Folha");
            System.out.println("0 - Sair do Programa");
            System.out.print("Escolha uma opcao: ");
            String textoOpcao = sc.nextLine();
            while (!textoOpcao.matches("[0-5]")) {
                System.out.print("Opcao invalida. Digite um numero de 0 a 5: ");
                textoOpcao = sc.nextLine();
            }
            opcao = Integer.parseInt(textoOpcao);

            switch (opcao) {
                case 1:
                    int matricula = lerMatricula(sc, colaboradores);
                    String nome = lerNome(sc);
                    colaboradores.add(new ColaboradorPadrao(matricula, nome));
                    System.out.println("Colaborador cadastrado!");
                    break;

                case 2:
                    int matriculaC = lerMatricula(sc, colaboradores);
                    String nomeC = lerNome(sc);
                    double vendas = lerDecimal(sc, "Valor de vendas: ", 9, 999999999.99);
                    double percentual = lerDecimal(sc, "Percentual de comissao (0 a 100): ", 3, 100);
                    colaboradores.add(new ColaboradorComissionado(matriculaC, nomeC, vendas, percentual));
                    System.out.println("Colaborador cadastrado!");
                    break;

                case 3:
                    int matriculaP = lerMatricula(sc, colaboradores);
                    String nomeP = lerNome(sc);
                    int pecas = lerInteiro(sc, "Quantidade de pecas: ");
                    double valorPeca = lerDecimal(sc, "Valor por peca: ", 5, 99999.99);
                    colaboradores.add(new ColaboradorProducao(matriculaP, nomeP, pecas, valorPeca));
                    System.out.println("Colaborador cadastrado!");
                    break;

                case 4:
                    if (colaboradores.isEmpty()) {
                        System.out.println("Nenhum colaborador cadastrado.");
                    } else {
                        System.out.println("\n===== FOLHA DE PAGAMENTO =====");
                        for (Colaborador c : colaboradores) {
                            c.exibirDados();
                            System.out.println("------------------------------");
                        }
                    }
                    break;

                case 5:
                    exibirResumo(colaboradores);
                    break;

                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
            }
        } while (opcao != 0);
    }

    public static int lerMatricula(Scanner sc, ArrayList<Colaborador> lista) {
        System.out.print("Matricula: ");
        String texto = sc.nextLine();
        while (!texto.matches("[0-9]{1,9}") || matriculaExiste(lista, texto)) {
            System.out.print("Matricula invalida ou ja cadastrada (ate 9 digitos): ");
            texto = sc.nextLine();
        }
        return Integer.parseInt(texto);
    }

    public static String lerNome(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        while (!nome.matches("[A-Za-zÀ-ÿ ]{1,50}")) {
            System.out.print("Nome invalido. Digite apenas letras (ate 50 caracteres): ");
            nome = sc.nextLine();
        }
        return nome;
    }

    public static int lerInteiro(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        String texto = sc.nextLine();
        while (!texto.matches("[0-9]{1,9}")) {
            System.out.print("Valor invalido. Digite apenas numeros inteiros (ate 9 digitos): ");
            texto = sc.nextLine();
        }
        return Integer.parseInt(texto);
    }

    public static double lerDecimal(Scanner sc, String mensagem, int maxDigitos, double maximo) {
        System.out.print(mensagem);
        String texto = sc.nextLine();
        while (!texto.matches("[0-9]{1," + maxDigitos + "}([.,][0-9]{1,2})?")
                || Double.parseDouble(texto.replace(",", ".")) > maximo) {
            System.out.print("Valor invalido. Ex: 1500 ou 1500,50 (maximo " + maximo + "): ");
            texto = sc.nextLine();
        }
        return Double.parseDouble(texto.replace(",", "."));
    }

    public static boolean matriculaExiste(ArrayList<Colaborador> lista, String textoMatricula) {
        int numero = Integer.parseInt(textoMatricula);
        for (Colaborador c : lista) {
            if (c.getMatricula() == numero) {
                return true;
            }
        }
        return false;
    }

    public static int contarColaboradores(ArrayList<Colaborador> lista) {
        return lista.size();
    }

    public static double calcularTotalFolha(ArrayList<Colaborador> lista) {
        double total = 0;
        for (Colaborador c : lista) {
            total += c.calcularSalarioFinal();
        }
        return total;
    }

    public static double calcularMediaSalarial(ArrayList<Colaborador> lista) {
        if (contarColaboradores(lista) == 0) {
            return 0;
        }
        return calcularTotalFolha(lista) / contarColaboradores(lista);
    }

    public static void exibirResumo(ArrayList<Colaborador> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado.");
            return;
        }
        System.out.println("\n===== RESUMO DA FOLHA =====");
        System.out.println("Quantidade de colaboradores: " + contarColaboradores(lista));
        System.out.printf("Soma total da folha: R$ %.2f%n", calcularTotalFolha(lista));
        System.out.printf("Media salarial: R$ %.2f%n", calcularMediaSalarial(lista));
    }
}