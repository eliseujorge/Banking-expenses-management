# Sistema Bancário Simples

**Projeto Inspirado no Banking Expenses Management em Python da Ancha Pedro**

Github: https://github.com/anchaapedro/Banking-expenses_management

(Feito com fins de praticar o aprendizado em java)

Este é um programa em Java que simula um sistema bancário básico, permitindo ao usuário gerenciar saldo e transações.

## Funcionalidades

1. **Depositar dinheiro**: Permite adicionar dinheiro à conta.
2. **Nova transação**: Permite registrar entradas (créditos) ou saídas (débitos) com categoria e valor. É possível registrar várias transações seguidas sem sair da categoria.
3. **Ver extrato**: Mostra todas as transações realizadas, com data, categoria, tipo e valor, além do saldo atual.
4. **Ver resumo mensal**: Exibe o total de entradas, saídas e saldo de um mês específico.
5. **Sair**: Encerra o programa.

## Como usar

1. Abra o arquivo `BankingExpensesManagement.java` em um ambiente Java 21.
2. Execute o programa.
3. Siga as instruções do menu para registrar depósitos, transações e visualizar extratos e resumos mensais.

## Observações

- Valores devem ser positivos e podem ser informados com ponto ou vírgula como separador decimal.
- Ao registrar uma saída (débito), o programa verifica se há saldo suficiente antes de concluir a transação.