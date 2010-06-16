import wx
defaultId=-1

class LabelText:
    def __init__(self, label, cord, welcomePanel,styleVar):
        self.label = wx.StaticText(welcomePanel, defaultId, label, (cord[0], cord[1]))
        self.text = wx.TextCtrl(welcomePanel, defaultId, '', (cord[0] + 300, cord[1]),style = styleVar)


    def getValue(self):
        return self.text.GetValue()

