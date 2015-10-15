#include "boutonmenu.h"

boutonMenu::boutonMenu()
{

}

boutonMenu::boutonMenu(const QString& str, QWidget* parent) : QPushButton(str, parent)
{

}

boutonMenu::~boutonMenu()
{

}

void boutonMenu::clickBouton(fenetre *parent)
{
    this->clicked();
}
