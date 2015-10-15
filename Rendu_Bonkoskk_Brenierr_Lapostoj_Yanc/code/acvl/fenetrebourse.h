#ifndef FENETREBOURSE_H
#define FENETREBOURSE_H
#include "menujeu.h"
#include <QAbstractItemModel>
#include <QStandardItemModel>
#include <QDate>
#include <QTime>
#include <QDateTime>
#include <QPushButton>
#include "controleur.h"
class fenetreBourse : public fenetre
{

    Q_OBJECT

public:
    fenetreBourse(controleur *contro);
    ~fenetreBourse();
    // creer le tableau et ajoute les actions dedans
    QAbstractItemModel* creationTableauDonnees();
public slots:
    // creer une boite de dialogue pour permettre à l'utilisateur d'acheter une action
    void ouvrirBoiteAchat();
    void actualiser();
private:
    QPushButton *boutonAchat;
    controleur *contro;
    QPushButton *boutonActu;
    QStandardItemModel *model;
    QLabel *solde;

    bool idInBourse(QString id);
};

#endif // FENETREBOURSE_H
