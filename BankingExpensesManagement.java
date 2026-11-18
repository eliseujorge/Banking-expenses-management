
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankingExpensesManagement {

    static class Transacao {
        String tipo;
        String categoria;
        double valor;
        LocalDate data;

        public Transacao(String tipo, String categoria, double valor, LocalDate data) {
            this.tipo = tipo;
            this.categoria = categoria;
            this.valor = valor;
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double saldo = 0.0;
        ArrayList<Transacao> transacoes = new ArrayList<>();
        HashMap<String, Double> despesasFixas = new HashMap<>();

        while (true) {
            System.out.println("\n=== SISTEMA BANCARIO ===");
            System.out.println("0 - Ver saldo atual");
            System.out.println("1 - Adicionar budget (Depósito)");
            System.out.println("2 - Nova transação (Débito)");
            System.out.println("3 - Criar despesa fixa");
            System.out.println("4 - Ver extrato");
            System.out.println("5 - Ver resumo por categoria");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            String escolha = scanner.nextLine().trim();

            if (escolha.equals("0")) {
                System.out.printf("\nSaldo atual: %.2f Mzn\n", saldo);

            } else if (escolha.equals("1")) {
                System.out.println("\nTipo de depósito:");
                System.out.println("1 - Salário");
                System.out.println("2 - Outro");
                System.out.println("0 - Voltar ao menu principal");
                System.out.print("Escolha o tipo de depósito: ");
                String tipoDeposito = scanner.nextLine().trim();

                if (tipoDeposito.equals("0")) {
                    continue;
                }

                String categoria;
                if (tipoDeposito.equals("1")) {
                    categoria = "salário";
                } else {
                    System.out.print("Digite a origem do depósito: ");
                    categoria = scanner.nextLine().toLowerCase().trim();
                    if (categoria.isEmpty()) {
                        categoria = "outro";
                    }
                }

                double valor = 0;
                try {
                    System.out.print("Valor do depósito: ");
                    valor = Double.parseDouble(scanner.nextLine().replace(',', '.'));
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido.");
                    continue;
                }

                if (valor <= 0) {
                    System.out.println("O valor deve ser maior que zero.");
                    continue;
                }

                LocalDate data = LocalDate.now();
                saldo += valor;
                transacoes.add(new Transacao("credito", categoria, valor, data));
                System.out.printf("Depósito de Mzn %.2f realizado com sucesso.\n", valor);

            } else if (escolha.equals("2")) {
                String tipo = "debito";

                while (true) {
                    System.out.printf("\n=== Nova transação (%s) ===\n", tipo.toUpperCase());
                    System.out.println("1 - Adicionar transação");
                    System.out.println("0 - Voltar ao menu principal");
                    System.out.print("Escolha: ");
                    String subEscolha = scanner.nextLine().trim();

                    if (subEscolha.equals("0")) {
                        break;
                    }
                    if (!subEscolha.equals("1")) {
                        System.out.println("Opção inválida.");
                        continue;
                    }

                    String categoria = "";
                    double valor = 0;

                    if (!despesasFixas.isEmpty()) {
                        System.out.println("\nDespesas fixas disponíveis:");
                        for (Map.Entry<String, Double> entry : despesasFixas.entrySet()) {
                            System.out.printf("- %s (Mzn %.2f)\n", entry.getKey(), entry.getValue());
                        }
                        System.out.print("Deseja associar a uma despesa fixa? (digite o nome ou 'nenhuma'): ");
                        String inputFixa = scanner.nextLine().toLowerCase().trim();

                        if (despesasFixas.containsKey(inputFixa)) {
                            categoria = inputFixa;
                            valor = despesasFixas.get(inputFixa);
                        } else {
                            System.out.print("Digite a categoria da transação: ");
                            categoria = scanner.nextLine().toLowerCase().trim();
                            try {
                                System.out.print("Valor da transação: ");
                                valor = Double.parseDouble(scanner.nextLine().replace(',', '.'));
                            } catch (NumberFormatException e) {
                                System.out.println("Valor inválido.");
                                continue;
                            }
                        }
                    } else {
                        System.out.print("Digite a categoria da transação: ");
                        categoria = scanner.nextLine().toLowerCase().trim();
                        try {
                            System.out.print("Valor da transação: ");
                            valor = Double.parseDouble(scanner.nextLine().replace(',', '.'));
                        } catch (NumberFormatException e) {
                            System.out.println("Valor inválido.");
                            continue;
                        }
                    }

                    if (valor <= 0) {
                        System.out.println("O valor deve ser maior que zero.");
                        continue;
                    }

                    if (valor > saldo) {
                        System.out.println("Saldo insuficiente.");
                        continue;
                    }

                    LocalDate data = LocalDate.now();
                    saldo -= valor;
                    transacoes.add(new Transacao(tipo, categoria, valor, data));
                    System.out.printf("Transação de Mzn %.2f registrada com sucesso na categoria '%s'.\n", valor, categoria);
                    break;
                }

            } else if (escolha.equals("3")) {
                System.out.print("Nome da despesa fixa: ");
                String nome = scanner.nextLine().toLowerCase().trim();
                double valor = 0;
                try {
                    System.out.print("Valor fixo: ");
                    valor = Double.parseDouble(scanner.nextLine().replace(',', '.'));
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido.");
                    continue;
                }
                despesasFixas.put(nome, valor);
                System.out.printf("Despesa fixa '%s' registrada com valor Mzn %.2f.\n", nome, valor);

            } else if (escolha.equals("4")) {
                if (transacoes.isEmpty()) {
                    System.out.println("Nenhuma transação registrada.");
                    continue;
                }
                System.out.println("\n=== Extrato ===");
                for (int i = 0; i < transacoes.size(); i++) {
                    Transacao t = transacoes.get(i);
                    String sinal = t.tipo.equals("credito") ? "+" : "-";
                    System.out.printf("%d | %s | %s | %sMzn %.2f\n", i, t.data, t.categoria, sinal, t.valor);
                }
                System.out.printf("\nSaldo atual: Mzn %.2f\n", saldo);

            } else if (escolha.equals("5")) {
                if (transacoes.isEmpty()) {
                    System.out.println("Nenhuma transação registrada.");
                    continue;
                }
                HashMap<String, Double> resumo = new HashMap<>();
                for (Transacao t : transacoes) {
                    if (t.tipo.equals("debito")) {
                        resumo.put(t.categoria, resumo.getOrDefault(t.categoria, 0.0) + t.valor);
                    }
                }
                if (resumo.isEmpty()) {
                    System.out.println("Nenhum gasto encontrado.");
                    continue;
                }
                System.out.println("\n=== Resumo por Categoria ===");
                for (Map.Entry<String, Double> entry : resumo.entrySet()) {
                    String catCapitalizada = entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
                    System.out.printf("%s: Mzn %.2f\n", catCapitalizada, entry.getValue());
                }

            } else if (escolha.equals("6")) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }
}
