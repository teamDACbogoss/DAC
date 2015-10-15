#ifndef FENETREPORTEFEUILLE_H
#define FENETREPORTEFEUILLE_H
#include "menujeu.h"
#include <QAbstractItemModel>
#include <QStandardItemModel>
#include <QDate>
#include <QTime>
#include <QDateTime>
#include <QPushButton>
#include "controleur.h"

class fenetrePortefeuille : public fenetre
{
    Q_OBJECT

    public:
    fenetrePortefeuille(controleur *contro);
    ~fenetrePortefeuille();
    // creer le tableau et ajoute les actions dedans
    QAbstractItemModel* creationTableauDonnees();

    public slots:
    // creer une boite de dialogue pour permettre à l'utilisateur de vendre une action
    void ouvrirBoiteVente();
    void actualiser();

    private:
    QStandardItemModel *model;
    controleur *contro;
    QPushButton *boutonVente;
    QPushButton *boutonActu;
};

#endif // FENETREPORTEFEUILLE_H
