import wx
from component.LabelText import LabelText
from component.Delegate import Delegate
from functools import partial
from component.constants import constants
c=constants()

class TransactionTab:
    def createTransactionTab(self,parent):
        transactionPanel=wx.Panel(parent,c.defaultId)
        noteBook=wx.Notebook(transactionPanel, c.defaultId, style=(wx.NB_LEFT))
        return transactionPanel
