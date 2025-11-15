# Criptografia Assimétrica RSA em Java

Implementação didática do algoritmo RSA para o trabalho da disciplina de Segurança da Informação (UDESC).

## Funcionalidades

- Geração de par de chaves RSA (pública e privada) com tamanho padrão 1024 bits.
- Conversão de mensagens de texto (`String`) para `BigInteger` usando ASCII e `new BigInteger(1, bytes)` (assegura valor positivo).
- Uso do expoente público padrão `e = 65537` (com fallback se necessário).
- Criptografia (`C = M^e mod n`) e descriptografia (`M = C^d mod n`).
- Exibição: mensagem original, mensagem criptografada (inteiros), chave pública (`n`,`e`), chave privada (`n`,`d`) e mensagem descriptografada.

> Observação: para maior segurança em produção, recomenda‑se usar chaves >= 2048 bits e padding OAEP (bibliotecas JCA / BouncyCastle).

## Requisitos

- Java Development Kit (JDK) versão 17 ou superior (recomendado OpenJDK 17/21).

## Como executar

1. Abra um terminal no diretório do projeto:

```powershell
cd 'c:\Users\gabri\Desktop\SEC - TF - Github\CriptografiaRSA'
```

2. Compile todos os arquivos e coloque as classes em `bin`:

```powershell
javac -d bin src\*.java
```

3. Execute a aplicação:

```powershell
java -cp bin App
```

A saída exibirá a demonstração (por padrão testamos as mensagens: `"abc"`, `"teste"` e `"gabriel"`).

## Estrutura do projeto

- `src/App.java`: classe principal — lê/define mensagens de teste, converte para `BigInteger`, gera chaves via `RSA`, encripta e decripta, e imprime os resultados.
- `src/RSA.java`: implementação didática de RSA — gera `p`, `q`, `n`, `phi`, escolhe `e`, calcula `d`, e fornece `encrypt`/`decrypt`.

## Notas finais

- O projeto está alinhado com o enunciado do trabalho (conversão ASCII → inteiro, geração de chaves, criptografia/descriptografia e reconversão para texto).
- Para submissão acadêmica, se quiser, posso ajustar o README para incluir instruções de execução alternativas ou explicar pontos de segurança (padding, tamanho de chave, não expor `d`).
