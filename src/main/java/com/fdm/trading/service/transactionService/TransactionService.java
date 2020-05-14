package com.fdm.trading.service.transactionService;

import com.fdm.trading.domain.Account;
import com.fdm.trading.domain.Stocks;
import com.fdm.trading.domain.Transaction;

public interface TransactionService {

    Transaction findByTransactionId(long transactionId);
    Transaction createPurchaseTransaction(int stockId, int accountId, long volume);
    public Transaction createSaleTransaction(int stockId, int accountId, long volume);

}
