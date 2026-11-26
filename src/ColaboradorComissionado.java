public class ColaboradorComissionado extends Colaborador {

    private double valorVendas;
    private double percentualComissao;

    public ColaboradorComissionado(int matricula, String nome,
                                   double valorVendas, double percentualComissao) {
        super(matricula, nome);
        this.valorVendas = valorVendas;
        this.percentualComissao = percentualComissao;
    }

    public double getValorVendas() {
        return valorVendas;
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    public double calcularComissao() {
        return valorVendas * percentualComissao / 100;
    }

    @Override
    public double calcularSalarioFinal() {
        return getSalarioBase() + calcularComissao();
    }

    @Override
    public void exibirDados() {
        System.out.println("Tipo: Comissionado");
        super.exibirDados();
        System.out.printf("Comissao: R$ %.2f%n", calcularComissao());
        System.out.printf("Salario Final: R$ %.2f%n", calcularSalarioFinal());
    }
}