#ifndef CONTENEUR_H
#define CONTENEUR_H

#include <QStackedWidget>

class conteneur : public QStackedWidget
{
    public:
    conteneur();
    ~conteneur();

    public slots:
    void actionBouton(int i);
};

#endif // CONTENEUR_H
