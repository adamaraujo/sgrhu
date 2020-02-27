import React from 'react';
import './App.css';

import logo from './assets/logo.png';

function App() {
  return (
    <div className="container">
      <img src={logo} alt="SGRHU"/>

      <div className="content">
        <h1>
          SEJA BEM-VINDO (A)!
        </h1>
        <p>
          Para ter acesso ao sistema, entre com suas credenciais abaixo
        </p>

        <form>
          <input 
            type="email" 
            id="email" 
            placeholder="Digite o seu login"
          />
          <input 
            type="password" 
            id="password" 
            placeholder="Digite a sua senha"
          />

          <button className="btn" type="submit">ENTRAR</button>
        </form>
      </div>
    </div>
  );
}

export default App;
