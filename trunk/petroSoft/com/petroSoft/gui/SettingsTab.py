import wx
from com.petroSoft.gui.custom.LabelText import LabelText
from com.petroSoft.gui.custom.AskPassword import AskPwd
from com.petroSoft.Delegate import Delegate
from functools import partial
from com.petroSoft.Constants import constants

c=constants()
class AuthenticatePanel:
    def createAuthenticatePanel(self,parent):
        authenticatePanel=wx.Panel(parent, c.defaultId, size=(800, 600))
        authenticatePanel.authenticateLabel=wx.StaticText(authenticatePanel,c.defaultId,c.AUTHENTICATE,(50,50))
        authenticatePanel.pwdLabelText=LabelText(c.PWDLABEL, (50,80), authenticatePanel,wx.TE_LINEWRAP)

        authenticatePanel.loginButton=wx.Button(authenticatePanel,1,c.SUBMIT,(50, 130))
        authenticatePanel.loginFailedTxt=wx.StaticText(authenticatePanel,c.defaultId,c.LOGINFAILEDLABEL,(50,160))
        authenticatePanel.loginFailedTxt.Show(False)
        authenticatePanel.isValid=False

        return  authenticatePanel



class InitPanel:
    def createInitPanel(self,parent):
        initPanel=wx.Panel(parent, c.defaultId, size=(800, 600))
        initPanel.priceButton=wx.Button(initPanel,1,c.EDIT_PRICE_LABEL,(50, 130))
        initPanel.Bind(wx.EVT_BUTTON, partial(self.OnEditPrice,initPanel),id=1)
        return initPanel

    def OnEditPrice(self,initPanel,event):
        dial = wx.MessageDialog(None, 'Are you sure to edit current petrol and diesel price?', 'Question', wx.YES_NO | wx.NO_DEFAULT | wx.ICON_QUESTION)
        ret=dial.ShowModal()
        if ret==wx.ID_YES:
            print 'yes'
            print ret
        else:
            print 'no'


class SettingsTab:
    def createSettingsTab(self, noteBook):

        settingsPanel= wx.Panel(noteBook, c.defaultId, size=(500, 500))
        hbox=wx.BoxSizer(wx.HORIZONTAL)
        settingsPanel.initPanel=InitPanel().createInitPanel(settingsPanel)
        hbox.Add(settingsPanel.initPanel)
        settingsPanel.authenticatePanel=AuthenticatePanel().createAuthenticatePanel(settingsPanel)
        hbox.Add(settingsPanel.authenticatePanel)
        settingsPanel.authenticatePanel.Bind(wx.EVT_BUTTON,partial(self.OnClick,settingsPanel.authenticatePanel,settingsPanel.initPanel))
        settingsPanel.Bind(wx.EVT_SET_FOCUS,partial(self.OnFocus,settingsPanel.authenticatePanel,settingsPanel.initPanel))
        settingsPanel.SetSizer(hbox)
        settingsPanel.initPanel.Show(False)
        return  settingsPanel
    def OnClick(self,authenticatePanel,initPanel,event):
        obj =Delegate()
        isValid=obj.authenticate(authenticatePanel.pwdLabelText.getValue())
        if isValid :
            authenticatePanel.Show(False)
            initPanel.Show(True)
        else:
            authenticatePanel.loginFailedTxt.Show(True)
    def OnFocus(self,authenticatePanel,initPanel,event):
        authenticatePanel.Show(True)
        initPanel.Show(False)
        