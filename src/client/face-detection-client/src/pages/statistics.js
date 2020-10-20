import React from 'react';
import ReactDOM from 'react-dom';
import Menu from 'components/Menu';
import StatisticsAnalysis from 'components/StatisticsAnalysis';

ReactDOM.render(<Menu />, document.getElementById('menu'));
ReactDOM.render(<StatisticsAnalysis />, document.getElementById('statisticsAnalysis'));
