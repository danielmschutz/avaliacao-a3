public class ColaboradorProducao extends Colaborador {

    private int quantidadePecas;
    private double valorPorPeca;

    public ColaboradorProducao(int matricula, String nome,
                               int quantidadePecas, double valorPorPeca) {
        super(matricula, nome);
        this.quantidadePecas = quantidadePecas;
        this.valorPorPeca = valorPorPeca;
    }

    public int getQuantidadePecas() {
        return quantidadePecas;
    }

    public double getValorPorPeca() {
        return valorPorPeca;
    }

    public double calcularProdutividade() {
        return quantidadePecas * valorPorPeca;
    }

    @Override
    public double calcularSalarioFinal() {
        return getSalarioBase() + calcularProdutividade();
    }

    @Override
    public void exibirDados() {
        System.out.println("Tipo: Producao");
        super.exibirDados();
        System.out.printf("Produtividade: R$ %.2f%n", calcularProdutividade());
        System.out.printf("Salario Final: R$ %.2f%n", calcularSalarioFinal());
    }
}