#include "boiteachat.h"

#include <QLineEdit>

boiteAchat::boiteAchat()
{

}

boiteAchat::boiteAchat(QWidget *parent) : QDialog(parent)
{
    setFixedSize(100, 100);
    QLineEdit idEdit (this);
    idEdit.setGeometry(100, 100, 100, 50);
    QString id = idEdit.text();
}

boiteAchat::~boiteAchat()
{

}

