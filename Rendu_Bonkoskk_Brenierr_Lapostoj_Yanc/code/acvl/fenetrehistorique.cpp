#include "fenetrehistorique.h"

fenetreHistorique::fenetreHistorique(controleur *control)
{
    this->creationTableau();
    contro = control;
    model = new QStandardItemModel(0, 6);
}

fenetreHistorique::~fenetreHistorique()
{

}

QAbstractItemModel *fenetreHistorique::creationTableauDonnees()
{
    model->setHeaderData(0, Qt::Horizontal, QObject::tr("idAction"));
    model->setHeaderData(1, Qt::Horizontal, QObject::tr("Entreprise"));
    model->setHeaderData(2, Qt::Horizontal, QObject::tr("Cours Operation"));
    model->setHeaderData(3, Qt::Horizontal, QObject::tr("Cours actuel"));
    model->setHeaderData(4, Qt::Horizontal, QObject::tr("Quantite"));
    model->setHeaderData(5, Qt::Horizontal, QObject::tr("Date Operation"));
    // on ajoute toutes les transactions dispo au tableau historique
    contro->parcourirActionsHistorique(model);
    return model;
}


