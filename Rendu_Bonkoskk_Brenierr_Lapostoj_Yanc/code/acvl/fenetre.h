#ifndef FENETRE_H
#define FENETRE_H
#include <QSortFilterProxyModel>
#include "fenetre.h"
#include <QTableView>
#include <QGroupBox>
#include <QCheckBox>
#include <QLabel>
#include <QWidget>
class fenetre : public QWidget
{
    public:
    fenetre();
    fenetre(QWidget *parent);
    ~fenetre();

    void setSourceModel(QAbstractItemModel *model);

    void creationTableau();

    signals:

    public slots:

    private:
    int hauteur;
    int largeur;

public:
    QSortFilterProxyModel *proxyModel;
    QGroupBox *sourceGroupBox;
    QGroupBox *proxyGroupBox;
    QTableView *sourceView;
    QCheckBox *filterCaseSensitivityCheckBox;
    QCheckBox *sortCaseSensitivityCheckBox;
    QLabel *filterPatternLabel;
    QLabel *filterSyntaxLabel;
    QLabel *filterColumnLabel;
};

#endif // FENETRE_H
