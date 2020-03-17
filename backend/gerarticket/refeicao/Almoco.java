package backend.gerarticket.refeicao;
import java.util.*;

public class Almoco extends Refeicao{
    protected float valorTotal;
    protected float valorParcial;
    public Almoco(Timer horaRefeicao, float valorTotal, float valorParcial){
        super(horaRefeicao);
        this.valorTotal=valorTotal;
        this.valorParcial=valorParcial;
    }
}