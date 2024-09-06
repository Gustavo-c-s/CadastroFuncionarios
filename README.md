# CadastroFuncionarios
Recriando o trabalho "Departamento-Pessoal q era em portugal" agora esta em java

# Sistema de Gestão de Funcionários

Este projeto é um sistema simples de gestão de funcionários, que permite cadastrar, editar, registrar o ponto e gerar uma folha de pagamento. O sistema foi desenvolvido em Java, utilizando listas para armazenar os funcionários e os dados de ponto.

## Funcionalidades

1. **Cadastro de Funcionário**: Permite cadastrar um novo funcionário, solicitando nome, CPF e salário. O funcionário é armazenado em uma lista de funcionários.

2. **Edição de Funcionário**: Permite editar os dados de um funcionário cadastrado. O usuário pode alterar o nome, CPF ou salário de um funcionário, pesquisando pelo CPF.

3. **Registro de Ponto**: O usuário pode registrar a hora de entrada e saída de um funcionário, com base no CPF. As horas são inseridas no formato `HH:mm` e armazenadas no objeto funcionário.

4. **Folha de Pagamento**: Calcula e exibe a folha de pagamento dos funcionários, considerando os descontos de INSS e IRRF. O sistema exibe o salário bruto, os descontos e o salário líquido para cada funcionário.

5. **Listagem de Funcionários**: Exibe uma lista de todos os funcionários cadastrados, mostrando seus códigos, nomes, CPFs e salários.

6. **Sair**: Encerra o sistema.

## Estrutura

- O sistema utiliza uma classe principal chamada `Primaria`, que contém o método `main` e o menu principal.
- Os dados dos funcionários são armazenados em uma lista de objetos da classe `Funcionario`.
- As operações de cálculo de INSS e IRRF são realizadas através de métodos específicos, com base nas regras tributárias para cada faixa salarial.

### Classes Principais

#### Primaria.java

- **menu()**: Exibe o menu principal e chama as funções de acordo com a opção selecionada pelo usuário.
- **cadastraFuncionario()**: Permite cadastrar um novo funcionário e armazená-lo na lista de funcionários.
- **editarFuncionario()**: Localiza um funcionário pelo CPF e permite editar seus dados.
- **registraPonto()**: Registra a hora de entrada e saída de um funcionário utilizando o formato `HH:mm`.
- **folhaPagamento()**: Calcula e exibe a folha de pagamento com base no salário bruto, descontos de INSS e IRRF, e o salário líquido.
- **listaFuncionario()**: Exibe uma lista de todos os funcionários cadastrados.

#### Funcionario.java (suposição)

A classe `Funcionario` deve conter os atributos e métodos para representar os dados de um funcionário, tais como:
- **Atributos**: `nome`, `cpf`, `salario`, `hrEntrada`, `hrSaida`, etc.
- **Métodos**: Métodos getter e setter para acessar e modificar os dados do funcionário.

### Métodos Auxiliares

- **procuraCpf()**: Busca um funcionário na lista com base no CPF.
- **calcularINSS()**: Calcula o desconto do INSS com base no salário bruto.
- **calcularIRRF()**: Calcula o desconto do IRRF com base no salário após o desconto do INSS.

## Exemplo de Uso

```plaintext
**************************
     Bem Vindo ao DpPs
1 - Cadastra Funcionario.
2 - Editar Funcionaro.
3 - Registra Ponto.
4 - Folha de Pagamento.
5 - Lista de Funcionaio.
6 - Sair
**************************
Escolha uma opção: 1
Digite o nome do funcionario: João Silva
Digite o CPF do funcionario: 12345678900
Digite o salario do funcionario: 5000.00
Deseja continuar cadastrando? 1 - sim / 2 - retornar



Esse `README.md` fornece uma visão geral sobre o que o código faz, suas funcionalidades, como usá-lo e como ele pode ser melhorado no futuro.
