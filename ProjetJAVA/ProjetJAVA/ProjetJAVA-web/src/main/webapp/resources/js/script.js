$(document).ready(function(){

    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    
    
    $("#nouvelle-pos\\:select_type").change(function() {
        if ($("#nouvelle-pos\\:select_type").val()==="action") {
            $("#nouvelle-pos\\:id_t").prop('disabled', true);
            $("#nouvelle-pos\\:K").prop('disabled', true);
        } else {
            $("#nouvelle-pos\\:id_t").prop('disabled', false);
            $("#nouvelle-pos\\:K").prop('disabled', false);
        }
    });
    
});




function Administrateur() {
    // To do ajouter dans la liste
    window.location = "page-admin.xhtml";
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

function returnPortefeuille() {
    window.location = "portfolio.xhtml";
};

function validationNouvellePosition() {
    window.alert("Vous venez d'acheter ....");

    // TO DO ajout nouvelle position ....
    window.location = "cotations.xhtml";
};



function ajouterProduit(){
    window.location = "ajout-produit.xhtml";
};

function validerAjout() {
    window.alert("Vous venez d'acheter ....");

    // To do ajouter dans la liste
    window.location = "creation-strats.xhtml";
};


function createAccountAdmin() {
    window.location = "create-account-admin.xhtml";
};

function pageAdmin() {
    window.location = "page-admin.xhtml";
};

function gestionActif() {
    window.location = "gestion-actifs.xhtml";
}

function ajoutActionAdmin() {
    window.location = "ajout-action-admin.xhtml";
};


function ajoutActifAdmin() {
    window.alert('Josi est  un blaireau');
    gestionActif();
}

function notImplemented() {
    window.location = "not-implemented.xhtml";
}