import wx

userName="rashi"
pwd="rashi"

mainFrameWidth=800
mainFrameLength=600

class gui_main(wx.Frame):
    def __init__(self, parent, id, title):
        wx.Frame.__init__(self, parent, id, title,size=(mainFrameWidth,mainFrameLength))
        #creating the only child of self; is a Notebook
        self.notebook=wx.Notebook(self,-1,style=(wx.NB_TOP))

        # using panel names according to parent tabbed name;panelX_Y for tabbedX
        self.panel1_1=wx.Panel(self.notebook,-1)
        #using text,button variable according to parent panel name
        self.t1_1_1=wx.StaticText(self.panel1_1,-1,'Username:',(mainFrameWidth/2-100,mainFrameLength/2 - 150))
        self.in_username=wx.TextCtrl(self.panel1_1, -1, '', (mainFrameWidth/2, mainFrameLength/2 -150))
        self.t1_1_2=wx.StaticText(self.panel1_1,-1,'Password:',(mainFrameWidth/2-100,mainFrameLength/2-100))
        self.in_passwd=wx.TextCtrl(self.panel1_1,-1,'',(mainFrameWidth/2,mainFrameLength/2-100))
        self.b1_1_1=wx.Button(self.panel1_1,1,'Login',(mainFrameWidth/2-50,mainFrameLength/2-50))

        self.Bind(wx.EVT_BUTTON,self.OnLogin,id=1)

        self.b1_1_2=wx.Button(self.panel1_1,2,'Initialize',(mainFrameWidth/2-50,mainFrameLength/2-50))
        self.Bind(wx.EVT_BUTTON,self.OnInitialize,id=2)
        self.t1_1_3=wx.StaticText(self.panel1_1,-1,'Incorrect Username or Password',(mainFrameWidth/2-100,(mainFrameLength/2)))
        self.t1_1_3.Show(False)
        self.b1_1_2.Show(False)

        self.panel1_2=wx.Panel(self.notebook,-1)
        self.tabbed2=wx.Notebook(self.panel1_2, -1, style=(wx.NB_LEFT),size=(mainFrameWidth,mainFrameLength))
        self.panel2_1=wx.Panel(self.tabbed2,-1)
        self.panel2_2=wx.Panel(self.tabbed2,-1)
        self.panel2_3=wx.Panel(self.tabbed2,-1)
        self.panel2_4=wx.Panel(self.tabbed2,-1)

        self.tabbed2.AddPage(self.panel2_1,"Inventory")
        self.tabbed2.AddPage(self.panel2_2,"Daily Transactions")
        self.tabbed2.AddPage(self.panel2_3,"Bank Deposits")
        self.tabbed2.AddPage(self.panel2_4,"Reports")
        self.notebook.AddPage(self.panel1_1,"Welcome")
        self.notebook.AddPage(self.panel1_2,"Transactions")
        self.panel1_2.Show(False)	# will appear only after login

        self.CreateStatusBar()
        self.Centre()
        self.Show(True)
    def OnLogin(self,event):
        if userName == self.in_username.GetValue() and pwd == self.in_passwd.GetValue():
            self.t1_1_1.Show(False)
            self.t1_1_2.Show(False)
            self.in_username.Show(False)
            self.in_passwd.Show(False)
            self.b1_1_1.Show(False)
            self.t1_1_3.Show(False)
            self.b1_1_2.Show(True)
            self.panel1_2.Show(True)
        else:
            self.t1_1_3.Show(True)
    def OnInitialize(self,event):
    ##code to show widgets to initialize
        pass
app = wx.App()
gui_main(None, -1, 'Application')
app.MainLoop()
		
        
