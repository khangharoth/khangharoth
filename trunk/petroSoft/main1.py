import wx
from component.InitializePanel import InitializePanel
from component.WelcomePanel import WelcomePanel
from component.constants import constants

c=constants()

class mainFrame(wx.Frame):

    def __init__(self, title):
        wx.Frame.__init__(self, None, c.defaultId, title,size=(800,600))
        self.createNoteBook()

        self.CreateStatusBar()
        self.Centre()
        self.Show(True)



    def createNoteBook(self):
        c=constants()
        noteBook= wx.Notebook(self, c.defaultId, style=(wx.NB_TOP))

        obj=InitializePanel()
        InitializePanelObj=obj.createInitializePanel(noteBook)
        noteBook.AddPage(InitializePanelObj,c.INITIALIZETAB)

        obj=WelcomePanel()
        WelcomePanelObj= obj.createWelcomePanel(noteBook,InitializePanelObj)
        noteBook.AddPage( WelcomePanelObj,c.WELCOMETAB)

        return noteBook


app = wx.App()
mainFrame( 'Petro Soft - ')
app.MainLoop()


