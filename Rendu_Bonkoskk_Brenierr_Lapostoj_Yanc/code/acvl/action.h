#ifndef ACTION_H
#define ACTION_H

#include <QObject>
#include <string>

class Action
{
    std::string idAction;
    float prix;
    std::string emeteur;
    int quantite;
    float prixOuverture;
public:


    //explicit Action(QObject *parent = 0);
    Action();

    Action(std::string id, std::string emeteur, int quantite, float prixOuverture, float prix);

    /*accesseur id
     * renvoie id de l'action
     */
    float getPrix();
    float getPrixOuverture();
    std::string getEmeteur();
    int getQuantite();
    std::string getId();
    void setQuantite(int qtt);

    void setPrix(float prix);

    ~Action();

    //mettre à jour le prix d'une action
    void majPrix(int newPrix);
    void majPrixOuverture(float prix);

    /*vendre une action au marché
     * quantite : le nombre d'action qu'on veut vendre
     * on incrémente la quantité de action
     * renvoie le prix total de transaction
     */
    float vendreAction(int quantite);

    /*acheter une action
     * quantité : le nomnbre d'action qu'on veut acheter
     * si il n'y a pas assez, on leve une exception
     * renvoie le prix total d'achat
     */
    float acheterAction(int quantite);



signals:

public slots:
};

#endif // ACTION_H
