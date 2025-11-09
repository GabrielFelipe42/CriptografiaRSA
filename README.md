# Criptografia Assimétrica RSA em Java

Este projeto implementa o algoritmo de criptografia e descriptografia de mensagens utilizando o algoritmo RSA, conforme solicitado para o trabalho final da disciplina de Segurança da Informação da UDESC.

## Funcionalidades

- Geração de par de chaves RSA (pública e privada) com tamanho de 1024 bits.
- Conversão de mensagens de texto (String) para representação numérica (BigInteger) utilizando UTF-8.
- Criptografia de mensagens usando a chave pública (n, e).
- Descriptografia de mensagens usando a chave privada (n, d).
- Conversão de mensagens descriptografadas (BigInteger) de volta para texto (String).
- Exibição clara de todas as etapas: mensagem original, chaves geradas, mensagem criptografada e mensagem descriptografada.

## Requisitos

- Java Development Kit (JDK) versão 17 ou superior (preferencialmente uma versão LTS como OpenJDK 17 ou 21).

## Como Executar

Para compilar e executar o projeto, siga os passos abaixo:

1.  **Navegue até o diretório raiz do projeto:**
    ```bash
    cd CriptografiaRSA
    ```

2.  **Compile os arquivos Java:**
    ```bash
    javac src/App.java src/RSA.java
    ```

3.  **Execute a aplicação:**
    ```bash
    java -cp src App
    ```

    A saída no console mostrará o processo de criptografia e descriptografia para as mensagens de teste "abc" e "teste", exibindo a mensagem original, as chaves, a mensagem criptografada e a mensagem descriptografada.

## Estrutura do Projeto

-   `src/App.java`: Contém o ponto de entrada da aplicação (`main` method) e a lógica para executar os testes, converter strings e exibir os resultados. Também inclui o método para converter `BigInteger` de volta para `String`.
-   `src/RSA.java`: Implementa o algoritmo RSA, incluindo a geração das chaves (p, q, n, phi, e, d), e os métodos para criptografar (`encrypt`) e descriptografar (`decrypt`) mensagens.
