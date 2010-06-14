import wx
defaultId=-1

class LabelText:
    def __init__(self, label, cord, welcomePanel):
        self.label = wx.StaticText(welcomePanel, defaultId, label, (cord[0], cord[1]))
        self.text = wx.TextCtrl(welcomePanel, defaultId, '', (cord[0] + 100, cord[1]))

    def getValue(self):
        return self.text.GetValue()

