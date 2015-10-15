#ifndef PARTIE_H
#define PARTIE_H

#include <QDate>
#include "portefeuille.h"
#include "bourse.h"
#include "controleur.h"
class partie
{
public:
    partie();
    partie(QString nomPartie);
    partie & operator =(const partie &P);
    ~partie();

    Portefeuille *mPort;
    Bourse *mBourse;
    float getSolde();
    void depense(float montant);
    void credit(float montant);

private:
    float solde;
    QDate date;
    QString nom;
};

#endif // PARTIE_H
