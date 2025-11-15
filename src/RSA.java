import java.math.BigInteger;
import java.security.SecureRandom;

/** Implementação simples de RSA: gera chaves e cifra/decifra inteiros. */
public class RSA {
    private BigInteger p, q, n, phi, e, d;
    private int bitLength;
    private SecureRandom random;

    /**
     * Construtor para a classe RSA.
     * Gera um novo par de chaves RSA com um tamanho de bit especificado.
     *
     * @param bitLength O tamanho em bits dos números primos p e q.
     */
    public RSA(int bitLength) {
        this.bitLength = bitLength;
        this.random = new SecureRandom();
        generateKeys();
    }

    /** Gera p, q, n, phi, e e d (chaves RSA). */
    private void generateKeys() {
        // Gera primos p e q (~bitLength/2 bits)
        p = BigInteger.probablePrime(bitLength / 2, random);
        q = BigInteger.probablePrime(bitLength / 2, random);
        // Garante que p e q sejam diferentes.
        while (q.equals(p)) {
            q = BigInteger.probablePrime(bitLength / 2, random);
        }

        // n = p * q
        n = p.multiply(q);

        // phi = (p-1)*(q-1)
        phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Expoente público padrão: 65537 (fallback para aleatório se necessário)
        e = BigInteger.valueOf(65537);
        // Se 65537 não for coprimo de phi (caso raro), escolhe um expoente público
        // aleatório que satisfaça gcd(e, phi) = 1.
        if (phi.gcd(e).compareTo(BigInteger.ONE) != 0) {
            e = BigInteger.probablePrime(bitLength / 2, random);
            while (phi.gcd(e).compareTo(BigInteger.ONE) != 0) {
                e = BigInteger.probablePrime(bitLength / 2, random);
            }
        }

        // d = e^(-1) mod phi
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
