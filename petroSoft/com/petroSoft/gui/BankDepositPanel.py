
import wx
from com.petroSoft.Constants import constants
from com.petroSoft.gui.custom.LabelText import LabelText
from functools import partial

c=constants()



class BankPanel:
     def createBankPanel(self,notebook,Delegate):
        self.delegate =Delegate
        BankPanel = wx.Panel(notebook, c.defaultId)
        BankPanel.debitLabelText = LabelText(c.AMOUNT_DEPOSITED, (50, 50), BankPanel,wx.TE_LINEWRAP)
        BankPanel.currentTotalCredit = LabelText(c.TOTALCREDIT, (50, 250), BankPanel,wx.TE_READONLY)
        BankPanel.currentBankCredit = LabelText(c.BANK_CREDIT,  (50, 300), BankPanel,wx.TE_READONLY)

        submitId = 1
        BankPanel.submit = wx.Button(BankPanel, submitId, c.SUBMIT, (50, 180))
        BankPanel.Bind(wx.EVT_BUTTON, partial(self.OnSubmit, BankPanel), id=submitId)
        BankPanel.Bind(wx.EVT_SET_FOCUS,partial(self.populateCurrentTotalCredit, BankPanel) )
        self.populateCurrentTotalCredit(BankPanel,None)
        self.populateCurrentBankCredit(BankPanel)
        return BankPanel

     def populateCurrentTotalCredit(self,BankPanel,event):
        currentCredit=self.delegate.getCredit()
        BankPanel.currentTotalCredit.text.SetValue(str(currentCredit))

     def populateCurrentBankCredit(self, BankPanel):
        currentCredit = self.delegate.getBankCredit()
        BankPanel.currentBankCredit.text.SetValue(str(currentCredit))

     def OnSubmit(self,BankPanel,event):
           self.delegate.addToBankCredit(BankPanel.debitLabelText.getValue())
           self.delegate.addToDebit(BankPanel.debitLabelText.getValue())
           self.populateCurrentTotalCredit(BankPanel,None)
           self.populateCurrentBankCredit(BankPanel)