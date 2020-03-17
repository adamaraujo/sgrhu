package backend.gerarticket.ticket;
import java.util.*;

public class TAvulso extends Ticket{
    protected int tipoTicket;
    public TAvulso(Date dataTicket, Timer horaTicket, int tipoTicket){
        super(dataTicket, horaTicket);
        this.tipoTicket=tipoTicket;
    }
    public void setTipoTicket(int tipoTicket){
        this.tipoTicket=tipoTicket;
    }
}