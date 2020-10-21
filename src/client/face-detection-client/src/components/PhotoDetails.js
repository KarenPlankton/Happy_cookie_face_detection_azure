import React, { Component } from 'react';
import style from './Menu.css';
import pDetails from './PhotoDetails.css';

class PhotoDetails extends Component {
   constructor(props) {
      super(props) ;

         this.state = {
            items : [ ],
            isLoaded : false,
            photo: [],
            analysis:[]
         }


}
//convert binary to base64, string
arrayBufferToBase64(buffer) {
  var binary = '';
  var bytes = [].slice.call(new Uint8Array(buffer));

  bytes.forEach((b) => binary += String.fromCharCode(b));

  return window.btoa(binary);
};


componentDidMount() {
   const windowUrl = window.location.search;
   //console.log(windowUrl);
   const params = windowUrl.split("=");
   const photoid=params[1];
   //console.log(photoid);
   var urlDet='http://localhost:8080/photosdetails/'+photoid;
   //console.log("urlDet is "+urlDet)
   fetch(urlDet).then(res => res.json() ) .then(json => {
      this.setState ({
         isLoaded: true,
         items: json,
      })
      //console.log(json);
   });

   var urlPhot='http://localhost:8080/photosbinary/'+photoid;
      //console.log("urlPhot is "+urlPhot);
   fetch(urlPhot).then(response =>
   {
     response.arrayBuffer().then((buffer) => {
       var base64Flag = 'data:image/jpg;base64,';
       var imageStr = this.arrayBufferToBase64(buffer);

       this.setState({
            photo:base64Flag + imageStr,
       })
       //console.log(base64Flag + imageStr);
     });
      });

      var urlPhotAnalysis='http://localhost:8080/retrieveAnalysis/'+photoid;
            //console.log("urlPhot is "+urlPhot);
         fetch(urlPhotAnalysis).then(res => res.json()).then(json => {
                                           this.setState ({
                                              analysis: json,
                                           })
                                           //console.log(json);
                                           });



}



render() {
   var{isLoaded,items,photo,analysis}= this.state;
      return (
        <div className={style.PhotoManagement}>
            <h2 className={pDetails.photoDetails}>Photo Details</h2>
            <ul>Id: {items.id}</ul><ul> Name: {items.imageName}</ul>
            <ul className="image"><img src={photo} alt={items.imageName} height="60%" width="60%" /></ul>
            <ul className={pDetails.analysis} id="analysis">Analysis Basics:</ul>
            {analysis.map( blah =>
            <div>
                    <ul>Age: {blah.faceAttributes.age}</ul>
                    <ul>Gender: {blah.faceAttributes.gender}</ul>

                    <ul className={pDetails.facial}>Facial Characteristics</ul>
                    <ul>Glasses: {blah.faceAttributes.glasses}</ul>
                    <ul>Baldness: {blah.faceAttributes.hair.bald}</ul>
                    <ul>Beard: {blah.faceAttributes.facialHair.beard}</ul>
                    <ul>Moustache: {blah.faceAttributes.facialHair.moustache}</ul>
                    <ul>Sideburns: {blah.faceAttributes.facialHair.sideburns}</ul>



                    <ul className={pDetails.emotions}>Emotions</ul>
                    <ul>Anger: {blah.faceAttributes.emotion.anger}</ul>
                    <ul>Contempt: {blah.faceAttributes.emotion.contempt}</ul>
                    <ul>Disgust: {blah.faceAttributes.emotion.disgust}</ul>
                    <ul>Fear: {blah.faceAttributes.emotion.fear}</ul>
                    <ul>Happiness: {blah.faceAttributes.emotion.happiness}</ul>
                    <ul>Neutral: {blah.faceAttributes.emotion.neutral}</ul>
                    <ul>Sadness: {blah.faceAttributes.emotion.sadness}</ul>
                    <ul>Surprise: {blah.faceAttributes.emotion.surprise}</ul>
            </div>
                )
            }
            <form>
            <button type="submit" className={pDetails.button}  formaction={"http://localhost:8080/saveAnalysisDeletePhoto/"+items.id}><span>Save analysis and delete photo</span></button>
            </form>
        </div>
      );
}

}
export default PhotoDetails;
