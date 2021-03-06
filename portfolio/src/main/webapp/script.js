// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Hello world!', '¡Hola Mundo!', '你好，世界！', 'Bonjour le monde!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

function createMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 40.423, lng: -86.921},
    zoom: 16
    });
    // const map = new google.maps.Map(
    //   document.getElementById('map'),
    //   {center: {lat: 25.338, lng: -86.921195}, zoom: 16});
}


function displayHobbies() {
    const title = document.getElementById('content-title');
    title.innerText = "This is a list of my main hobbies:";
    $(function(){
        $("#content-panel").load("main_page_clickables/hobbies.html"); 
    });
}

function displayAbout() {
    const title = document.getElementById('content-title');
    title.innerText = "Here is some background information about me:";
    $(function(){
        $("#content-panel").load("main_page_clickables/aboutme.html"); 
    });
}

function displayGame() {
    const title = document.getElementById('content-title');
    title.innerHTML = "Let's play a fun game (well, at least I find it fun <i class=\"em em-smile\" aria-role=\"funny\" aria-label=\"Smile\"></i>)";
    $(function(){
        $("#content-panel").load("main_page_clickables/game.html"); 
    });   
}

function displayCountry() {
    console.log("Getting the country name country");

    const responsePromise = fetch('/country');

    responsePromise.then(handleResponse);
}

function handleResponse(response) {
  console.log('Handling the country response.');

    // response.text() returns a Promise, because the response is a stream of
    // content and not a simple variable.
    // const textPromise = response.text();
    const textPromise = response.json();
    // console.log(textPromise);
    // console.log(textPromise[0]);

    textPromise.then(countryGame);
}

function countryGame(country) {
    var round = 1;
    var score = 0;
    const scoreHTML = document.getElementById('country-score');
    
    //while (round <= 10) {
        document.getElementById('correct-country-btn').onclick = function() {
            round++;
            score++;
            break;
        }
        document.getElementById('incorrect-country-btn').onclick = function() {
            round++;
            break;
        }
    //}
    
    scoreHTML.innerText = score + '/10';
    console.log(score);
    console.log("hehhahaha");
    console.log(round);

    console.log('Country added: ' + country);
    console.log(country[0].countryName);

    const countryName = document.getElementById('country-name');
    countryName.innerText = country;
}


function displayComments() {
    const title = document.getElementById('content-title');
    title.innerHTML = "Do you have any feedback on my website? Please feel free to leave a comment.";
    $(function(){
        $("#content-panel").load("main_page_clickables/comment-form.html"); 
    });

}

function getComments() {
    fetch('/data').then(response => response.json()).then((comments) => {
        // comments is an object, not a string, so we have to
        // reference its fields to create HTML content

        console.log(comments);
        const title = document.getElementById('content-title');
        const content = document.getElementById('content-panel');

        //Is setting this to empty a good practice standard?
        title.innerHTML = '';
        title.innerText = 'message 1:';

        content.innerHTML = '';
        content.innerText = comments['messages'][0];

        //Question: what's the use of appendChild() ?

        // const statsListElement = document.getElementById('server-stats-container');
        // statsListElement.innerHTML = '';
        // statsListElement.appendChild(
        //     createListElement('Start time: ' + stats.startTime));
    });
}

function activateListItem() {
    $(document).ready(function() { 
            $('li').click(function() { 
                $('li.list-group-item.active').removeClass("active"); 
                $(this).addClass("active"); 
            }); 
        });
}

