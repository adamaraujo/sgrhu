import React from 'react';
import './App.css';

import logo from './assets/logo.png';

import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faUser, faSignInAlt, faLock } from '@fortawesome/free-solid-svg-icons'

library.add(faUser, faSignInAlt, faLock)

function App() {
  return (
    <div className="container">
      <img src={logo} alt="SGRHU"/>

      <div className="content">
        <h1>
          Seja bem-vindo!
        </h1>
        <p>
          Para ter acesso ao sistema, entre com suas credenciais abaixo
        </p>

        <form>
          <div className="input-icon">
            <div className="icon"><FontAwesomeIcon icon="user" /></div>
            <input 
              type="email" 
              id="email" 
              placeholder="Digite o seu login"
            />
          </div>
          <div className="input-icon">
            <div className="icon"><FontAwesomeIcon icon="lock" /></div>
            <input 
              type="password" 
              id="password" 
              placeholder="Digite a sua senha"
            />
          </div>

          <button className="btn" type="submit">
            <FontAwesomeIcon icon="sign-in-alt" />  ENTRAR
          </button>
        </form>
      </div>
    </div>
  );
}

export default App;
