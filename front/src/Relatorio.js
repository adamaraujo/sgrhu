import React from 'react';
import ReactDOM from 'react-dom';
import './global.css';
import './App.css';
import './Saidebar.css';
import './header.css';
import './Relatorio.css';
import Autorizacao from './Autorizacao';
import App from './App.js';
import Ticket from './Ticket.js';


const strangerName = 'Você já está na página Relatório!';
function Relatorio() {
    return(
        <div id="relatorio">
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
          <button onClick={ () => alert(strangerName)}>
            RELATÓRIOS</button>
          <button onClick={() => ReactDOM.render(
                              <React.StrictMode>
                                <Autorizacao />
                              </React.StrictMode>,
                              document.getElementById('root'))}>
            AUTORIZAÇÕES</button>
          </div>
          <div>
            <button onClick={() => alert(strangerName)}>
              SAIR</button>
          </div>
        </aside>
        <main>
            <button id="periodo" onClick={() => alert(strangerName)}>
                Período</button>
            <button id="consumidor" onClick={() => alert(strangerName)}>
                Perfil de consumidor</button>
            <button id="refeicao" onClick={ () => alert(strangerName)}>
                Tipo de Refeição</button>
            <button id="ticketss" onClick={() => alert(strangerName)}>
                Tipo de ticket</button>
            <button id="pdf" onClick={() => alert(strangerName)}>
                download</button>
            <ul id="refe">
                <strong id="relat">Refeições distribuidas até hoje</strong>
            </ul>
            <ul id="avulsa">
                <strong id ="relat">Refeições avulsas vendidas até hoje</strong>
            </ul>
            <ul id="cafe">
                <strong id ="relat">Cafés-da-manhã distribuidos até hoje</strong>
            </ul>
            <ul id="almoco">
                <strong id ="relat">Almoços distribuidos até hoje</strong>
            </ul>
            <ul id="jantar">
                <strong id ="relat">Jantares distribuidos até hoje</strong>
            </ul>
        </main>
      </div>
    );
}
export default Relatorio;