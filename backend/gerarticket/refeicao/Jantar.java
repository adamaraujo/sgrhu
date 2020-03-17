package backend.gerarticket.refeicao;
import java.util.*;

public class Jantar extends Refeicao{
    protected float valorTotal;
    protected float valorParcial;
    public Jantar(Timer horaRefeicao, float valorTotal, float valorParcial){
        super(horaRefeicao);
        this.valorTotal=valorTotal;
        this.valorParcial=valorParcial;
    }
}