import React from 'react';
import './App.css';

import styled from 'styled-components';
import {User, LockAlt} from 'styled-icons/boxicons-solid';
import {SignInAlt} from 'styled-icons/fa-solid/SignInAlt';

import logo from './assets/logo.png';

function App() {
  return (
    <div className="container">
      <img src={logo} alt="SGRHU"/>

      <div className="content">
        <h1>
          Olá, bem-vindo de volta!
        </h1>
        <p>
          Para ter acesso ao sistema, entre com suas <strong>credenciais</strong> abaixo
        </p>

        <form>
          <div className="input-icon">
            <input 
              type="email" 
              id="email" 
              placeholder="Digite o seu login"
            />
            <i><User size="18"/></i>
          </div>
          <div className="input-icon">
            <input 
              type="password" 
              id="password" 
              placeholder="Digite a sua senha"
            />
            <i><LockAlt size="18"/></i>
          </div>

          <button className="btn" type="submit">
            <SignInAlt size="18"/>  ENTRAR
          </button>
        </form>
      </div>
    </div>
  );
}

export default App;
