import React, { useState, Component } from 'react';
import api from '../../services/api';
import './Login.css';

import {User, LockAlt} from 'styled-icons/boxicons-solid';
import {SignInAlt} from 'styled-icons/fa-solid/SignInAlt';

import logo from '../../assets/logo.png';

export default class Login extends Component {
  state = {
    username: "",
    password: "",
    error: ""
  };

  handleSignIn = async event => {
    event.preventDefault();
    const { username, password } = this.state;
    if (!username || !password) {
      this.setState({ error: "Por favor, preencha login e senha para continuar!" });
    }
  }

  render () {
    return (
      <div className="container">
        <img src={logo} alt="SGRHU"/>

        <div className="content">
          <h1>
            Olá, bem-vindo(a) de volta!
          </h1>
          <p>
            Para ter acesso ao sistema, entre com suas <strong>credenciais</strong> abaixo
          </p>

          {this.state.error && <p class="error">{this.state.error}</p>}

          <form onSubmit={this.handleSignIn}>
            <div className="input-icon">
              <input 
                type="login" 
                id="login"
                placeholder="Digite o seu login"
                onChange={event => this.setState({username: event.target.value})}
              />
              <i><User size="18"/></i>
            </div>
            <div className="input-icon">
              <input 
                type="password" 
                id="password"
                placeholder="Digite a sua senha"
                onChange={event => this.setState({password: event.target.value})}
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
}
