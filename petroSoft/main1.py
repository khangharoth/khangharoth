import wx
from component.WelcomePanel import WelcomePanel

defaultId=-1

class mainFrame(wx.Frame):

    def __init__(self, title):
        wx.Frame.__init__(self, None, defaultId, title,size=(800,600))
        self.createNoteBook()

        self.CreateStatusBar()
        self.Centre()
        self.Show(True)



    def createNoteBook(self):
        noteBook= wx.Notebook(self, defaultId, style=(wx.NB_TOP))

        obj=WelcomePanel()
        welcomePanelObj= obj.createWelcomePanel(noteBook)

        noteBook.AddPage( welcomePanelObj,"Welcome")
        return noteBook


app = wx.App()
mainFrame( 'Petro Soft - ')
app.MainLoop()


