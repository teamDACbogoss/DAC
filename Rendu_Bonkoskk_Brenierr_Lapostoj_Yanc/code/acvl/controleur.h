#ifndef CONTROLEUR_H
#define CONTROLEUR_H
#include <QString>
#include <QStandardItemModel>
#include <QDateEdit>
class partie;

class controleur
{
public:
    controleur(QString nom);
    void traiterAchat(QString idAction, int nbActions, QStandardItemModel *model);

    // on garantie avant que la vente est possible
    void traiterVente(QString idAction, int nbActions, QStandardItemModel *model);

    void parcourirActionsPortefeuille(QStandardItemModel *model);

    void parcourirActionsHistorique(QStandardItemModel *model);

    void parcourirActionsBourse(QStandardItemModel *model);

    void addActionBourse(QStandardItemModel *model, const QString &idAction,
                   const QString &entreprise, const float &coursOuverture, const float &coursActuel, const int &quantite);

    void addActionPortefeuille(QStandardItemModel *model, const QString &idAction,
                   const QString &entreprise, const float &coursActuel, const int &quantite);

    void cleanTable(QStandardItemModel *model);

    void cleanPortefeuille(QStandardItemModel *model);

    ~controleur();

    void addTransactionHistorique(QAbstractItemModel *model, const QString &idAction,
                   const QString &entreprise, const float &coursOuverture, const float &coursActuel, const int &quantite, const QDateTime &dateTransaction);

    void majPrix();

    void actualiser();

    bool idInBourse(QString id);

    bool idInPortefeuille(QString id);

    int quantiteActionDetenues(QString id);

    int quantiteAction(QString id);

    float cashDispo();

    float prixAction(QString id);

    partie *getPartie();

private:
    partie *mPartie;
    bool firstActu;
};

#endif // CONTROLEUR_H
