import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/** Classe principal: demonstra criptografia/descriptografia RSA. */
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

    /** Processa uma mensagem: converte, gera chaves, encripta e decripta. */
    public static void runRSA(String mensagemOriginal) throws Exception {
        System.out.println("\nMensagem Original (texto claro): \u001b[1m" + mensagemOriginal + "\u001b[0m");

        // Converte string -> BigInteger (ASCII, sinal positivo)
        BigInteger mensagemNumerica = new BigInteger(1, mensagemOriginal.getBytes(StandardCharsets.US_ASCII));

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

        // C = M^e mod n
        BigInteger mensagemCriptografada = rsa.encrypt(mensagemNumerica);
        System.out.println("\nMensagem Criptografada (valores inteiros):");
        System.out.println("  " + mensagemCriptografada);

        // M = C^d mod n
        BigInteger mensagemDescriptografadaNumerica = rsa.decrypt(mensagemCriptografada);

        // Converte o BigInteger descriptografado de volta para uma string UTF-8.
        String mensagemDescriptografada = bigIntegerToString(mensagemDescriptografadaNumerica);
        System.out.println("\nMensagem Descriptografada (texto claro): \u001b[1m" + mensagemDescriptografada + "\u001b[0m");
        System.out.println("--------------------------------------------------");
    }

    /** Converte BigInteger -> String (remove possível byte de sinal). */
    public static String bigIntegerToString(BigInteger bigInteger) throws Exception {
        byte[] bytes = bigInteger.toByteArray();
        if (bytes.length > 1 && bytes[0] == 0) {
            bytes = Arrays.copyOfRange(bytes, 1, bytes.length);
        }
        return new String(bytes, StandardCharsets.US_ASCII);
    }
}
