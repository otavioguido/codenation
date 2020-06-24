package challenge;

import java.util.Objects;

import static challenge.Utils.*;

public class Carro {

    private final Motorista motorista;

    private final String placa;

    private final Cor cor;

    private Carro(Motorista motorista, String placa, Cor cor) {
        this.motorista = motorista;
        this.placa = placa;
        this.cor = cor;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public String getPlaca() {
        return placa;
    }

    public Cor getCor() {
        return cor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Carro carro = (Carro) o;
        return Objects.equals(motorista, carro.motorista) &&
                Objects.equals(placa, carro.placa) &&
                cor == carro.cor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(motorista, placa, cor);
    }

    @Override
    public String toString() {
        return "Carro{" +
                "motorista=" + motorista +
                ", placa='" + placa + '\'' +
                ", cor=" + cor +
                '}';
    }

    public static CarroBuilder builder() {
        return new CarroBuilder();
    }


    public static class CarroBuilder {

        private Motorista motorista;

        private String placa;

        private Cor cor;

        private CarroBuilder() {
        }

        public CarroBuilder withMotorista(Motorista motorista) {
            if (motorista.getIdade() < 18){
                throw new EstacionamentoException(UNDER_AGE_DRIVER + motorista.getIdade());
            }
            this.motorista = motorista;
            return this;
        }

        public CarroBuilder withPlaca(String placa) {
            if (placa.isEmpty()){
                throw new IllegalArgumentException(INVALID_COLOR + placa);
            }
            this.placa = placa;
            return this;
        }

        public CarroBuilder withCor(Cor cor) {
            if (!validColor(cor)){
                throw new IllegalArgumentException(INVALID_COLOR + cor);
            }
            this.cor = cor;
            return this;
        }

        public Carro build() {
            if (placa == null){
                throw new NullPointerException(INVALID_PLATE + placa);
            }else if (cor == null){
                throw new NullPointerException(INVALID_COLOR + cor);
            }else if (motorista == null){
                throw new EstacionamentoException(NO_DRIVER + motorista);
            }
            return new Carro(motorista, placa, cor);
        }
    }
}
