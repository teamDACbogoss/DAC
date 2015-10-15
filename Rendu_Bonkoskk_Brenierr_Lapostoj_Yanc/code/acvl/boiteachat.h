#ifndef BOITEACHAT_H
#define BOITEACHAT_H

#include <QWidget>
#include <QDialog>

class boiteAchat : public QDialog
{
    Q_OBJECT

    public:
    boiteAchat();
    boiteAchat(QWidget *parent);
    ~boiteAchat();
};

#endif // BOITEACHAT_H
