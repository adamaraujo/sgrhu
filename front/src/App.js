import React from 'react';
import ReactDOM from 'react-dom';

import './global.css';
import './App.css';
import './Saidebar.css';
import './header.css';
import './Ticket.css';
import Relatorio from './Relatorio.js';
import Autorizacao from './Autorizacao.js';
import Ticket from './Ticket.js';

const strangerName = 'Você já está na página Inicio!';
function App() {

  return (
    <div id="app">
      <header>
        <strong>Olá!</strong>
      </header>
      <aside>
        <strong>SGRHU</strong>
        <div className = "Menu">
        <button onClick={() => alert(strangerName)}>
          INÍCIO</button>
        <button onClick={() =>ReactDOM.render(
                              <React.StrictMode>
                                <Ticket />
                              </React.StrictMode>,
                              document.getElementById('root')
        )}>TICKET</button>
        <button onClick={() => ReactDOM.render(
                              <React.StrictMode>
                                <Relatorio />
                              </React.StrictMode>,
                              document.getElementById('root')
        )}>RELATÓRIOS</button>
        <button onClick={() => ReactDOM.render(
                              <React.StrictMode>
                                <Autorizacao />
                              </React.StrictMode>,
                              document.getElementById('root')
        )}>AUTORIZAÇÕES</button>
        </div>
        <div>
          <button onClick={() => alert(strangerName)}>
            SAIR</button>
        </div>
      </aside>
      <main>
      </main>
    </div>
  );
}

export default App;
