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

function createAccount() {
    window.location = "create-account.xhtml";
};

function creationStrategie() {
    window.location = "creation-strats.xhtml";
};

function nouvellePosition() {
    window.location = "nouvelle-position.xhtml";
};

function returnCotations() {
    window.location = "cotations.xhtml";
};

function validationNouvellePosition() {
    window.alert("Vous venez d'acheter ....");
    
    // TO DO ajout nouvelle position ....
    window.location = "cotations.xhtml";
};

$(document).ready(function(){

    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

});

function ajouterProduit(){
    window.location = "ajout-produit.xhtml";
}

function validerAjout() {
    window.alert("Vous venez d'acheter ....");
    
    // To do ajouter dans la liste
    window.location = "creation-strats.xhtml";
};