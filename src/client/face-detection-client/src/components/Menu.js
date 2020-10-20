import React, { Component } from 'react';
import style from './Menu.css';

export default class Menu extends Component {
  render() {
    return (

       <nav className={style.navigation} >
      <ul>
        <li><a href="/index.html">HOME</a></li>
        <li><a href="/photomanagement.html">PHOTO MANAGEMENT</a></li>
        <li><a href="/photoanalysis.html">PHOTO ANALYSIS</a></li>
        <li><a href="/statistics.html">STATISTICS</a></li>
      </ul>
      </nav>

//      </div>
    );
  }
}
