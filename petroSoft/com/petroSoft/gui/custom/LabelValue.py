import wx
defaultId=-1

class LabelValue:
    def __init__(self, label,value, cord, parent):
        self.label = wx.StaticText(parent, defaultId, label, (cord[0], cord[1]))
        self.value = wx.StaticText(parent, defaultId, str(value), (cord[0] + 500, cord[1]))


