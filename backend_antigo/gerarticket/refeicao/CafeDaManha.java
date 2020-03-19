package backend.gerarticket.refeicao;
import java.util.*;

public class CafeDaManha extends Refeicao{
    protected float valorTotal;
    protected float valorParcial;
    public CafeDaManha(Timer horaRefeicao, float valorTotal, float valorParcial){
        super(horaRefeicao);
        this.valorTotal=valorTotal;
        this.valorParcial=valorParcial;
    }
}