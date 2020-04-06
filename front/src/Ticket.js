import React, { useEffect, useState } from 'react';
import ReactDOM from 'react-dom';
import api from './services/api'
import './global.css';
import './App.css';
import './Saidebar.css';
import './header.css';
import './Ticket.css';
import Autorizacao from './Autorizacao.js';
import Relatorio from './Relatorio.js';
import App from './App.js';


const strangerName = 'Você já está na página Ticket!';
function Ticket() {

  const [cpf_beneficiado, setCpfBeneficiado] = useState('');
  const [benef, setBenef] = useState([]);
  useEffect(() =>{
    async function loadBenef(){
      const response = await api.get('benef')
      setBenef(response.data);
    }
    loadBenef();
  }, []);
  async function handleBuscarBene(e){
    e.preventDefault();
    const response = await api.get('/tickets/consulta/paciente'+cpf_beneficiado)
    setCpfBeneficiado('');
  }
    return(
        <div id="ticket">
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
          <button onClick={() => alert(strangerName)}>
            TICKET</button>
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
                              document.getElementById('root'))}>
            AUTORIZAÇÕES</button>
          </div>
          <div>
            <button onClick={() => alert(strangerName)}>
              SAIR</button>
          </div>
        </aside>
        <main>
          <div id="divBusca">
            <input type = "text" id="txtBusca" placeholder= "Digite o CPF do beneficiário" 
              required value={cpf_beneficiado} onChange={e => setCpfBeneficiado(e.target.value)}/>
            <button id="btnBusca" type="submit">Buscar</button>
          </div>
          <ul>
            {benef.map(dev =>(
              <li key={dev._id} id="beneficiado">
              <strong>{benef.name}</strong>
            <a>{benef.cpf}</a>
              <a>{benef.perfil}</a>
              <a>{benef.refeicao}</a>
              <button id="btngerarticket">Gerar Ticket</button>
            </li>
            ))}
          </ul>
        </main>
      </div>
    );
}
export default Ticket;