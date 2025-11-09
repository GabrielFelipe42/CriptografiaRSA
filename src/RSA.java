import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Implementa o algoritmo de criptografia RSA.
 * Esta classe é responsável pela geração das chaves pública e privada,
 * bem como pela criptografia e descriptografia de mensagens.
 */
public class RSA {
    private BigInteger p, q, n, phi, e, d;
    private int bitLength;
    private SecureRandom random;

    /**
     * Construtor para a classe RSA.
     * Gera um novo par de chaves RSA com um tamanho de bit especificado.
     *
     * @param bitLength O tamanho em bits dos números primos p e q. Recomenda-se 1024 ou 2048 para segurança.
     */
    public RSA(int bitLength) {
        this.bitLength = bitLength;
        this.random = new SecureRandom();
        generateKeys();
    }

    /**
     * Gera o par de chaves pública e privada RSA.
     * O processo envolve:
     * 1. Escolha de dois números primos grandes e diferentes, p e q.
     * 2. Cálculo de n (o módulo): n = p * q.
     * 3. Cálculo de phi(n) (a função totiente de Euler): phi(n) = (p-1) * (q-1).
     * 4. Escolha do expoente público e (1 < e < phi(n) e e é coprimo de phi(n)).
     * 5. Cálculo do expoente privado d (d * e ≡ 1 (mod phi(n))).
     */
    private void generateKeys() {
        // 1. Escolha dois números primos grandes e diferentes, p e q
        // O tamanho de cada primo é aproximadamente metade do bitLength total.
        p = BigInteger.probablePrime(bitLength / 2, random);
        q = BigInteger.probablePrime(bitLength / 2, random);
        // Garante que p e q sejam diferentes.
        while (q.equals(p)) {
            q = BigInteger.probablePrime(bitLength / 2, random);
        }

        // 2. Calcule n (o módulo): n = p * q
        // n é o módulo para as chaves pública e privada.
        n = p.multiply(q);

        // 3. Calcule phi(n) (a função totiente de Euler): phi(n) = (p-1) * (q-1)
        // phi(n) é usado para calcular d, o inverso modular de e.
        phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // 4. Escolha o expoente público e
        // e deve ser 1 < e < phi(n) e ser coprimo de phi(n).
        // BigInteger.probablePrime é usado para encontrar um primo que sirva como e.
        e = BigInteger.probablePrime(bitLength / 2, random);
        while (phi.gcd(e).compareTo(BigInteger.ONE) != 0) {
            e = BigInteger.probablePrime(bitLength / 2, random);
        }

        // 5. Calcule o expoente privado d
        // d é o inverso modular de e modulo phi(n).
        // Isso significa que (d * e) % phi(n) = 1.
        d = e.modInverse(phi);
    }

    /**
     * Retorna o módulo n (parte da chave pública e privada).
     *
     * @return O BigInteger n.
     */
    public BigInteger getN() {
        return n;
    }

    /**
     * Retorna o expoente público e (parte da chave pública).
     *
     * @return O BigInteger e.
     */
    public BigInteger getE() {
        return e;
    }

    /**
     * Retorna o expoente privado d (parte da chave privada).
     *
     * @return O BigInteger d.
     */
    public BigInteger getD() {
        return d;
    }

    /**
     * Criptografa uma mensagem usando a chave pública (e, n).
     *
     * @param message A mensagem (como BigInteger) a ser criptografada.
     * @return A mensagem criptografada (BigInteger).
     */
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    /**
     * Descriptografa uma mensagem usando a chave privada (d, n).
     *
     * @param encryptedMessage A mensagem criptografada (como BigInteger) a ser descriptografada.
     * @return A mensagem descriptografada (BigInteger).
     */
    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(d, n);
    }
}
