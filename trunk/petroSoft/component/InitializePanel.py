import wx
from component.LabelText import LabelText
from component.Delegate import Delegate
from functools import partial
from component.constants import constants
c=constants()

class InitializePanel:
    def createInitializePanel(self, noteBook):
        InitializePanel= wx.Panel(noteBook, c.defaultId, size=(100, 100))

        InitializePanel.InitializeButton=wx.Button(InitializePanel,1,c.INITIALIZETAB,(50, 130))
        InitializePanel.Bind(wx.EVT_BUTTON, partial(self.OnInitialize,InitializePanel),id=1)

        InitializePanel.Show(False)



        return  InitializePanel
    def OnInitialize(self,InitializePanel,event):
        wx.MessageBox(c.INITIALIZEWARN,c.INITIALIZEMESSAGETITLE)
        


