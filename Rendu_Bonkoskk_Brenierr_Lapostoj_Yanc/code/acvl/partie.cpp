#include "partie.h"
#include <assert.h>
partie::partie()
{
    solde = 10000;
    nom = "PartieSansNom";
    date = QDate::currentDate();
    mPort = new Portefeuille();
    mBourse = new Bourse();
}

partie::partie(QString nomPartie)
{
    solde = 10000;
    nom = nomPartie;
    date = QDate::currentDate();
    mPort = new Portefeuille();
    mBourse = new Bourse(nomPartie.toStdString());
    this->mBourse->getNom();
}

partie::~partie()
{

}

float partie::getSolde()
{
    return solde;
}

void partie::depense(float montant)
{
    solde -= montant;
}

void partie::credit(float montant) {
    solde += montant;
}

