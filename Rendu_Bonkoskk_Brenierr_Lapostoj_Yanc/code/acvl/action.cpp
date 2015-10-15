#include "action.h"
#include <stdexcept>

/*Action::Action(QObject *parent) : QObject(parent)
{

}*/

Action::Action(){

}

Action::Action(std::string id, std::string emeteur, int quantite, float prixOuverture, float prix)
{
    idAction = id;
    this->emeteur = emeteur;
    this->quantite = quantite;
    this->prix = prix;
    this->prixOuverture = prixOuverture;
}

Action::~Action()
{

}

float Action::getPrixOuverture() {
    return prixOuverture;
}

void Action::majPrix(int newPrix){
    prix = newPrix;
}

void Action::setQuantite(int qtt)
{
    quantite = qtt;
}

float Action::vendreAction(int quantite){
    this->quantite += quantite;
    return quantite*prix;
}


float Action::acheterAction(int quantite){
    if(quantite > this->quantite){
        throw new std::string("la quantite d'action n'est pas suffissante");
    }
    this->quantite -= quantite;
    return quantite * prix;
}

std::string Action::getId(){
    return idAction;
}
std::string Action::getEmeteur(){
    return emeteur;
}
float Action::getPrix(){
    return prix;
}
int Action::getQuantite(){
    return quantite;
}


void Action::setPrix(float prix){
    this->prix = prix;
}

void Action::majPrixOuverture(float prix)
{
    prixOuverture = prix;
}

