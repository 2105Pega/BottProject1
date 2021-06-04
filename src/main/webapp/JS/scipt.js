var username = getElementById("username").value
var password = getElementById("password").value

function login_user() {
    let searchText = document.getElementById("login_button").value;
    let url = `https://pokeapi.co/api/v2/pokemon/${searchText}`;

    let request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        if (this.readyState == 4) {
            currentPokemon = JSON.parse(this.response);
            document.querySelector("#pokemon_image").src = currentPokemon.sprites.front_shiny;
        }
    }

    request.open("GET", url);
    request.send();
}