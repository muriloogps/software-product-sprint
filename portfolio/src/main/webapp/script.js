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

function handleResponse(response) {
  console.log('Handling the country response.');

  // response.text() returns a Promise, because the response is a stream of
  // content and not a simple variable.
  const textPromise1 = response.text();
  console.log(textPromise1);
//   const textPromise2 = response[b].text(); 
//   console.log(textPromise2);

  textPromise1.then(addCountry);
}

function addCountry(country) {
  console.log('Country added: ' + country);

  const countryName = document.getElementById('country-name');
  countryName.innerText = country;
}

function activateListItem() {
    $(document).ready(function() { 
            $('li').click(function() { 
                $('li.list-group-item.active').removeClass("active"); 
                $(this).addClass("active"); 
            }); 
        });
}

