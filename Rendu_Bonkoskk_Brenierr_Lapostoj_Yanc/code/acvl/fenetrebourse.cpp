#include "fenetrebourse.h"
#include <QInputDialog>
#include <QLineEdit>
#include <QMessageBox>
#include <QFormLayout>
#include <QDialogButtonBox>
#include <QSpinBox>
#include "partie.h"

fenetreBourse::fenetreBourse(controleur *control)
{
    this->creationTableau();
    boutonAchat = new QPushButton("Acheter actions", this);
    boutonAchat->setToolTip("Acheter une action");
    boutonAchat->setGeometry(625, 6, 150, 25);
    QObject::connect(boutonAchat, SIGNAL(clicked()), this, SLOT(ouvrirBoiteAchat()));

    boutonActu = new QPushButton("Actualiser", this);
    boutonActu->setToolTip("Actualise les infos");
    boutonActu->setGeometry(450, 6, 150, 25);
    QObject::connect(boutonActu, SIGNAL(clicked()), this, SLOT(actualiser()));

    contro = control;
    model = new QStandardItemModel(0, 5);

    QString montant = QString::number(contro->getPartie()->getSolde());
    solde = new QLabel("Cash disponible : "+montant, this);
    solde->setGeometry(200, 6, 300, 25);
}

fenetreBourse::~fenetreBourse()
{

}

void fenetreBourse::ouvrirBoiteAchat()
{
    bool ok;
    QString id = QInputDialog::getText(this, "ID action", "ID de l'action ?", QLineEdit::Normal, QString(), &ok);
    if (ok)
    {
        if (!id.isEmpty() && idInBourse(id))
        {
            int max = contro->quantiteAction(id);
            max = std::min((int) (contro->cashDispo()/contro->prixAction(id)), max);
            int nbActions = QInputDialog::getInt(this, "Nombre d'actions", "Combien en voulez-vous ?", 0, 0, max, 1, &ok);
            if (ok)
            {
                contro->traiterAchat(id, nbActions, model);
                solde->setText("Cash disponible : "+QString::number(contro->getPartie()->getSolde())+"€");
            }
            else
            {
                QMessageBox::critical(this, "Achat annule", "Achat annule");
            }
        }
        else
        {
            QMessageBox::critical(this, "Achat actions", "Erreur : Aucun identifiant ne correspond a celui saisi.");
        }
    }
    else
    {
        QMessageBox::critical(this, "Achat annule", "Achat annule");
    }

}

bool fenetreBourse::idInBourse(QString id)
{
    return contro->idInBourse(id);
}

void fenetreBourse::actualiser()
{
    contro->cleanTable(model);
    contro->actualiser();
    contro->parcourirActionsBourse(model);
}

QAbstractItemModel *fenetreBourse::creationTableauDonnees()
{
    model->setHeaderData(0, Qt::Horizontal, QObject::tr("Action"));
    model->setHeaderData(1, Qt::Horizontal, QObject::tr("Entreprise"));
    model->setHeaderData(2, Qt::Horizontal, QObject::tr("Cours d'ouverture"));
    model->setHeaderData(3, Qt::Horizontal, QObject::tr("Cours actuel"));
    model->setHeaderData(4, Qt::Horizontal, QObject::tr("Quantite"));
    contro->actualiser();
// on ajoute toutes les actions dispo à la bourse
    contro->parcourirActionsBourse(model);

    return model;
}
