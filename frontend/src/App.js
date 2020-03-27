import React from 'react';
import logo from './logo.svg';
import {DatePicker} from "antd"
import "antd/dist/antd.css"
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <DatePicker format="DD/MM/YYYY"/>
        
          Learn React

      </header>
    </div>
  );
}

export default App;
