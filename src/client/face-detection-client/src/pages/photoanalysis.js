import React from 'react';
import ReactDOM from 'react-dom';
import Menu from 'components/Menu';
import PhotoList from 'components/PhotoList';


ReactDOM.render(<Menu />, document.getElementById('menu'));
ReactDOM.render(<PhotoList isManagement={false}/>, document.getElementById('photoanalysis'));
