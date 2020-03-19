package backend.gerarticket.ticket;
import java.util.*;

public class Ticket{
    public Date dataTicket;
    public Timer horaTicket;
    
    public Ticket(Date dataTicket, Timer horaTicket){
        this.dataTicket=dataTicket;
        this.horaTicket=horaTicket;
    }
    public void setDateTicket(Date dataTicket){
        this.dataTicket=dataTicket;
    }
    public Date getDataTicket(){
        return this.dataTicket;
    }
    public void setHoraTicket(Timer horaTicket){
        this.horaTicket=horaTicket;
    }
    public Timer getHoraTicket(){
        return this.horaTicket;
    }
    //public boolean situacaoTicket(){}
    //public int filtrarTicket(listaTicket, listaFiltro){}
    //NÃO É NESSA CLASSE//
    //public void inserirTicket(ticket, refeiccao, cliente){}
    //public Ticket listarTicket(){}

}