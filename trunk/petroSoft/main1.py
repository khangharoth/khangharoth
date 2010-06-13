import wx

from component.WelcomeTab import WelcomeTab
from component.TransactionTab import TransactionTab
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


        noteBook.AddPage( WelcomeTab().createWelcomePanel(noteBook),c.WELCOMETAB)
        noteBook.AddPage( TransactionTab().createTransactionTab(noteBook),c.TRANSACTION_TAB)



        return noteBook


app = wx.App()
mainFrame( 'Petro Soft - ')
app.MainLoop()


