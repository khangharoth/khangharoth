import wx
from com.petroSoft.gui.LabelText import LabelText
from component.Delegate import Delegate
from functools import partial
from com.petroSoft.constants import constants

c=constants()
class WelcomeTab:
    def createWelcomePanel(self, noteBook):

        welcomePanel= wx.Panel(noteBook, c.defaultId, size=(100, 100))


        welcomePanel.userNameLabelText=LabelText(c.USERNAMELABEL, (50,50), welcomePanel)
        welcomePanel.pwdLabelText=LabelText(c.PWDLABEL, (50,80), welcomePanel)

        welcomePanel.loginButton=wx.Button(welcomePanel,1,c.LOGINLABEL,(50, 130))
        welcomePanel.loginFailedTxt=wx.StaticText(welcomePanel,c.defaultId,c.LOGINFAILEDLABEL,(50,180))
        welcomePanel.loginFailedTxt.Show(False)
        
        welcomePanel.Bind(wx.EVT_BUTTON, partial(self.OnLogin,welcomePanel),id=1)

        return  welcomePanel

        

    def OnLogin(self,welcomePanel,event):
           obj =Delegate()
           value=obj.checkUser(welcomePanel.userNameLabelText.userName.GetValue(),welcomePanel.pwdLabelText.userName.GetValue())

           if value :
               welcomePanel.Show(False)
               print 'Welcome user'
           else :
               welcomePanel.loginFailedTxt.Show(True)



