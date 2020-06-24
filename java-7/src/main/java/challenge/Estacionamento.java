package challenge;

import java.util.ArrayList;
import java.util.List;

import static challenge.Utils.*;

public class Estacionamento {
    private int maxSpots = 10;
    private List<Carro> parked = new ArrayList<>();


    public void estacionar(Carro carro) {
        if (carro.getMotorista().getPontos() > 21){
            throw new EstacionamentoException(INVALID_LICENSE + carro.getMotorista().getPontos());
        }
        if (carrosEstacionados() < maxSpots){
            parked.add(carro);
        }else {
            for (int i = 0; i < carrosEstacionados(); i++){
                if (parked.get(i).getMotorista().getIdade() < 55){
                    parked.remove(i);
                    parked.add(carro);
                    return;
                }
            }
            throw new EstacionamentoException(PARKING_FULL);
        }
    }

    public int carrosEstacionados() {
        return parked.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return parked.contains(carro);
    }
}
