REPORTS = "Reports"
BANK_DEPOSIT = "Bank Deposits"
DAILY_TRANSACTION = "Daily Transactions"
STOCK = "Stock"

import wx

from com.petroSoft.Constants import constants
from com.petroSoft.Delegate import Delegate
from com.petroSoft.gui.custom.LabelText import LabelText
from com.petroSoft.gui.custom.LabelValue import LabelValue
from functools import partial
c=constants()

class TransactionTab:
    def addTab(self, noteBook, name):
           noteBook.AddPage(wx.Panel(noteBook,c.defaultId), name)


    def populateCurrentStock(self, stockPanel):
        currentStock = self.delegate.getStock()
        stockPanel.currentPetrolStock = LabelValue(c.CURRENT_PETROL_STOCK, currentStock[0], (50, 250), stockPanel)
        stockPanel.currentDieselStock = LabelValue(c.CURRENT_DIESEL_STOCK, currentStock[1], (50, 300), stockPanel)

    def addStockTab(self, noteBook):
        stockPanel = wx.Panel(noteBook, c.defaultId)
        stockPanel.petrolLabelText = LabelText(c.PETROL, (50, 50), stockPanel)
        stockPanel.dieselLabelText = LabelText(c.Diesel, (50, 150), stockPanel)
        submitId = 1
        stockPanel.submit = wx.Button(stockPanel, submitId, c.SUBMIT, (50, 180))
        stockPanel.Bind(wx.EVT_BUTTON, partial(self.OnSubmit, stockPanel), id=submitId)

        self.populateCurrentStock(stockPanel)

        noteBook.AddPage(stockPanel, STOCK)

    def createTransactionTab(self,parent):
           transactionPanel=wx.Panel(parent,c.defaultId)
           noteBook=wx.Notebook(transactionPanel, c.defaultId, style=(wx.NB_LEFT),size=(800,600))
           self.delegate =Delegate()

           self.addStockTab(noteBook)

           self.addTab(noteBook, DAILY_TRANSACTION)
           self.addTab(noteBook, BANK_DEPOSIT)
           self.addTab(noteBook, REPORTS)


           return transactionPanel

    def OnSubmit(self,stockPanel,event):

           self.delegate.addToStock(stockPanel.petrolLabelText.getValue(),stockPanel.dieselLabelText.getValue())

           self.populateCurrentStock(stockPanel)

    