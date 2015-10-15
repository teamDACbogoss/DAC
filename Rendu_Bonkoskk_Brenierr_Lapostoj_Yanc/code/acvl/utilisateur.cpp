#include "utilisateur.h"

Utilisateur::Utilisateur(string nom,string mdp)
{
    this->nom = nom;
    this->mdp = mdp;
}

void Utilisateur::modifierMdp(string mdp)
{
    this->mdp = mdp;
}

Utilisateur::~Utilisateur()
{

}

