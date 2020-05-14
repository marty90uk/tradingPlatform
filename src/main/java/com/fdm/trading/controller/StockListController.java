package com.fdm.trading.controller;

import com.fdm.trading.dao.StocksDao;
import com.fdm.trading.domain.Account;
import com.fdm.trading.domain.Stocks;
import com.fdm.trading.domain.Transaction;
import com.fdm.trading.service.accountServiceImpl.AccountServiceImpl;
import com.fdm.trading.service.stocksServiceImpl.StockServiceImpl;
import com.fdm.trading.service.transactionService.TransactionService;
import com.fdm.trading.service.transactionService.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StockListController {

    private final StocksDao stocksDao;

    @Autowired
    StockServiceImpl stockService;

    @Autowired
    TransactionServiceImpl transService;

    @Autowired
    AccountServiceImpl accountService;

    public StockListController(StocksDao stocksDao) {
        this.stocksDao = stocksDao;
    }


    @RequestMapping(value = "/stocks", method = RequestMethod.GET)
    public String getStocks(Model model, HttpSession session){
        List<Stocks> stocksList = this.stockService.findAll();
        Account account = (Account) session.getAttribute("account");
        System.out.println(stocksList);
        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
        System.out.println(";;;;;;;;"+account+";;;;;;");
        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
        model.addAttribute("account", account);
        model.addAttribute("stocks", stocksList);
        return "stocks";
    }

    @RequestMapping(value = "/stocks/purchase/{id}", method = RequestMethod.GET)
    public String getPurchase(@PathVariable int id, Model model){
        Stocks stocks = stockService.findByStockId(id);
        System.out.println(stocks.getCompany());
        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
        model.addAttribute("stocks", stocks);
        return "purchase";
    }

    @RequestMapping(value = "/stocks/purchase/{id}", method = RequestMethod.POST)
    public String postPurchase(@PathVariable int id, Model model, @ModelAttribute Transaction transaction, HttpSession session){
        Account account = (Account) session.getAttribute("account");
        transService.createPurchaseTransaction(id, (int) account.getAccountId(), transaction.getVolume());
        Stocks stocks = stockService.findByStockId(id);
        model.addAttribute("stocks", stocks);
        model.addAttribute("transaction", transaction);
        return "purchase-success";
    }
}
