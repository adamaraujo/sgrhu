package backend.gerarticket.refeicao;
import java.util.*;

public class Refeicao{
    protected Timer horaRefeicao;
    public Refeicao(Timer horaRefeicao){
        this.horaRefeicao=horaRefeicao;
    }
    public void setHoraRefeicao(Timer horaRefeicao){
        this.horaRefeicao=horaRefeicao;
    }
    public Timer getHoraRefeicao(){
        return this.horaRefeicao;
    }
}