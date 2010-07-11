class Delegate:
    def __init__(self):
         self.petrolQunt=0
         self.dieselQuantity=0
         self.petrolPrice=50
         self.dieselPrice=35
         self.credit=0
         self.bankCredit=0
         self.cursessionUser=None

    def checkUser(self,userName,pwd):
        if userName=='rashi' and pwd=='rashi':
            self.cursessionUser=userName
            return True
        else :
            return  False
    def authenticate(self,pwd):
        if pwd=='rashi': #for the username=self.cursessionUser
            return True
        else:
            return False


    def addToStock(self,petrolQuantity,dieselQuantity):
        self.petrolQunt=self.petrolQunt + int(petrolQuantity)
        self.dieselQuantity=self.dieselQuantity+int(dieselQuantity)

    def removeFromStock(self,petrolQuantity,dieselQuantity):
        self.petrolQunt=self.petrolQunt - int(petrolQuantity)
        self.dieselQuantity=self.dieselQuantity - int(dieselQuantity)

    def getStock(self):
        return (self.petrolQunt,self.dieselQuantity)

    def getPetrolPrice(self):
        return self.petrolPrice

    def getDieselPrice(self):
        return self.dieselPrice

    def addToCredit(self,petrolQuantity,dieselQuantity):
        self.credit=self.credit + int(petrolQuantity) * self.petrolPrice + int(dieselQuantity) * self.dieselPrice
    def addToDebit(self,amount):
        self.credit= self.credit- int(amount)

    def getCredit(self):
        return (self.credit)

    def addToBankCredit(self,deposit):
        self.bankCredit= self.bankCredit+int(deposit)

    def getBankCredit(self):
        return self.bankCredit
