#ifndef MENUJEU_H
#define MENUJEU_H

#include <QPushButton>
#include "fenetre.h"
#include "controleur.h"
class menuJeu : public fenetre
{
    Q_OBJECT

    public:
    menuJeu();
    ~menuJeu();
    controleur *contro;

    public slots:
    void afficheBourse();
    void affichePortefeuille();
    void afficheHistorique();

    private:
    //partie *partieActuelle;
    QPushButton *boutonPortefeuille;
    QPushButton *boutonMarche;
    QPushButton *boutonHistorique;
    void initBoutons();

};

#endif // MENUJEU_H
