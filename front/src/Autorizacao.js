import React from 'react';
import ReactDOM from 'react-dom';
import './global.css';
import './App.css';
import './Saidebar.css';
import './header.css';
import './Autorizacao.css';
import Relatorio from './Relatorio.js';
import App from './App.js';
import Ticket from './Ticket.js';


const strangerName = 'Você já está nessa página!';
function Autorizacao() {
    return(
        <div id="autorizacao">
        <header>
          <strong>Olá!</strong>
        </header>
        <aside>
          <strong>SGRHU</strong>
          <div className = "Menu">
          <button onClick={() => ReactDOM.render(
                              <React.StrictMode>
                                <App />
                              </React.StrictMode>,
                              document.getElementById('root'))}>
            INÍCIO</button>
          <button onClick={() => ReactDOM.render(
                              <React.StrictMode>
                                <Ticket />
                              </React.StrictMode>,
                              document.getElementById('root'))}>
            TICKET</button>
          <button onClick={ () => ReactDOM.render(
                              <React.StrictMode>
                                <Relatorio />
                              </React.StrictMode>,
                              document.getElementById('root'))}>
            RELATÓRIOS</button>
          <button onClick={() => alert(strangerName)}>
            AUTORIZAÇÕES</button>
          </div>
          <div>
            <button onClick={() => alert(strangerName)}>
              SAIR</button>
          </div>
        </aside>
        <main>
          <div id="divEnviar">
            <input type = "text" id="nome" placeholder= "Nome"/>
            <input type = "text" id="cpf" placeholder= "CPF"/>
            <input type = "text" id="perfil" placeholder= "Perfil"/>
            <input type = "text" id="dinicio" placeholder= "Data de inicio: d/m/a"/>
            <input type = "text" id="dfim" placeholder= "Data de fim: d/m/a"/>
            <input type = "text" id="hinicio" placeholder= "Hora de inicio: h:m"/>
            <input type = "text" id="hfim" placeholder= "Hora de fim: h:m"/>
            <button id="btnEnviar">Enviar</button>
          </div>
        </main>
      </div>
    );
}
export default Autorizacao;