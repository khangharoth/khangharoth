import wx
username="rashi"
passwd="rashi"
X=800
Y=600

class gui_main(wx.Frame):
	def __init__(self, parent, id, title):
		wx.Frame.__init__(self, parent, id, title,size=(X,Y))
		#self.vbox = wx.BoxSizer(wx.VERTICAL)
	        #self.panel1 = wx.Panel(self, -1,size=(800,500))
		self.tabbed1=wx.Notebook(self,-1,style=(wx.NB_TOP))
		self.panel1=wx.Panel(self.tabbed1,-1)
		wx.StaticText(self.panel1,-1,'Username:',(X/2-100,Y/2 - 150))
		self.in_username=wx.TextCtrl(self.panel1, -1, '', (X/2, Y/2 -150))
		wx.StaticText(self.panel1,-1,'Password:',(X/2-100,Y/2-100))        
		self.in_passwd=wx.TextCtrl(self.panel1,-1,'',(X/2,Y/2-100))
		self.b1=wx.Button(self.panel1,1,'Login',(X/2-50,Y/2-50))
		self.Bind(wx.EVT_BUTTON,self.OnLogin,id=1)
		#self.panel1.Show(True)

		
		self.panel2=wx.Panel(self.tabbed1,-1)
		self.tabbed2=wx.Notebook(self.panel2, -1, style=(wx.NB_LEFT),size=(X,Y))		
		self.panel3=wx.NotebookPage(self.tabbed2,-1)
		self.panel4=wx.NotebookPage(self.tabbed2,-1)
		self.tabbed2.AddPage(self.panel3,"Inventory")	        
		self.tabbed2.AddPage(self.panel4,"Reports")
		self.tabbed1.AddPage(self.panel1,"Welcome")
		self.tabbed1.AddPage(self.panel2,"Transactions")		
		#self.tabbed.Show(False)		
		#self.panel2.Show(False)
		#self.panel3.Show(False)
		#self.vbox.Add(self.panel1,0,wx.EXPAND)
		#self.vbox.Add(self.tabbed,1,wx.EXPAND)
				
		#self.SetSizer(self.vbox)
		self.CreateStatusBar()		
		self.Centre()
		self.Show(True)        
	def OnLogin(self,event):
		if username == self.in_username.GetValue() and passwd == self.in_passwd.GetValue():
			#self.vbox.Remove(0)		
			self.panel1.Show(False)
			#self.tabbed.Show(True)
			self.panel2.Show(True)
			self.panel3.Show(True)
			#self.SetSizer(self.vbox)
			#self.Centre()
			#self.Show(True) 
		else:
			wx.StaticText(self.panel1,-1,'Incorrect Username or Password',(X/2-100,(Y/2)))
		
app = wx.App()
gui_main(None, -1, 'Application')
app.MainLoop()
		
        
