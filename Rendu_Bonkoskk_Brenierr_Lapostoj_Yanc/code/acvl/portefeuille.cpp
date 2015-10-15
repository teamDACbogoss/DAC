#include "portefeuille.h"
#include <assert.h>
#include <iostream>
#include <QTextStream>


Portefeuille::Portefeuille()
{
}

void Portefeuille::addTransaction(const Transaction &t){
    actionTransaction[t.getIdAction()].insert(t);
}


map<string, set<Transaction> > Portefeuille::getTransactions() {
    return actionTransaction;
}


Portefeuille::~Portefeuille()
{

}

