#include "controleur.h"
#include "partie.h"
#include <iterator>
#include <QString>
#include <assert.h>
#include <sstream>

controleur::controleur(QString nom)
{
    mPartie = new partie(nom);
    firstActu = true;
}

controleur::~controleur()
{

}

partie *controleur::getPartie()
{
    return mPartie;
}

void controleur::traiterAchat(QString idAction, int nbActions, QStandardItemModel *model) {
    Transaction *tr = new Transaction((mPartie->mBourse->getPrix(idAction.toStdString())), nbActions, idAction.toStdString());
    mPartie->mPort->addTransaction(*tr); // on les ajoute au portefeuille
    float prix = mPartie->mBourse->acheterAction(idAction.toStdString(), nbActions); // on les enlève du marché
    mPartie->depense(prix); // on notifie la dépense dans le solde
    // on actualise l'affichage de la bourse
    cleanTable(model);
    parcourirActionsBourse(model);
}

void controleur::traiterVente(QString idAction, int nbActions, QStandardItemModel *model) {
    Transaction *tr = new Transaction((mPartie->mBourse->getPrix(idAction.toStdString())), -nbActions, idAction.toStdString());
    mPartie->mPort->addTransaction(*tr); // on les ajoute au portefeuille
    float prix = mPartie->mBourse->vendreAction(idAction.toStdString(), nbActions); // on les enlève du marché
    mPartie->credit(prix); // on notifie la dépense dans le solde
    // on actualise l'affichage de la bourse
    cleanTable(model);
    parcourirActionsPortefeuille(model);
}

void controleur::addActionPortefeuille(QStandardItemModel *model, const QString &idAction,
             const QString &entreprise, const float &coursActuel, const int &quantite)
{
    model->insertRow(0);
    model->setData(model->index(0, 0), idAction);
    model->setData(model->index(0, 1), entreprise);
    model->setData(model->index(0, 2), coursActuel);
    model->setData(model->index(0, 3), quantite);
}

void controleur::addActionBourse(QStandardItemModel *model, const QString &idAction,
             const QString &entreprise, const float &coursOuverture, const float &coursActuel, const int &quantite)
{
    model->insertRow(0);
    model->setData(model->index(0, 0), idAction);
    model->setData(model->index(0, 1), entreprise);
    model->setData(model->index(0, 2), coursOuverture);
    model->setData(model->index(0, 3), coursActuel);
    model->setData(model->index(0, 4), quantite);
}

void controleur::addTransactionHistorique(QAbstractItemModel *model, const QString &idAction,
               const QString &entreprise, const float &coursOuverture, const float &coursActuel, const int &quantite, const QDateTime &dateTransaction) {
    model->insertRow(0);
    model->setData(model->index(0, 0), idAction);
    model->setData(model->index(0, 1), entreprise);
    model->setData(model->index(0,2), coursOuverture);
    model->setData(model->index(0, 3), coursActuel);
    model->setData(model->index(0, 4), quantite);
    model->setData(model->index(0, 5), dateTransaction);
}

void controleur::actualiser()
{
    if (firstActu)
    {
        mPartie->mBourse->setQuantite();
        firstActu = false;
    }
    else
    {
        if (mPartie->mBourse->getActu().daysTo(QDateTime::currentDateTime()) > 0)
        {
            mPartie->mBourse->setQuantite();
        }
        qint64 duree = mPartie->mBourse->getActu().secsTo(QDateTime::currentDateTime());
        mPartie->mBourse->setPrix((int) duree);
    }

    mPartie->mBourse->setActu();
}

void controleur::cleanTable(QStandardItemModel *model)
{
    std::map<std::string,Action> actions = mPartie->mBourse->getActionSet();
    for(std::map<std::string,Action>::iterator it=actions.begin() ; it!=actions.end() ; ++it)
    {
        model->takeRow(0);
    }
}

void controleur::cleanPortefeuille(QStandardItemModel *model)
{
    std::map<std::string, set<Transaction> > actions = mPartie->mPort->getTransactions();
    for(std::map<std::string, set<Transaction> >::iterator it=actions.begin() ; it!=actions.end() ; ++it)
    {
        model->takeRow(0);
    }
}

int controleur::quantiteActionDetenues(QString id) {
    std::map<std::string, set<Transaction> > trans = mPartie->mPort->getTransactions();
    int quantiteAction = 0;
    for(std::set<Transaction>::iterator it = trans[id.toStdString()].begin() ; it!= trans[(id).toStdString()].end() ; ++it) {
        quantiteAction += it->getQuantite();
    }
    return quantiteAction;
}

int controleur::quantiteAction(QString id)
{
    std::map<std::string,Action> actions = mPartie->mBourse->getActionSet();
    return actions.find(id.toStdString())->second.getQuantite();
}

float controleur::prixAction(QString id)
{
    std::map<std::string,Action> actions = mPartie->mBourse->getActionSet();
    return actions.find(id.toStdString())->second.getPrix();
}

float controleur::cashDispo()
{
    return mPartie->getSolde();
}

void controleur::parcourirActionsBourse(QStandardItemModel *model) {
    std::map<std::string,Action> actions = mPartie->mBourse->getActionSet();
    for(std::map<std::string,Action>::iterator it=actions.begin() ; it!=actions.end() ; ++it)
    {
        addActionBourse(model, QString::fromStdString(it->first), QString::fromStdString(it->second.getEmeteur()),
                        it->second.getPrixOuverture(), it->second.getPrix(), it->second.getQuantite());
    }
}

void controleur::parcourirActionsPortefeuille(QStandardItemModel *model) {

    int quantiteAction = 0;
    std::map<std::string,Action> actions = mPartie->mBourse->getActionSet();
    std::map<std::string, set<Transaction> > trans = mPartie->mPort->getTransactions();
    for(std::map<std::string,set<Transaction> >::iterator it=trans.begin() ; it!=trans.end() ; ++it)
    {
        quantiteAction = 0;
        for(std::set<Transaction>::iterator itTrans = (it->second).begin() ; itTrans != (it->second).end() ; ++itTrans) {
            quantiteAction += itTrans->getQuantite();
        }
        assert(quantiteAction >= 0); // on n'aurait vendu des actions non détenues
        if (quantiteAction > 0) {
            addActionPortefeuille(model, QString::fromStdString(actions[it->first].getId()), QString::fromStdString(actions[it->first].getEmeteur()),
                     actions[(it->first)].getPrix(), quantiteAction);
        }
    }
}

void controleur::parcourirActionsHistorique(QStandardItemModel *model) {
    std::map<std::string,Action> actions = mPartie->mBourse->getActionSet(); // pour récuperer l'emetteur et le prix actuel.
    std::map<std::string,set<Transaction> > transactions = mPartie->mPort->getTransactions(); // pour récuperer l'emetteur et le prix actuel.

    for(std::map<std::string,set<Transaction> >::iterator it=transactions.begin() ; it!=transactions.end() ; ++it)
    {

        for(std::set<Transaction>::iterator itTrans = (it->second).begin() ; itTrans != (it->second).end() ; ++itTrans) {

            addTransactionHistorique(model, QString::fromStdString(itTrans->getIdAction()), QString::fromStdString(actions[it->first].getEmeteur()), (itTrans)->getPrix(),
                    actions[it->first].getPrix(), (itTrans)->getQuantite(),  QDateTime::fromTime_t((itTrans)->getDateTime()));
        }
    }
}

bool controleur::idInBourse(QString id)
{
    std::map<std::string,Action> actions = mPartie->mBourse->getActionSet();
    return !(actions.find(id.toStdString()) == actions.end());
}

bool controleur::idInPortefeuille(QString id)
{
    std::map<std::string, set<Transaction> > actions = mPartie->mPort->getTransactions();
    return !(actions.find(id.toStdString()) == actions.end());
}
