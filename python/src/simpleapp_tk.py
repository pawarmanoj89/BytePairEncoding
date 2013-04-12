'''
Created on Jan 31, 2012

@author: PG
'''
#!/usr/bin/python
# -*- coding: iso-8859-1 -*-

import Tkinter
import tkFont
from code import randomizeComp
class simpleapp_tk(Tkinter.Tk):
    def __init__(self,parent):
        Tkinter.Tk.__init__(self,parent)
        self.parent = parent
        self.initialize()

    def initialize(self):
        self.grid()
        self.geometry('550x300+200+200')
        self.customFont = tkFont.Font(family="Helvetica", size=20)
       
        self.labelVariableTitle = Tkinter.StringVar()
        label = Tkinter.Label(self,textvariable=self.labelVariableTitle,
                              anchor="center",fg="black",bg="Bisque",font=self.customFont,height="2")
        label.grid(column=0,row=0,columnspan=2,sticky='EW')
        self.labelVariableTitle.set(u" BPE Algorithm")
        
        self.entryVariable = Tkinter.StringVar()
        self.entry = Tkinter.Entry(self,textvariable=self.entryVariable,font=self.customFont)
        self.entry.grid(column=0,row=2,sticky='EW')
        self.entry.bind("<Return>", self.OnPressEnter)
        self.entryVariable.set(u"Enter text here.")

        button = Tkinter.Button(self,text=u"Compress",
                                command=self.OnButtonClick)
        button.grid(column=0,row=3)

       
        self.labelVariableLength1 = Tkinter.StringVar()
        labelLength = Tkinter.Label(self,textvariable=self.labelVariableLength1,
                              anchor="center",fg="black",font=self.customFont, height="1")
        labelLength.grid(column=2,row=2,columnspan=1,sticky='EW')
        self.labelVariableLength1.set(u"  ")
        
        self.labelVariableLength2 = Tkinter.StringVar()
        labelLength = Tkinter.Label(self,textvariable=self.labelVariableLength2,
                              anchor="center",fg="black",font=self.customFont, height="1")
        labelLength.grid(column=2,row=4,columnspan=1,sticky='EW')
        self.labelVariableLength2.set(u"  ")
        

        self.labelVariable1 = Tkinter.StringVar()
        label = Tkinter.Label(self,textvariable=self.labelVariable1,
                              anchor="w",fg="white",bg="Spring green",font=self.customFont)
        label.grid(column=0,row=4,columnspan=2,sticky='EW')
        self.labelVariable1.set(u" ")
        
        button = Tkinter.Button(self,text=u"Decompress",
                                command=self.OnButtonClick2)
        button.grid(column=0,row=5)


        self.labelVariable2 = Tkinter.StringVar()
        label = Tkinter.Label(self,textvariable=self.labelVariable2,
                              anchor="w",fg="white",bg="Bisque",font=self.customFont)
        label.grid(column=0,row=6,columnspan=2,sticky='EW')
        self.labelVariable2.set(u" ")
        
        self.grid_columnconfigure(0,weight=1)
        self.resizable(True,False)
        self.update()
           
        self.entry.focus_set()
        self.entry.selection_range(0, Tkinter.END)

    def OnButtonClick(self):
        obj=randomizeComp()
        compressedTxt=obj.compression( self.entryVariable.get())
        self.labelVariable1.set( compressedTxt )
        self.entry.focus_set()
        self.entry.selection_range(0, Tkinter.END)
        self.labelVariableLength1.set( len(self.entryVariable.get())) 
        self.labelVariableLength2.set( len(compressedTxt )) 

    def OnPressEnter(self,event):
        obj=randomizeComp()
        compressedTxt=obj.compression( self.entryVariable.get())
        self.labelVariable1.set( compressedTxt )
        self.entry.focus_set()
        self.entry.selection_range(0, Tkinter.END)
        self.labelVariableLength1.set( len(self.entryVariable.get())) 
        self.labelVariableLength2.set( len(compressedTxt )) 
        
    def OnButtonClick2(self):
        obj=randomizeComp()
        
        self.labelVariable2.set( obj.decompression( self.labelVariable1.get()) )
        self.entry.focus_set()
        self.entry.selection_range(0, Tkinter.END)
       

    def OnPressEnter2(self,event):
        obj=randomizeComp()
        
        self.labelVariable2.set( obj.decompression( self.labelVariable1.get()) )
        self.entry.focus_set()
        self.entry.selection_range(0, Tkinter.END)
         
if __name__ == "__main__":
    app = simpleapp_tk(None)
    app.title('Data Compression')
    app.mainloop()