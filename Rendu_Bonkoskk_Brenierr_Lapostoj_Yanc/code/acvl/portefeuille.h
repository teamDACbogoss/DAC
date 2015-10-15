#ifndef PORTEFEUILLE_H
#define PORTEFEUILLE_H

#include <set>
#include <map>
#include "transaction.h"
#include "action.h"
using namespace std;
class Portefeuille
{

    map<string, set<Transaction> > actionTransaction;


public:
    Portefeuille();
    map<string, set<Transaction> > getTransactions();
    void addTransaction(const Transaction &t);
    ~Portefeuille();
};

#endif // PORTEFEUILLE_H
