#ifndef TRANSACTION_H
#define TRANSACTION_H

#include <string>
#include <ctime>
using namespace std;
class Transaction
{
    int idTransaction;
    float prix;
    int quantite;
    time_t dateTime;
    string idAction;
public:
    Transaction(float prix, int quantite, string idAction);


    int getIdTransaction();
    float getPrix() const;
    int getQuantite() const;
    time_t getDateTime() const;
    string getIdAction() const;

    ~Transaction();
};

bool operator< (const Transaction& tran,const Transaction& tran2);

#endif // TRANSACTION_H
