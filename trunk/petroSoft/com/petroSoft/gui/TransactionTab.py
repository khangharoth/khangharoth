REPORTS = "Reports"
BANK_DEPOSIT = "Bank Deposits"
DAILY_TRANSACTION = "Daily Transactions"
STOCK = "Stock"

import wx
from com.petroSoft.Delegate import Delegate
from com.petroSoft.Constants import constants
from com.petroSoft.gui.StockPanel import StockPanel
from com.petroSoft.gui.DailyTransactionsPanel import DTPanel
from com.petroSoft.gui.BankDepositPanel import BankPanel
c=constants()

class TransactionTab:
    def addTab(self, noteBook, name):
           noteBook.AddPage(wx.Panel(noteBook,c.defaultId), name)




    def createTransactionTab(self,parent):
           transactionPanel=wx.Panel(parent,c.defaultId)
           noteBook=wx.Notebook(transactionPanel, c.defaultId, style=(wx.NB_LEFT),size=(800,600))
           
           self.Delegate=Delegate()
           noteBook.AddPage(StockPanel().createStockPanel(noteBook,self.Delegate), STOCK)
           noteBook.AddPage(DTPanel().createDTPanel(noteBook,self.Delegate), DAILY_TRANSACTION)
           noteBook.AddPage(BankPanel().createBankPanel(noteBook,self.Delegate), BANK_DEPOSIT)

           self.addTab(noteBook, REPORTS)


           return transactionPanel



    