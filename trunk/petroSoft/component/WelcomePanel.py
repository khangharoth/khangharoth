import wx
from component.LabelText import LabelText
from component.Delegate import Delegate
from functools import partial
defaultId=-1

class WelcomePanel:
    def createWelcomePanel(self, noteBook):
        welcomePanel= wx.Panel(noteBook, defaultId, size=(100, 100))


        welcomePanel.userNameLabelText=LabelText('Username:', (50,50), welcomePanel)
        welcomePanel.pwdLabelText=LabelText('PassWord:', (50,80), welcomePanel)

        welcomePanel.loginButton=wx.Button(welcomePanel,1,'Login',(50, 130))


        
        welcomePanel.Bind(wx.EVT_BUTTON, partial(self.OnLogin,welcomePanel),id=1)

        return  welcomePanel

        

    def OnLogin(self,welcomePanel,event,):
           obj =Delegate()
           value=obj.checkUser(welcomePanel.userNameLabelText.userName.GetValue(),welcomePanel.pwdLabelText.userName.GetValue())

           if value==0 :
               print " Welcome User"
           else :
               print "Invalid User"



