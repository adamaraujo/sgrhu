package backend.gerarticket;
import java.util.*;
public class AutorizacaoTicket{
    protected String nomeAutorizado;
    protected String cpfAutorizado;
    protected Date dataFim;
    protected Date dataInicio;
    protected Timer horaInicio;
    //protected int operacao;
    public AutorizacaoTicket(String nomeAutorizado, String cpfAutorizado, Date dataFim, Date dataInicio, Timer horaInicio){
        this.nomeAutorizado=nomeAutorizado;
        this.cpfAutorizado=cpfAutorizado;
        this.dataFim=dataFim;
        this.dataInicio=dataInicio;
        this.horaInicio=horaInicio;
    }
    public void setNomeAutorizado(String nomeAutorizado){
        this.nomeAutorizado=nomeAutorizado;
    }
    public String getNomeAutorizado(){
        return this.nomeAutorizado;
    }
    public void setCpfAutorizado(String cpfAutorizado){
        this.cpfAutorizado=cpfAutorizado;
    }
    public String getCpfAutorizado(){
        return this.cpfAutorizado;
    }
    public void setDataFim(Date dataFim){
        this.dataFim=dataFim;
    }
    public Date getDataFim(){
        return this.dataFim;
    }
    public void setDataInicio(Date dataInicio){
        this.dataInicio=dataInicio;
    }
    public Date getDataInicio(){
        return this.dataInicio;
    }
    public void setHoraInicio(Timer horaInicio){
        this.horaInicio=horaInicio;
    }
    public Timer getHoraInicio(){
        return this.horaInicio;
    }


}