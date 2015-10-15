#include "fenetre.h"
#include <QHBoxLayout>

fenetre::fenetre() : QWidget()
{
    largeur = 800;
    hauteur = 600;
    setFixedSize(largeur, hauteur);
}

fenetre::fenetre(QWidget *parent) : QWidget(parent)
{
    largeur = 800;
    hauteur = 600;
    setFixedSize(largeur, hauteur);
}

fenetre::~fenetre()
{

}

void fenetre::creationTableau() {
    sourceView = new QTableView;
    sourceView->setAlternatingRowColors(true);
    sourceView->setEditTriggers(QAbstractItemView::NoEditTriggers);
    sourceView->setSortingEnabled(true);
    sourceGroupBox = new QGroupBox(tr("Actions du CAC 40"));

    QHBoxLayout *sourceLayout = new QHBoxLayout;
    sourceLayout->addWidget(sourceView);
    sourceGroupBox->setLayout(sourceLayout);

    QVBoxLayout *mainLayout = new QVBoxLayout;

    mainLayout->addWidget(sourceGroupBox);

    setLayout(mainLayout);
    resize(500, 450);

    setWindowTitle(tr("Bourse"));
}

void fenetre::setSourceModel(QAbstractItemModel *model)
{
    sourceView->setModel(model);
}
