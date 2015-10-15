#ifndef BOURSE_H
#define BOURSE_H

#include <QObject>
#include <string>
#include <map>
#include "action.h"
#include <QDateTime>

class Bourse
{
    std::string nomBourse;
    std::map<std::string,Action> actionSet;
    QDateTime derniereActu;
public:

    Bourse();
    Bourse(std::string nom);

    /* get all action dans un set
     * return actionSet
     */
    std::map<std::string,Action> getActionSet();

    std::string getNom();
    /*ajouter une action dans le bourse
     * action : l'action qu'on veut ajouter dans le bourse
     * si action déjà existe, on ne fait rien
     */
    void addAction(Action *ac);

    void deleteAction(std::string id);

    float getPrix(std::string idAction);

    Action getAction(std::string id);

    QDateTime getActu();

    void setQuantite();

    void setPrix(int duree);

    float acheterAction(std::string id, int qtt);

    float vendreAction(std::string id, int qtt);

    void setActu();

    ~Bourse();

signals:

public slots:

private:
    void initCAC40();
};

#endif // BOURSE_H
