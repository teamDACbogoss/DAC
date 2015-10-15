#include <QApplication>
#include <QObject>
#include <QWidget>
#include <QStackedWidget>
#include <QPushButton>
#include "time.h"
#include "menuprincipal.h"
#include "controleur.h"

int main(int argc, char *argv[])
{
    srand (time(NULL));
    QApplication app(argc, argv);
    menuPrincipal *accueil = new menuPrincipal();
    accueil->show();
    return app.exec();
}

