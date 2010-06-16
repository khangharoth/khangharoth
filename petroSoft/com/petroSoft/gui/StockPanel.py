
import wx

from com.petroSoft.Constants import constants
from com.petroSoft.Delegate import Delegate
from com.petroSoft.gui.custom.LabelText import LabelText
from functools import partial
c=constants()



class StockPanel:
     def createStockPanel(self,notebook):
        self.delegate =Delegate()
        stockPanel = wx.Panel(notebook, c.defaultId)
        stockPanel.petrolLabelText = LabelText(c.PETROL, (50, 50), stockPanel,wx.TE_LINEWRAP)
        stockPanel.dieselLabelText = LabelText(c.Diesel, (50, 150), stockPanel,wx.TE_LINEWRAP)
        stockPanel.currentPetrolStock = LabelText(c.CURRENT_PETROL_STOCK, (50, 250), stockPanel,wx.TE_READONLY)
        stockPanel.currentDieselStock = LabelText(c.CURRENT_DIESEL_STOCK,  (50, 300), stockPanel,wx.TE_READONLY)

        submitId = 1
        stockPanel.submit = wx.Button(stockPanel, submitId, c.SUBMIT, (50, 180))
        stockPanel.Bind(wx.EVT_BUTTON, partial(self.OnSubmit, stockPanel), id=submitId)
        stockPanel.countSubmit=0
        self.populateCurrentStock(stockPanel)
        return stockPanel


     def populateCurrentStock(self, stockPanel):
        currentStock = self.delegate.getStock()
        stockPanel.currentPetrolStock.text.SetValue(str(currentStock[0]))
        stockPanel.currentDieselStock.text.SetValue(str(currentStock[1]))

     def OnSubmit(self,stockPanel,event):
           self.delegate.addToStock(stockPanel.petrolLabelText.getValue(),stockPanel.dieselLabelText.getValue())
           self.populateCurrentStock(stockPanel)