package backend.relatorio;
import java.util.*;

public class Estimativa{
   // protected List Cliente 
   protected Date dataEstimativa;
   protected Timer horaEstimativa;

   public Estimativa(Date dataEstimativa, Timer horaEstimativa){
       this.dataEstimativa=dataEstimativa;
       this.horaEstimativa=horaEstimativa;
   }
   public void setDataEstimativa(Date dataEstimativa){
       this.dataEstimativa=dataEstimativa;
   }
   public Date getDataTicket(){
       return this.dataEstimativa;
   }
   public void setHoraEstimativa(Timer horaEstimativa){
       this.horaEstimativa=horaEstimativa;
   }
   public Timer getHoraEstimativa(Timer dataEstimativa){
       return this.horaEstimativa;
   }
   //public int quantRefeicao(Cliente listCliente){}
   public void InserirEstimativa(){
       //Rever se realmente é nessa classe
   }

}