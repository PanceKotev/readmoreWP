import React from 'react';
import logo from './logo.svg';
import {DatePicker} from "antd"
import "antd/dist/antd.css"
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <DatePicker format="DD/MM/YYYY"/>
      </header>
    </div>
  );
}

export default App;
