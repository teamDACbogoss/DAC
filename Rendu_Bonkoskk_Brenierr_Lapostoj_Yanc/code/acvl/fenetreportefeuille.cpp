#include "fenetreportefeuille.h"
#include <QInputDialog>
#include <QLineEdit>
#include <QMessageBox>
#include <QFormLayout>
#include <QDialogButtonBox>
#include <QSpinBox>

fenetrePortefeuille::fenetrePortefeuille(controleur *control)
{
    contro = control;
    this->creationTableau();
    boutonVente= new QPushButton("Vendre actions", this);
    boutonVente->setToolTip("Vendre une action");
    boutonVente->setGeometry(625, 6, 150, 25);
    QObject::connect(boutonVente, SIGNAL(clicked()), this, SLOT(ouvrirBoiteVente()));

    boutonActu = new QPushButton("Actualiser", this);
    boutonActu->setToolTip("Actualise les infos");
    boutonActu->setGeometry(450, 6, 150, 25);
    QObject::connect(boutonActu, SIGNAL(clicked()), this, SLOT(actualiser()));

    model = new QStandardItemModel(0, 4);
}

fenetrePortefeuille::~fenetrePortefeuille()
{

}

void fenetrePortefeuille::ouvrirBoiteVente()
{
    bool ok;
    QString id = QInputDialog::getText(this, "ID action", "ID de l'action ?", QLineEdit::Normal, QString(), &ok);
    if (ok && !id.isEmpty())
    {
        if (contro->idInPortefeuille(id)) {
            int nbActionDetenues = contro->quantiteActionDetenues(id);
            // lutilisateur ne peut pas en acheter plus que ce qu'il y a (saisie est bloquée)
            int nbActions = QInputDialog::getInt(this, "Nombre d'actions", "Combien voulez-vous en vendre ?", 0, 0, nbActionDetenues, 1, &ok);
            if (ok) {
                contro->traiterVente(id, nbActions, model);
            } else {
                QMessageBox::critical(this, "Vente annule", "Vente annule");
            }
        } else {
            QMessageBox::critical(this, "Vente annule", "Vous ne possedez pas l'action.");
        }
    }
    else
    {
        QMessageBox::critical(this, "Vente annule", "Vente annule");
    }
}

void fenetrePortefeuille::actualiser()
{
    contro->cleanPortefeuille(model);
    contro->actualiser();
    contro->parcourirActionsPortefeuille(model);
}

QAbstractItemModel *fenetrePortefeuille::creationTableauDonnees()
{
    model->setHeaderData(0, Qt::Horizontal, QObject::tr("Action"));
    model->setHeaderData(1, Qt::Horizontal, QObject::tr("Entreprise"));
    model->setHeaderData(2, Qt::Horizontal, QObject::tr("Cours Actuel"));
    model->setHeaderData(3, Qt::Horizontal, QObject::tr("Quantite"));

    // TODO : parcourir la liste d'actions et les ajouter
    contro->parcourirActionsPortefeuille(model);
    return model;
}
