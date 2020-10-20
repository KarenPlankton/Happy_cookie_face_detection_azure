import React, { Component } from 'react';
import style from './Menu.css';

class PhotoList extends Component {
   constructor(props) {
      super(props) ;

         this.state = {
            items : [ ],
            isLoaded : false,
         }
}

componentDidMount() {
   fetch('http://localhost:8080/photomanagementlist').then(res => res.json() ) .then(json => {
      this.setState ({
         isLoaded: true,
         items: json,
      })
   });
}

render() {
   var{isLoaded,items}= this.state;
    if(this.props.isManagement){
    return (
             <div className={style.PhotoManagement}>
                <ul>
                   {items.map(item => (
                       <li key = {item.id }>
                         Id:{item.id } Name: {item.imageName} <a href={"http://localhost:8080/photodelete/"+item.id}>Delete</a>
                      </li>
                   ))}
                </ul>
             </div>
          );
    }
      return (
         <div className={style.PhotoManagement}>
            <ul>
               {items.map(item => (
                   <li key = {item.id }>
                     Name: {item.imageName} <a href={"http://localhost:3100/photoDetailsAndAnalysis.html?photoid="+item.id}>Analyse</a>
                  </li>
               ))}
            </ul>
         </div>
      );
}

}
export default PhotoList;
