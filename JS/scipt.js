function login_user() {
    console.log("entering login user method");
    var username = document.getElementById('usernameInput').value
    var password = document.getElementById('passwordInput').value

    const payload = {
        username: username,
        password: password,
    };


    let url = `http://localhost:8080/Project1/api/c/login`;



    let request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        console.log("ready state changed", this.readyState);
        if (this.readyState == 4) {
            validLogin = JSON.parse(this.response);

            console.log(validLogin);
            if (validLogin) {
                console.log("Makes into valid login")
                    // redirect to user accounts page
                window.location.href = `http://google.com`;
            }
        }
    }

    request.open("POST", url);
    request.send(JSON.stringify(payload));
}

function logout() {
    window.location.href = `C:\\Users\\james\\OneDrive\\Desktop\\Git\\BottProject1\\BottProject1\\src\\main\\webapp\\HTML\\logIn.html`;

}

// likely need to pass username
function load_accounts() {

    let url = `http://localhost:8080/Project1/api/c/account`;

    let request = new XMLHttpRequest();

    request.onreadystatechange = function() {

        if (this.readyState == 4) {
            accounts = JSON.parse(this.response);

            // make loop that prints accounts
            // add onclick properties to deposit and withdraw
            let i;
            for (i = 0; i < accounts.length; i++) {}
            document.getElementById("accounts").innerHTML +=
                "<p>Account ID: " + accid + " has a Balance: " + balance + " </p ><br>";

        }



    }


}


function deposit() {
    let url = `http://localhost:8080/Project1/api/c/deposit`;

    let id = document.getElementById("account_selected").value;
    let deposit = document.getElementById("withdrawOrDeposit").value;

    let acc_and_bal = {
        accid: id,
        balance: deposit,
    };
    let request = new XMLHttpRequest();


    request.open("POST", url);
    request.send(JSON.stringify(acc_and_bal));

}

function withdraw() {

    let url = `http://localhost:8080/Project1/api/c/withdraw`;

    let id = document.getElementById("account_selected").value;
    let withdraw = document.getElementById("withdrawOrDeposit").value;

    let acc_and_bal = {
        accid: id,
        balance: withdraw,
    };
    let request = new XMLHttpRequest();


    request.open("POST", url);
    request.send(JSON.stringify(acc_and_bal));


}


function add_account() {
    let url = `http://localhost:8080/Project1/api/c/add_account`;

    let request = new XMLHttpRequest();

    request.onreadystatechange = function() {

        if (this.readyState == 4) {
            accounts = JSON.parse(this.response);
        }
    }
}