import wx
defaultId=-1

class LabelText:
    def __init__(self, label, cord, welcomePanel):
        self.text_userName = wx.StaticText(welcomePanel, defaultId, label, (cord[0], cord[1]))
        self.userName = wx.TextCtrl(welcomePanel, defaultId, '', (cord[0] + 100, cord[1]))

