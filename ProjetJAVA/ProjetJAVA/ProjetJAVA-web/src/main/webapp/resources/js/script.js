$("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
});
        
function baduser() {
    window.alert('Mot de passe ou e-mail incorrect, veuillez recommencer');
};

function checkUser() {
    var check_user = true; // récupérer dans le bean bd
    
    if (!check_user)
    {
        window.alert('Josi gros cassos');
    } else {
        window.location = "portfolio.xhtml";
    }
};

function forgottenPassword() {
    window.location = "forgotten-password.xhtml";
};

function returnConnection() {
        window.location = "login.xhtml";
};

function answerQuestion() {
    var check_user = true; // récupérer dans le bean bd
    
    if (!check_user)
    {
        window.alert('E-mail non connu');
        returnConnection();
    } else {
        window.location = "answer-question.xhtml";
    }
};

function reinitiatePassword() {
    var check_answer = true; // récupérer dans le bean bd
    
    if (!check_answer)
    {
        window.alert('La réponse est erronée');
        returnConnection();
    } else {
        window.location = "reinitiate-password.xhtml";
    }
};
