#include "menuprincipal.h"
#include "menujeu.h"

menuPrincipal::menuPrincipal() : fenetre()
{
    initBoutons();
}

void menuPrincipal::initBoutons() {
    boutonNouvellePartie = new QPushButton("Nouvelle partie", this);
    boutonNouvellePartie->setToolTip("Lance une nouvelle partie");
    boutonNouvellePartie->setGeometry(275, 125, 250, 50);
    QObject::connect(boutonNouvellePartie, SIGNAL(clicked()), this, SLOT(afficheJeu()));

    boutonContinuer = new QPushButton("Continuer", this);
    boutonContinuer->setToolTip("Continuer une partie enregistree");
    boutonContinuer->setGeometry(275, 225, 250, 50);

    boutonOptions = new QPushButton("Options", this);
    boutonOptions->setToolTip("Affiche les options");
    boutonOptions->setGeometry(275, 325, 250, 50);

    boutonAPropos = new QPushButton("A propos", this);
    boutonAPropos->setToolTip("Affiche les informations sur l'application");
    boutonAPropos->setGeometry(275, 425, 250, 50);
}

menuPrincipal::~menuPrincipal()
{

}

void menuPrincipal::afficheJeu()
{
    menuJeu *jeu = new menuJeu();
    jeu->show();
    this->hide();
}
