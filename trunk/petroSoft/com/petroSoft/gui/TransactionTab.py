REPORTS = "Reports"
BANK_DEPOSIT = "Bank Deposits"
DAILY_TRANSACTION = "Daily Transactions"
STOCK = "Stock"

import wx

from com.petroSoft.Constants import constants
from com.petroSoft.gui.StockPanel import StockPanel
c=constants()

class TransactionTab:
    def addTab(self, noteBook, name):
           noteBook.AddPage(wx.Panel(noteBook,c.defaultId), name)




    def createTransactionTab(self,parent):
           transactionPanel=wx.Panel(parent,c.defaultId)
           noteBook=wx.Notebook(transactionPanel, c.defaultId, style=(wx.NB_LEFT),size=(800,600))
           

           noteBook.AddPage(StockPanel().createStockPanel(noteBook), STOCK)

           self.addTab(noteBook, DAILY_TRANSACTION)
           self.addTab(noteBook, BANK_DEPOSIT)
           self.addTab(noteBook, REPORTS)


           return transactionPanel



    