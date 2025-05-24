#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

struct Transaction {
    int transactionID;
    char type[10];
    double amount;
    char date[20];
    struct Transaction* next; 
};

struct BankAccount {
    int accountNumber;
    char accountHolderName[50];
    double balance;
    char password[20];  // Add password field
    struct Transaction* transactionHead; 
    int numTransactions;
    struct BankAccount* next; 
};

struct BankAccount* accountHead = NULL;

char *getCurrentDate() {
    time_t t;
    time(&t);
    struct tm *tm = localtime(&t);
    static char date[20];
    strftime(date, sizeof(date), "%d-%m-%Y", tm);
    return date;
}

void createAccount() {
    struct BankAccount* newAccount = (struct BankAccount*)malloc(sizeof(struct BankAccount));
    printf("Enter account number: ");
    scanf("%d", &newAccount->accountNumber);
    printf("Enter account holder name: ");
    scanf("%s", newAccount->accountHolderName);
    printf("Set a password: ");  // Prompt for password
    scanf("%s", newAccount->password);
    newAccount->balance = 0;
    newAccount->numTransactions = 0;
    newAccount->transactionHead = NULL;
    newAccount->next = accountHead;
    accountHead = newAccount;
    printf("Account created successfully!\n");
}

struct BankAccount* findAccount(int accountNumber) {
    struct BankAccount* current = accountHead;
    while (current != NULL) {
        if (current->accountNumber == accountNumber) {
            return current;
        }
        current = current->next;
    }
    return NULL;
}

int verifyPassword(struct BankAccount* account) {
    char password[20];
    printf("Enter your password: ");
    scanf("%s", password);
    return strcmp(password, account->password) == 0;
}

void addTransaction(struct BankAccount* account, char* type, double amount) {
    struct Transaction* newTransaction = (struct Transaction*)malloc(sizeof(struct Transaction));
    newTransaction->transactionID = account->numTransactions++;
    strcpy(newTransaction->type, type);
    newTransaction->amount = amount;
    strcpy(newTransaction->date, getCurrentDate());
    newTransaction->next = account->transactionHead;
    account->transactionHead = newTransaction;
}

void depositCash(int accountNumber) {
    struct BankAccount* account = findAccount(accountNumber);
    if (account != NULL) {
        if (verifyPassword(account)) {  // Verify password
            double amount;
            printf("Enter amount to deposit: ");
            scanf("%lf", &amount);
            account->balance += amount;
            addTransaction(account, "deposit", amount);
            printf("Deposit successful! Current Balance: %.2lf\n", account->balance);
        } else {
            printf("Incorrect password.\n");
        }
    } else {
        printf("Account not found.\n");
    }
}

void withdrawCash(int accountNumber) {
    struct BankAccount* account = findAccount(accountNumber);
    if (account != NULL) {
        if (verifyPassword(account)) {  // Verify password
            double amount;
            printf("Enter amount to withdraw: ");
            scanf("%lf", &amount);
            if (amount > account->balance) {
                printf("Withdrawal unsuccessful.\nNot valid balance. \nCurrent Balance: %.2lf\n", account->balance);
            } else {
                account->balance -= amount;
                addTransaction(account, "withdraw", amount);
                printf("Withdrawal successful! Current Balance: %.2lf\n", account->balance);
            }
        } else {
            printf("Incorrect password.\n");
        }
    } else {
        printf("Account not found.\n");
    }
}

void miniStatement(int accountNumber) {
    struct BankAccount* account = findAccount(accountNumber);
    if (account != NULL) {
        if (verifyPassword(account)) {  // Verify password
            printf("Account Number: %d\n", account->accountNumber);
            printf("Account Holder: %s\n", account->accountHolderName);
            printf("Balance: %.2lf\n", account->balance);
            printf("Transactions:\n");
            struct Transaction* transaction = account->transactionHead;
            while (transaction != NULL) {
                printf("%d\t%s\t%.2lf\t%s\n", transaction->transactionID, transaction->type, transaction->amount, transaction->date);
                transaction = transaction->next;
            }
        } else {
            printf("Incorrect password.\n");
        }
    } else {
        printf("Account not found.\n");
    }
}

int main() {
    int choice;
    int accountNumber;

    do {
        printf("\nBanking Operations:\n");
        printf("1. Create Account\n");
        printf("2. Deposit Cash\n");
        printf("3. Withdraw Cash\n");
        printf("4. Mini Statement\n");
        printf("5. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                createAccount();
                break;
            case 2:
                printf("Enter account number: ");
                scanf("%d", &accountNumber);
                depositCash(accountNumber);
                break;
            case 3:
                printf("Enter account number: ");
                scanf("%d", &accountNumber);
                withdrawCash(accountNumber);
                break;
            case 4:
                printf("Enter account number: ");
                scanf("%d", &accountNumber);
                miniStatement(accountNumber);
                break;
            case 5:
                printf("Exiting...\n");
                break;
            default:
                printf("Invalid choice. Please try again.\n");
        }
    } while (choice != 5);

    return 0;
}