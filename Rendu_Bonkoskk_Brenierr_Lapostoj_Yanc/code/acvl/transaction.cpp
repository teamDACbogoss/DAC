#include "transaction.h"
int id_transaction = 0;
Transaction::Transaction(float prix, int quantite,string idAction)
{
    this->idTransaction = id_transaction+1;
    id_transaction++;
    this->prix = prix;
    this->quantite = quantite;
    this->dateTime = time(&dateTime);
    this->idAction = idAction;
}

bool operator< (const Transaction& tran,const Transaction& tran2){
    return tran.getDateTime() < tran2.getDateTime();
}


int Transaction::getIdTransaction(){
    return idTransaction;
}

float Transaction::getPrix() const{
    return prix;
}

int Transaction::getQuantite() const {
    return quantite;
}

time_t Transaction::getDateTime() const{
    return dateTime;
}

string Transaction::getIdAction()const {
    return idAction;
}


Transaction::~Transaction()
{

}

