import wx
username="rashi"
passwd="rashi"
X=800
Y=600

class gui_main(wx.Frame):
	def __init__(self, parent, id, title):
		wx.Frame.__init__(self, parent, id, title,size=(X,Y))
		#creating the only child of self; is a Notebook 
	        self.tabbed1=wx.Notebook(self,-1,style=(wx.NB_TOP))
		# using panel names according to parent tabbed name;panelX_Y for tabbedX
		self.panel1_1=wx.Panel(self.tabbed1,-1)
		#using text,button variable according to parent panel name
		self.t1_1_1=wx.StaticText(self.panel1_1,-1,'Username:',(X/2-100,Y/2 - 150))
		self.in_username=wx.TextCtrl(self.panel1_1, -1, '', (X/2, Y/2 -150))
		self.t1_1_2=wx.StaticText(self.panel1_1,-1,'Password:',(X/2-100,Y/2-100))        
		self.in_passwd=wx.TextCtrl(self.panel1_1,-1,'',(X/2,Y/2-100))
		self.b1_1_1=wx.Button(self.panel1_1,1,'Login',(X/2-50,Y/2-50))
		self.Bind(wx.EVT_BUTTON,self.OnLogin,id=1)
		self.b1_1_2=wx.Button(self.panel1_1,2,'Initialize',(X/2-50,Y/2-50))
		self.Bind(wx.EVT_BUTTON,self.OnInitialize,id=2)		
		self.t1_1_3=wx.StaticText(self.panel1_1,-1,'Incorrect Username or Password',(X/2-100,(Y/2)))
		self.t1_1_3.Show(False)		
		self.b1_1_2.Show(False)

		self.panel1_2=wx.Panel(self.tabbed1,-1)
		self.tabbed2=wx.Notebook(self.panel1_2, -1, style=(wx.NB_LEFT),size=(X,Y))		
		self.panel2_1=wx.Panel(self.tabbed2,-1)
		self.panel2_2=wx.Panel(self.tabbed2,-1)
		self.panel2_3=wx.Panel(self.tabbed2,-1)
		self.panel2_4=wx.Panel(self.tabbed2,-1)
		self.tabbed2.AddPage(self.panel2_1,"Inventory")	        
		self.tabbed2.AddPage(self.panel2_2,"Daily Transactions")
		self.tabbed2.AddPage(self.panel2_3,"Bank Deposits")
		self.tabbed2.AddPage(self.panel2_4,"Reports")
		self.tabbed1.AddPage(self.panel1_1,"Welcome")
		self.tabbed1.AddPage(self.panel1_2,"Transactions")
		self.panel1_2.Show(False)	# will appear only after login	

		self.CreateStatusBar()		
		self.Centre()
		self.Show(True)        
	def OnLogin(self,event):
		if username == self.in_username.GetValue() and passwd == self.in_passwd.GetValue():
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
		
        
