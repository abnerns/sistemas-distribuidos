# Verificação de Números Perfeitos e Amigáveis em Intervalos Grandes

Este repositório contém a implementação e análise de soluções sequencial, paralela e distribuída para o problema de verificação de números perfeitos e amigáveis em intervalos grandes. O objetivo principal é comparar o desempenho dessas diferentes abordagens e identificar os fatores que influenciam sua eficiência.

As soluções foram desenvolvidas em Java, utilizando Threads para a implementação paralela e RMI (Remote Method Invocation) para a implementação distribuída.

## 🚀 Visão Geral do Projeto

O projeto aborda a busca por números perfeitos e pares de números amigáveis em grandes intervalos, explorando três paradigmas de programação:

*   **Solução Sequencial**: Implementação tradicional, processando os números um a um.
*   **Solução Paralela**: Utiliza `Threads` em Java para dividir o trabalho e processar subintervalos simultaneamente.
*   **Solução Distribuída**: Emprega `Java RMI` para distribuir a carga de trabalho entre diferentes processos (simulando máquinas distintas).

## 🎯 Objetivo

Implementar e comparar soluções sequencial, paralela e distribuída para o problema de verificação de números perfeitos e amigáveis em intervalos grandes. A análise de desempenho dessas soluções permite identificar os fatores que influenciam sua eficiência e as situações em que cada abordagem é mais adequada.

## ✨ Conceitos Chave

### Números Perfeitos

Um número natural é **perfeito** se a soma de seus divisores naturais próprios (excluindo ele mesmo) é igual ao próprio número. Exemplo: `6` (divisores próprios: `1, 2, 3`; soma: `1+2+3=6`).

### Números Amigáveis

Dois números são **amigáveis** se cada um deles é a soma dos divisores próprios do outro. Exemplo: `(220, 284)`.

## 🛠️ Tecnologias Utilizadas

*   **Java**: Linguagem de programação.
*   **Maven**: Ferramenta de automação de build e gerenciamento de dependências.
*   **Java Threads**: Para paralelismo.
*   **Java RMI**: Para computação distribuída.

## ⚙️ Como Executar

### Execução no VS Code

Para usar o código no VS Code, siga estes passos:

1.  **Descompacte o arquivo `perfect_amicable_numbers_code.zip`** em uma pasta de sua preferência.
2.  **Abra o VS Code**.
3.  Selecione a pasta `perfect_amicable_numbers` que você descompactou.

**Para executar as diferentes soluções no VS Code:**

*   **Solução Sequencial:**
    1.  Abra o arquivo `src/main/java/com/example/perfectamicable/VerificadorNumeros.java`.
    2.  Clique no botão "Run"

*   **Solução Paralela:**
    1.  Abra o arquivo `src/main/java/com/example/perfectamicable/VerificadorNumerosParalelo.java`.
    2.  Clique no botão "Run"

*   **Solução Distribuída (RMI):**
    1.  **Primeiro, inicie o servidor RMI:**
        *   Abra o arquivo `src/main/java/com/example/perfectamicable/ServidorNumeros.java`.
        *   Clique no botão "Run"
    2.  **Em seguida, inicie o cliente RMI:**
        *   Abra o arquivo `src/main/java/com/example/perfectamicable/ClienteNumeros.java`.
        *   Clique no botão "Run"

## 📊 Resultados de Desempenho

Os testes foram realizados em um ambiente com as seguintes características:
- Sistema Operacional: Ubuntu 22.04
- Processador: 4 núcleos
- Memória RAM: 8 GB

| Tipo de Solução | Intervalo (Início, Fim) | Tempo (ms) |
|-----------------|-------------------------|------------|
| Sequencial      | (1, 1000)               | 26141      |
| Sequencial      | (1, 5000)               | 25796      |
| Sequencial      | (1, 10000)              | 26420      |
| Sequencial      | (1, 20000)              | 28885      |
| Sequencial      | (1, 30000)              | 26247      |
| Paralelo        | (1, 1000)               | 2796       |
| Paralelo        | (1, 5000)               | 2784       |
| Paralelo        | (1, 10000)              | 2794       |
| Paralelo        | (1, 20000)              | 2820       |
| Paralelo        | (1, 30000)              | 2873       |
| Distribuído     | (1, 1000)               | 131200     |
| Distribuído     | (1, 5000)               | 130562     |
| Distribuído     | (1, 10000)              | 130446     |
| Distribuído     | (1, 20000)              | 130562     |
| Distribuído     | (1, 30000)              | 130553     |


## 🤝 Contribuições

*   *Membro 1 (Introdução e Contexto do Problema)*: Pesquisa aprofundada sobre Números Perfeitos e Amigáveis, elaboração do relatório e do início dos slides.
*   *Membro 2 (Metodologia e Solução Sequencial)*: Desenvolvimento da solução sequencial, otimização do algoritmo de soma de divisores e documentação da metodologia.
*   *Membro 3 (Solução Paralela - Threads)*: Implementação da solução paralela com Threads em Java, gerenciamento de concorrência e documentação dos aspectos de paralelismo.
*   *Membro 4 (Solução Distribuída - RMI)*: Desenvolvimento da solução distribuída com RMI, configuração do ambiente cliente-servidor e documentação da arquitetura distribuída.
*   *Membro 5 (Resultados e Análise de Desempenho)*: Execução dos testes de desempenho para todas as soluções, coleta e organização dos dados, e criação dos gráficos comparativos.
*   *Membro 6 (Conclusão)*: Análise dos resultados, finalização dos slides e organização das referências do trabalho.


