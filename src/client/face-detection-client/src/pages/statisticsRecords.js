import React from 'react';
import ReactDOM from 'react-dom';
import Menu from 'components/Menu';
import StatisticsRecordList from 'components/StatisticsRecordList';

ReactDOM.render(<Menu />, document.getElementById('menu'));
ReactDOM.render(<StatisticsRecordList />, document.getElementById('statisticsRecordList'));
