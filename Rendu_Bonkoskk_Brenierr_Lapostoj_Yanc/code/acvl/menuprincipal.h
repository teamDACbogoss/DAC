#ifndef MENUPRINCIPAL_H
#define MENUPRINCIPAL_H

#include <QPushButton>
#include "fenetre.h"
#include "controleur.h"
class menuPrincipal : public fenetre
{
    Q_OBJECT

    public:
    menuPrincipal();
    menuPrincipal(controleur *contro);
    ~menuPrincipal();

    controleur *contro;

    public slots:
    void afficheJeu();

    private:
    QPushButton *boutonNouvellePartie;
    QPushButton *boutonContinuer;
    QPushButton *boutonOptions;
    QPushButton *boutonAPropos;
    void initBoutons();

};

#endif // MENUPRINCIPAL_H
