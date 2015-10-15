#include "menujeu.h"
#include "fenetrebourse.h"
#include "fenetreportefeuille.h"
#include "fenetrehistorique.h"
#include <iostream>
menuJeu::menuJeu()
{
    initBoutons();
    std::cout<<"MENUJEU";
    contro = new controleur("CAC40");
}

void menuJeu::initBoutons()
{
    boutonPortefeuille = new QPushButton("Mon portefeuille", this);
    boutonPortefeuille->setToolTip("Affiche le portefeuille");
    boutonPortefeuille->setGeometry(275, 150, 250, 50);
    QObject::connect(boutonPortefeuille, SIGNAL(clicked()), this, SLOT(affichePortefeuille()));

    boutonMarche = new QPushButton("Marche des actions", this);
    boutonMarche->setToolTip("Affiche le marche");
    boutonMarche->setGeometry(275, 250, 250, 50);
    QObject::connect(boutonMarche, SIGNAL(clicked()), this, SLOT(afficheBourse()));

    boutonHistorique = new QPushButton("Mon historique", this);
    boutonHistorique->setToolTip("Affiche l'historique des transactions");
    boutonHistorique->setGeometry(275, 350, 250, 50);
    QObject::connect(boutonHistorique, SIGNAL(clicked()), this, SLOT(afficheHistorique()));
}

menuJeu::~menuJeu()
{
}

void menuJeu::affichePortefeuille()
{
    fenetrePortefeuille *portefeuille = new fenetrePortefeuille(contro);
    portefeuille->setSourceModel(portefeuille->creationTableauDonnees());
    portefeuille->show();
}

void menuJeu::afficheBourse()
{
    fenetreBourse *bourse = new fenetreBourse(contro);
    bourse->setSourceModel(bourse->creationTableauDonnees());
    bourse->show();
}

void menuJeu::afficheHistorique()
{
    fenetreHistorique *historique = new fenetreHistorique(contro);
    historique->setSourceModel(historique->creationTableauDonnees());
    historique->show();
}


