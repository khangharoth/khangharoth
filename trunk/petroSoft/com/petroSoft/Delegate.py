class Delegate:
    def __init__(self):
         self.petrolQunt=0
         self.dieselQuantity=0

    def checkUser(self,userName,pwd):
          if userName=='rashi' and pwd=='rashi':
            return True
          else :
            return  False

    def addToStock(self,petrolQuantity,dieselQuantity):

        self.petrolQunt=self.petrolQunt + int(petrolQuantity)
        self.dieselQuantity=self.dieselQuantity+int(dieselQuantity)

    def getStock(self):
        return (self.petrolQunt,self.dieselQuantity)

