import wx
from com.petroSoft.gui.custom.LabelText import LabelText
from com.petroSoft.Constants import constants
from com.petroSoft.Delegate import Delegate
from functools import partial

c=constants()
class AskPwd(wx.Dialog):
    def __init__(self,parent,id,title,delegate):
        wx.Dialog.__init__(self, parent, id, title, size=(500, 500))
        self.pwdLabelText=LabelText(c.PWDLABEL, (50,80), self,wx.TE_LINEWRAP)
        submitButton=wx.Button(self,1,c.LOGINLABEL,(50, 130))
        submitButton.Bind(wx.EVT_BUTTON, partial(self.OnSubmit,delegate),id=1)

    def OnSubmit(self,delegate,event):
        return delegate.authenticate(self.pwdLabelText.getValue())

    