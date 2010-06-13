import wx
from com.petroSoft.gui.custom.LabelText import LabelText
from functools import partial
from com.petroSoft.Constants import constants
c=constants()

class TransactionTab:
    def addTab(self, noteBook, someName):
           noteBook.AddPage(wx.Panel(noteBook,c.defaultId), someName)

    def createTransactionTab(self,parent):
           transactionPanel=wx.Panel(parent,c.defaultId)
           noteBook=wx.Notebook(transactionPanel, c.defaultId, style=(wx.NB_LEFT),size=(800,600))

           self.addTab(noteBook, "Inventory")
           self.addTab(noteBook,"Daily Transactions")
           self.addTab(noteBook, "Bank Deposits")
           self.addTab(noteBook, "Reports")


           return transactionPanel
    