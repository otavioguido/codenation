package challenge;

public class CriptografiaCesariana implements Criptografia {

    // valor decimal na tabela ascii para o caracter 'a'
    private static final int A_CHAR = 97;
    // valor decimal na tabela ascii para o caracter 'z'
    private static final int Z_CHAR = 122;
    // o tamanho do alfabeto + 1 para quando algum caracter tiver algum valor superior a 'z' ou inferior a 'a'
    private static final int ALPHABET_SIZE = 26;
    // fator de criptografia
    private static final int CRYPTO_FACTOR = 3;

    @Override
    public String criptografar(String texto) {
        if (texto.isEmpty()){
            throw new IllegalArgumentException();

        }else {
            char[] charArray = texto.toLowerCase().toCharArray();

            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] >= A_CHAR && charArray[i] <= Z_CHAR) {
                    int aux = ((int) charArray[i]) + CRYPTO_FACTOR;

                    if (aux > Z_CHAR) {
                        aux -= ALPHABET_SIZE;
                    }

                    charArray[i] = (char) aux;
                }
            }
            return new String(charArray);
        }
    }

    @Override
    public String descriptografar(String texto) {

        if (texto.isEmpty()){
            throw new IllegalArgumentException();

        }else {
            char[] charArray = texto.toLowerCase().toCharArray();

            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] >= A_CHAR && charArray[i] <= Z_CHAR) {
                    int aux = ((int) charArray[i]) - CRYPTO_FACTOR;

                    if (aux < A_CHAR) {
                        aux += ALPHABET_SIZE;
                    }

                    charArray[i] = (char) aux;
                }
            }
            return new String(charArray);
        }
    }
}
