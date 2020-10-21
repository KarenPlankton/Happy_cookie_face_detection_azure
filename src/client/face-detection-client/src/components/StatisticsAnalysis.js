import React, { Component } from 'react';
import Chart from 'react-apexcharts';
import style from './Menu.css';
import pDetails from './PhotoDetails.css';

class StatisticsAnalysis extends Component {
   constructor(props) {
      super(props) ;

         this.state = {
            items : [ ],
            isLoaded : false,
            genderSeries: [44, 55],
            genderOptions: {
                          chart: {
                            width: 380,
                            type: 'pie',
                          },
                          labels: ['Male', 'Female', ],
                          responsive: [{
                            breakpoint: 480,
                            genderOptions: {
                              chart: {
                                width: 200
                              },
                              legend: {
                                position: 'bottom'
                              }
                            }
                          }]
                          },
            ageSeries: [44, 55,60,80],
            ageOptions: {
            chart: {
              width: 380,
              type: 'pie',
            },
            labels: ['Age 10 to 30', 'Age 30 to 50','Age 50 to 70','Age 70 plus' ],
            responsive: [{
              breakpoint: 480,
              genderOptions: {
                chart: {
                  width: 200
                },
                legend: {
                  position: 'bottom'
                }
              }
            }]
            },
            emotionSeries: [44, 55,60,80,44, 55,60,80],
            emotionOptions: {
            chart: {
              width: 380,
              type: 'pie',
            },
            labels: ['Anger', 'Contempt','Disgust','Fear','Hapiness','Neutral','Sadness','Surprise' ],
            responsive: [{
              breakpoint: 480,
              genderOptions: {
                chart: {
                  width: 200
                },
                legend: {
                  position: 'bottom'
                }
              }
            }]
            },
}
}

componentDidMount() {
   fetch('http://localhost:8080/statisticsAnalysisDetails').then(res => res.json() ) .then(json => {
      this.setState ({
         isLoaded: true,
         items: json,
         genderSeries: [json.percentageMale,json.percentageFemale],
         ageSeries: [json.percentage1030,json.percentage3050,json.percentage5070,json.percentage70plus],
         emotionSeries: [json.percentageAnger,json.percentageContempt,json.percentageDisgust,json.percentagefear,json.percentageHappiness,json.percentageNeutral,json.percentageSadness,json.percentageSurprise],
      })
   });
}

render() {
   var{isLoaded,items,genderSeries,genderOptions,ageSeries,ageOptions,emotionOptions,emotionSeries}= this.state;
      return (
         <div className={style.PhotoManagement}>
         <Chart options={genderOptions} series={genderSeries} height={350} type="pie" />
         <Chart options={ageOptions} series={ageSeries} height={350} type="pie" />
         <Chart options={emotionOptions} series={emotionSeries} height={350} type="pie" />
            <ul>Percentage Male: {(Math.round(items.percentageMale * 100) ).toFixed(2)}% </ul>
            <ul>Percentage Female: {(Math.round(items.percentageFemale*100) ).toFixed(2)}%</ul>
            <ul>Percentage 10 to 30: {(Math.round(items.percentage1030 * 100) ).toFixed(2)}% </ul>
            <ul>Percentage 30 to 50: {(Math.round(items.percentage3050*100) ).toFixed(2)}%</ul>
            <ul>Percentage 50 to 70: {(Math.round(items.percentage5070*100) ).toFixed(2)}% </ul>
            <ul>Percentage 70 plus: {(Math.round(items.percentage70plus*100) ).toFixed(2)}%</ul>
            <ul>Anger percentage: {(Math.round(items.percentageAnger*100) ).toFixed(2)}% </ul>
            <ul>Contempt percentage: {(Math.round(items.percentageContempt*100) ).toFixed(2)}%</ul>
            <ul>Disgust percentage: {(Math.round(items.percentageDisgust*100) ).toFixed(2)}% </ul>
            <ul>Fear percentage: {(Math.round(items.percentagefear*100) ).toFixed(2)}%</ul>
            <ul>Happiness percentage: {(Math.round(items.percentageHappiness*100) ).toFixed(2)}% </ul>
            <ul>Neutral percentage: {(Math.round(items.percentageNeutral*100) ).toFixed(2)}%</ul>
            <ul>Sadness percentage: {(Math.round(items.percentageSadness*100) ).toFixed(2)}% </ul>

            <ul>Surprise percentage: {(Math.round(items.percentageSurprise*100) ).toFixed(2)}%</ul>


               <form>

            <button type="submit" className={pDetails.button} formaction="http://localhost:3100/statisticsRecords.html"><span>Statistics Record</span></button>

               </form>





         </div>
      );

    }
}
export default StatisticsAnalysis;