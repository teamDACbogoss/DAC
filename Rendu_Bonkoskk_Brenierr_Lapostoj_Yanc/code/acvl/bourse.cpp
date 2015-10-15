#include "bourse.h"

#include <QDate>

Bourse::Bourse(){
    nomBourse = "BourseSansNom";
}

Bourse::Bourse(std::string nom){
    nomBourse = nom;
    initCAC40();
}

Bourse::~Bourse()
{

}

void Bourse::initCAC40()
{
    this->addAction(new Action("AC", "Accor", 0, 51.34, 51.34));
    this->addAction(new Action("AI", "Air Liquide", 0, 123.20, 123.20));
    this->addAction(new Action("ALU", "Alcatel-Lucent", 0, 3.800, 3.800));
    this->addAction(new Action("AIR", "Airbus Group", 0, 64.54, 64.54));
    this->addAction(new Action("ALO", "Alstom", 0, 28.665, 28.665));
    this->addAction(new Action("MT", "ArcelorMittal", 0, 8.835, 8.835));
    this->addAction(new Action("CS", "AXA", 0, 23.500, 23.500));
    this->addAction(new Action("BNP", "BNP Paribas", 0, 55.59, 55.59));
    this->addAction(new Action("EN", "Bouygues", 0, 37.890, 37.890));
    this->addAction(new Action("CAP", "Cagemini", 0, 78.77, 78.77));
    this->addAction(new Action("CA", "Carrefour", 0, 31.930, 31.930));
    this->addAction(new Action("ACA", "Crédit Agricole", 0, 13.425, 13.425));
    this->addAction(new Action("EDF", "EDF", 0, 22.880, 22.880));
    this->addAction(new Action("EI", "Essilor", 0, 111.70, 111.70));
    this->addAction(new Action("GSZ", "GDF Suez", 0, 19.300, 19.300));
    this->addAction(new Action("BN", "Groupe Danone", 0, 66.22, 66.22));
    this->addAction(new Action("KER", "Kering", 0, 178.00, 178.00));
    this->addAction(new Action("OR", "L'Oréal", 0, 178.15, 178.15));
    this->addAction(new Action("LG", "Lafarge", 0, 65.79, 65.79));
    this->addAction(new Action("LR", "Legrand", 0, 52.70, 52.70));
    this->addAction(new Action("MC", "LVMH", 0, 164.10, 164.10));
    this->addAction(new Action("ML", "Michelin", 0, 95.76, 95.76));
    this->addAction(new Action("ORA", "Orange", 0, 15.325, 15.325));
    this->addAction(new Action("RI", "Pernod Ricard", 0, 113.00, 113.00));
    this->addAction(new Action("PUB", "Publicis", 0, 76.81, 76.81));
    this->addAction(new Action("UG", "Peugeot", 0, 17.080, 17.080));
    this->addAction(new Action("RNO", "Renault", 0, 94.45, 94.45));
    this->addAction(new Action("SAF", "Safran", 0, 68.98, 68.98));
    this->addAction(new Action("SGO", "Saint-Gobain", 0, 41.365, 41.365));
    this->addAction(new Action("SAN", "Sanofi", 0, 95.91, 95.91));
    this->addAction(new Action("SU", "Schneider Electric", 0, 70.47, 70.47));
    this->addAction(new Action("GLE", "Société Générale", 0, 45.240, 45.240));
    this->addAction(new Action("SOLB", "Solvay", 0, 136.35, 136.35));
    this->addAction(new Action("TEC", "Technip", 0, 64.97, 64.97));
    this->addAction(new Action("FP", "Total", 0, 48.695, 48.695));
    this->addAction(new Action("UL", "Unibal-Rodamco", 0, 254.30, 254.30));
    this->addAction(new Action("FR", "Vleo", 0, 146.50, 146.50));
    this->addAction(new Action("VIE", "Veolia Environnement", 0, 19.175, 19.175));
    this->addAction(new Action("DG", "Vinci", 0, 57.09, 57.09));
    this->addAction(new Action("VIV", "Vivendi", 0, 22.890, 22.890));
}

void Bourse::setQuantite()
{
    int quantite = 0;
    std::map<std::string,Action>::iterator it;
    for(it=actionSet.begin() ; it!=actionSet.end() ; ++it)
    {
        quantite = rand() % 500;
        it->second.setQuantite(quantite);
    }
}

void Bourse::setPrix(int duree)
{
    float prix = 0;
    std::map<std::string,Action>::iterator it;
    for(it=actionSet.begin() ; it!=actionSet.end() ; ++it)
    {
        // tirage d'un pourcentage en -5% et 5% (variation considérée pour une journée)
        prix = (rand() % 10) - 5;
        // application d'une proportionnalité par rapport à la durée depuis la dernière mise à jour
        prix = prix*duree/(3600*24);
        prix = (1+prix)*it->second.getPrix();
        it->second.setPrix(prix);
    }
}

QDateTime Bourse::getActu()
{
    return derniereActu;
}

void Bourse::setActu()
{
    derniereActu = QDateTime::currentDateTime();
}

std::map<std::string,Action> Bourse::getActionSet(){
     return actionSet;
 }

std::string Bourse::getNom() {
    return nomBourse;
}

void Bourse::addAction(Action *ac){
    actionSet[(*ac).getId()] = *ac;
}

float Bourse::getPrix(std::string idAction) {
    return actionSet[idAction].getPrix();
}

void Bourse::deleteAction(std::string id){
    actionSet.erase(id);
}

Action Bourse::getAction(std::string id){
    return actionSet[id];
}

float Bourse::acheterAction(std::string id, int qtt)
{
    return actionSet.find(id)->second.acheterAction(qtt);
}

float Bourse::vendreAction(std::string id, int qtt)
{
    return actionSet.find(id)->second.vendreAction(qtt);
}
