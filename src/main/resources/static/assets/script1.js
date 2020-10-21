var i = 0;
var txt = 'Hello, I am a face detection app that detects customer satisfaction.';
var speed = 50;

function typeWriter() {
 if (i < txt.length) {
   document.getElementById("demo").innerHTML += txt.charAt(i);
   i++;
   setTimeout(typeWriter, speed);
 }
}