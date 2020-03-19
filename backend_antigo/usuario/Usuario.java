package backend.usuario;

public class Usuario {
    protected String cpfUsuario;
    protected String nomeUsuario;
    protected int nivelAcesso;
    private String login;
    private String senha;
    //protected int operacao;

    public Usuario(String cpfUsuario, String nomeUsuario, int nivelAcesso, String login, String senha){
        this.cpfUsuario=cpfUsuario;
        this.nomeUsuario=nomeUsuario;
        this.nivelAcesso=nivelAcesso;
        this.login=login;
        this.senha=senha;  
    }
    public void setCpfUsuario(String cpfUsuario){
        this.cpfUsuario=cpfUsuario;
    }
    public String getCpfUsuario(){
        return this.cpfUsuario;
    }
    public void setNomeUsuario(String nomeUsuario){
        this.nomeUsuario=nomeUsuario;
    }
    public String getNomeUsuario(){
        return this.nomeUsuario;
    }
    public void setNivelAcesso(int nivelAcesso){
        this.nivelAcesso=nivelAcesso;
    }
    public int getNivelAcesso(){
        return this.nivelAcesso;
    }
    public void setLogin(String login){
        this.login=login;
    }
    public String getLogin(){
        return this.login;
    }
    public void setSenha(String senha){
        this.senha=senha;
    }
    public String getSenha(){
        return this.senha;
    }
    public void controlarUsuario(String login, String senha){
        //Rever se realmente é nessa classe
    }
    public Usuario buscarUsuario(Usuario u){
        //Rever se realmente é nessa classe
        return u;
    }

}