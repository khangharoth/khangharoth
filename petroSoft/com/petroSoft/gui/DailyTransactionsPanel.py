
import wx
from com.petroSoft.Constants import constants
from com.petroSoft.gui.custom.LabelText import LabelText
from functools import partial
c=constants()



class DTPanel:
     def createDTPanel(self,notebook,Delegate):
        self.delegate =Delegate
        DTPanel = wx.Panel(notebook, c.defaultId)
        DTPanel.petrolLabelText = LabelText(c.PETROLSALE, (50, 50), DTPanel,wx.TE_LINEWRAP)
        DTPanel.dieselLabelText = LabelText(c.DIESELSALE, (50, 150), DTPanel,wx.TE_LINEWRAP)
        DTPanel.currentPetrolStock = LabelText(c.CURRENT_PETROL_STOCK, (50, 250), DTPanel,wx.TE_READONLY)
        DTPanel.currentDieselStock = LabelText(c.CURRENT_DIESEL_STOCK,  (50, 300), DTPanel,wx.TE_READONLY)
        wx.StaticText(DTPanel,c.defaultId,c.PETROLPRICE+str(self.delegate.getPetrolPrice())+c.LITRE ,(500,50))
        wx.StaticText(DTPanel,c.defaultId,c.DIESELPRICE+str(self.delegate.getDieselPrice())+c.LITRE,(500,150))
        DTPanel.currentTotalCredit = LabelText(c.TOTALCREDIT,  (50, 450), DTPanel,wx.TE_READONLY)

        submitId = 1
        DTPanel.submit = wx.Button(DTPanel, submitId, c.SUBMIT, (50, 180))
        DTPanel.Bind(wx.EVT_BUTTON, partial(self.OnSubmit, DTPanel), id=submitId)
        DTPanel.Bind(wx.EVT_SET_FOCUS,partial(self.OnFocus,DTPanel) )
        self.populateCurrentStock(DTPanel)
        self.populateCurrentCredit(DTPanel)
        return DTPanel

     def OnFocus(self,DTPanel,event):
         self.populateCurrentCredit(DTPanel)
         self.populateCurrentStock(DTPanel)

     def populateCurrentCredit(self,DTPanel):
        currentCredit=self.delegate.getCredit()
        DTPanel.currentTotalCredit.text.SetValue(str(currentCredit))

     def populateCurrentStock(self, DTPanel):
        currentStock = self.delegate.getStock()
        DTPanel.currentPetrolStock.text.SetValue(str(currentStock[0]))
        DTPanel.currentDieselStock.text.SetValue(str(currentStock[1]))

     def OnSubmit(self,DTPanel,event):
           self.delegate.removeFromStock(DTPanel.petrolLabelText.getValue(),DTPanel.dieselLabelText.getValue())
           self.delegate.addToCredit(DTPanel.petrolLabelText.getValue(),DTPanel.dieselLabelText.getValue())
           self.populateCurrentStock(DTPanel)
           self.populateCurrentCredit(DTPanel)