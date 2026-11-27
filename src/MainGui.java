import javax.swing.*;
import java.util.ArrayList;

public class MainGui {

    static ArrayList<Colaborador> colaboradores = new ArrayList<>();

    public static void main(String[] args) {
        int opcao = 1;

        while (opcao != 0) {
            String menu = "1 - Cadastrar Padrao\n"
                    + "2 - Cadastrar Comissionado\n"
                    + "3 - Cadastrar Producao\n"
                    + "4 - Mostrar Folha\n"
                    + "5 - Mostrar Resumo\n"
                    + "0 - Sair\n\n"
                    + "Escolha uma opcao:";

            String texto = JOptionPane.showInputDialog(menu);
            if (texto == null) {
                break;
            }

            if (!texto.matches("[0-5]")) {
                JOptionPane.showMessageDialog(null, "Opcao invalida.");
                continue;
            }

            opcao = Integer.parseInt(texto);

            if (opcao == 1) {
                cadastrarPadrao();
            } else if (opcao == 2) {
                cadastrarComissionado();
            } else if (opcao == 3) {
                cadastrarProducao();
            } else if (opcao == 4) {
                mostrarFolha();
            } else if (opcao == 5) {
                mostrarResumo();
            }
        }
    }

    static void cadastrarPadrao() {
        int matricula = lerMatricula();
        if (matricula == -1) return;

        String nome = lerNome();
        if (nome == null) return;

        colaboradores.add(new ColaboradorPadrao(matricula, nome));
        JOptionPane.showMessageDialog(null, "Colaborador cadastrado!");
    }

    static void cadastrarComissionado() {
        int matricula = lerMatricula();
        if (matricula == -1) return;

        String nome = lerNome();
        if (nome == null) return;

        String v = JOptionPane.showInputDialog("Valor de vendas:");
        if (v == null) return;
        double vendas = Double.parseDouble(v.replace(",", "."));

        String p = JOptionPane.showInputDialog("Percentual de comissao:");
        if (p == null) return;
        double percentual = Double.parseDouble(p.replace(",", "."));

        colaboradores.add(new ColaboradorComissionado(matricula, nome, vendas, percentual));
        JOptionPane.showMessageDialog(null, "Colaborador cadastrado!");
    }

    static void cadastrarProducao() {
        int matricula = lerMatricula();
        if (matricula == -1) return;

        String nome = lerNome();
        if (nome == null) return;

        String q = JOptionPane.showInputDialog("Quantidade de pecas:");
        if (q == null) return;
        int pecas = Integer.parseInt(q);

        String v = JOptionPane.showInputDialog("Valor por peca:");
        if (v == null) return;
        double valorPeca = Double.parseDouble(v.replace(",", "."));

        colaboradores.add(new ColaboradorProducao(matricula, nome, pecas, valorPeca));
        JOptionPane.showMessageDialog(null, "Colaborador cadastrado!");
    }

    static int lerMatricula() {
        while (true) {
            String texto = JOptionPane.showInputDialog("Matricula:");
            if (texto == null) return -1;

            if (!texto.matches("[0-9]{1,9}")) {
                JOptionPane.showMessageDialog(null, "Digite apenas numeros.");
                continue;
            }

            int numero = Integer.parseInt(texto);
            boolean existe = false;
            for (int i = 0; i < colaboradores.size(); i++) {
                if (colaboradores.get(i).getMatricula() == numero) {
                    existe = true;
                }
            }

            if (existe) {
                JOptionPane.showMessageDialog(null, "Matricula ja cadastrada.");
                continue;
            }

            return numero;
        }
    }

    static String lerNome() {
        while (true) {
            String nome = JOptionPane.showInputDialog("Nome:");
            if (nome == null) return null;

            if (!nome.matches("[A-Za-zÀ-ÿ ]{1,50}")) {
                JOptionPane.showMessageDialog(null, "Digite apenas letras.");
                continue;
            }

            return nome;
        }
    }

    static void mostrarFolha() {
        if (colaboradores.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum colaborador cadastrado.");
            return;
        }

        String texto = "";
        for (int i = 0; i < colaboradores.size(); i++) {
            Colaborador c = colaboradores.get(i);
            texto = texto + "Matricula: " + c.getMatricula() + "\n";
            texto = texto + "Nome: " + c.getNome() + "\n";
            texto = texto + "Salario Final: R$ " + String.format("%.2f", c.calcularSalarioFinal()) + "\n";
            texto = texto + "----------------------\n";
        }

        JOptionPane.showMessageDialog(null, texto);
    }

    static void mostrarResumo() {
        if (colaboradores.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum colaborador cadastrado.");
            return;
        }

        double total = 0;
        for (int i = 0; i < colaboradores.size(); i++) {
            total = total + colaboradores.get(i).calcularSalarioFinal();
        }

        double media = total / colaboradores.size();

        String texto = "Quantidade: " + colaboradores.size() + "\n";
        texto = texto + "Total da folha: R$ " + String.format("%.2f", total) + "\n";
        texto = texto + "Media salarial: R$ " + String.format("%.2f", media);

        JOptionPane.showMessageDialog(null, texto);
    }
}