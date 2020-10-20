import React, { Component } from 'react';
import style from './Menu.css';

class StatisticsRecordList extends Component {
   constructor(props) {
      super(props) ;

         this.state = {
            items : [ ],
            isLoaded : false,
         }
}

componentDidMount() {
   fetch('http://localhost:8080//photoanalysislist').then(res => res.json() ) .then(json => {
      this.setState ({
         isLoaded: true,
         items: json,
      })
   });
}

render() {
   var{isLoaded,items}= this.state;

      return (
         <div className={style.PhotoManagement}>
            <ul>
               {items.map(item => (
                   <li key = {item.id }>
                     Row: {item.id} Image Name: {item.photoFilename} <a href={"http://localhost:8080/photoAnalysisdelete/"+item.id}>Delete</a>
                  </li>
               ))}
            </ul>
         </div>
      );
}
}
export default StatisticsRecordList;
