import java.math.BigInteger;

/**
 * Classe principal que demonstra a criptografia e descriptografia RSA.
 */
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("==================================================");
        System.out.println("               DEMONSTRAÇÃO RSA                 ");
        System.out.println("==================================================");

        System.out.println("\n--- Teste 1: Mensagem 'abc' ---");
        System.out.println("--------------------------------------------------");
        runRSA("abc");

        System.out.println("\n--- Teste 2: Mensagem 'teste' ---");
        System.out.println("--------------------------------------------------");
        runRSA("teste");

        System.out.println("\n--- Teste 3: Mensagem 'gabriel' ---");
        System.out.println("--------------------------------------------------");
        runRSA("gabriel");

        System.out.println("\n==================================================");
        System.out.println("             FIM DA DEMONSTRAÇÃO                ");
        System.out.println("==================================================");
    }

    /**
     * Executa o processo completo de RSA para uma dada mensagem.
     *
     * @param mensagemOriginal A mensagem de texto claro a ser criptografada e descriptografada.
     * @throws Exception Se ocorrer um erro durante o processo de criptografia/descriptografia.
     */
    public static void runRSA(String mensagemOriginal) throws Exception {
        System.out.println("\nMensagem Original (texto claro): \u001b[1m" + mensagemOriginal + "\u001b[0m"); // Bold

        // Converte a mensagem original de string para BigInteger usando UTF-8.
        // Isso é necessário porque o algoritmo RSA opera em números, não em texto.
        BigInteger mensagemNumerica = new BigInteger(mensagemOriginal.getBytes("UTF-8"));

        System.out.println("\n--- Geração de Chaves RSA ---");
        // Inicializa o objeto RSA, que gera automaticamente as chaves pública e privada.
        // O parâmetro 1024 define o tamanho em bits dos números primos usados para gerar as chaves,
        // influenciando a segurança do algoritmo.
        RSA rsa = new RSA(1024);
        System.out.println("  Chave Pública:");
        System.out.println("    n: " + rsa.getN());
        System.out.println("    e: " + rsa.getE());
        System.out.println("  Chave Privada:");
        System.out.println("    n: " + rsa.getN());
        System.out.println("    d: " + rsa.getD());

        // Criptografa a mensagem numérica usando a chave pública (e, n).
        // A operação é: C = M^e mod n
        BigInteger mensagemCriptografada = rsa.encrypt(mensagemNumerica);
        System.out.println("\nMensagem Criptografada (valores inteiros):");
        System.out.println("  " + mensagemCriptografada);

        // Descriptografa a mensagem criptografada usando a chave privada (d, n).
        // A operação é: M = C^d mod n
        BigInteger mensagemDescriptografadaNumerica = rsa.decrypt(mensagemCriptografada);

        // Converte o BigInteger descriptografado de volta para uma string UTF-8.
        String mensagemDescriptografada = bigIntegerToString(mensagemDescriptografadaNumerica);
        System.out.println("\nMensagem Descriptografada (texto claro): \u001b[1m" + mensagemDescriptografada + "\u001b[0m"); // Bold
        System.out.println("--------------------------------------------------");
    }

    /**
     * Converte um BigInteger de volta para uma String usando a codificação UTF-8.
     *
     * @param bigInteger O BigInteger a ser convertido.
     * @return A String resultante da conversão.
     * @throws Exception Se ocorrer um erro de codificação.
     */
    public static String bigIntegerToString(BigInteger bigInteger) throws Exception {
        return new String(bigInteger.toByteArray(), "UTF-8");
    }
}
