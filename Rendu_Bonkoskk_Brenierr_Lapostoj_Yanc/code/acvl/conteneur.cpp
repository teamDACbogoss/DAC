#include "conteneur.h"

conteneur::conteneur() : QStackedWidget()
{

}

conteneur::~conteneur()
{

}

void conteneur::actionBouton(int i)
{
    this->setCurrentIndex(i);
}

