#ifndef BOUTONMENU_H
#define BOUTONMENU_H

#include <QPushButton>
#include "fenetre.h"

class boutonMenu : public QPushButton
{
public:
    boutonMenu();
    boutonMenu(const QString& str, QWidget* parent);
    ~boutonMenu();

signals:
    void clickBouton(fenetre *parent);
};

#endif // BOUTONMENU_H
