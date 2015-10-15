#ifndef FENETREHISTORIQUE_H
#define FENETREHISTORIQUE_H

#include "menujeu.h"
#include <QAbstractItemModel>
#include <QStandardItemModel>
#include "controleur.h"


class fenetreHistorique : public fenetre
{

    Q_OBJECT

public:
    fenetreHistorique(controleur *contro);
    ~fenetreHistorique();
    // creer le tableau et ajoute les transactions dedans
    QAbstractItemModel* creationTableauDonnees();
public slots:
private:
    controleur *contro;

    QStandardItemModel *model;
};

#endif // FENETREHISTORIQUE_H
