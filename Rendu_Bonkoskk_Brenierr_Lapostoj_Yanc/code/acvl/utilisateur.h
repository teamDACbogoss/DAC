#ifndef UTILISATEUR_H
#define UTILISATEUR_H

#include <string>

using namespace std;
class Utilisateur
{
    string nom;
    string mdp;
public:
    Utilisateur(string nom, string mdp);
    void modifierMdp(string mdp);
    ~Utilisateur();
};

#endif // UTILISATEUR_H
