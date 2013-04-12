'''
Created on Jan 25, 2012
   for j in range(i,strlen-1):  & y[i+1]==y[j+1]
    print chr(y[i]) +':'+ chr(y[j])
      print chr(y[i+1]) +':'+ chr(y[j+1])
@author: PG
'''
import string
import array

str_ori_len=0
class randomizeComp:
     
    def compression(self,input):
        
            x=input
            print x
            str_ori_len=len(x)
            strlen=len(x) 
            print strlen
            y=[]
            pairs={}
            pairs_list=[]
            for i in range(strlen):
                 y.append(ord(x[i]))  
            print y
            newascii=128  
            while True:
                     # find max length pair  
                    dict={}       
                    for i in range(strlen-1):
                         if  not dict.has_key(chr(y[i]) + chr(y[i+1])):  
                              for j in range(i+1,strlen-1):
                              
                                      if  y[i]==y[j] and y[i+1]==y[j+1] :
                                          
                                              if dict.has_key(chr(y[i]) + chr(y[i+1])):
                                                               dict[chr(y[i]) + chr(y[i+1])]=dict[chr(y[i]) + chr(y[i+1])]+1
                                              else :
                                                  dict[chr(y[i]) + chr(y[i+1])]=2
                                                  
                                              #print 'output  ' + chr(y[i])  + chr(y[i+1])      
                    #print dict     
                    values=dict.viewvalues()                            
                    #print values
                    #print y        
                           
                   
                    for (k, v) in sorted(dict.items(), key=lambda (k,v): v, reverse=True):
                        #print k
                        #print v
                        i=0 
                        pairs_list.append(ord(k[0]))
                        pairs_list.append(ord(k[1]))
                        while i<strlen-1:
                            if y[i]==ord(k[0]) and y[i+1]==ord(k[1]):
                                y[i]=newascii
                                strlen=strlen-1
                                
                                if not pairs.has_key('ord(k[0])'+'ord(k[1])'):
                                   pass
                                    
                                pairs[ord(k[0]),ord(k[1])]=newascii
                                for j in range(i+1,strlen):
                                    y[j]=y[j+1]
                                
                            i=i+1
                        #print y  
                        newascii=newascii+1
                        break
                    if len(dict)==0:
                        break 
                
               
            output=[]
            output.append(newascii)
            for i in pairs_list:
               output.append(i)
                
             
            for i in range(strlen):
                output.append(y[i])   
            
            print output
            compresssedText=''
            for i in range(len(output)):
               compresssedText=compresssedText+chr(output[i])     
            print 'Compressed ..........'
            print compresssedText      
                   
            #print len(output)
            return compresssedText
################# Decompression

    def decompression(self,input):  
            print 'Decompression...........'
            decompress=[]
            temp=[] 
            output=[]
            for i in input:
                output.append(ord(i))
            high= output[0]
            low= 128
            elements=high-low
            for i in range(1,2*elements+1):
                temp.append(output[i])
            
            
            #print temp
            #print len(temp)
            
            def find_key(lis, val):
                                   
               return lis[2*(val-128)+1]
            
            #print len(output)
            for i in range(2*(high-128)+1):
                output.pop(0)
            print output
            n=len(output)
            
            #print output
            #print str_ori_len
            while True: #len(output) < str_ori_len :
                 flg=0
                 for i in range(0,n):
                    if output[i]>127 :
                       decompress.append(  temp[2*(output[i]-128)] )
                       decompress.append( temp[2*(output[i]-128)+1] )    
                    else :
                        decompress.append(output[i])   
                 output=decompress
                 n=len(decompress) 
                 print len(output)
                 print decompress
                 decompress=[]
                 for i in output:
                     if i>127:
                         flg=1
                         break
                 if flg==0:
                     break    
                         
                         
                         
            decompressed=''
            print output
            for i in output:
                decompressed=decompressed+chr(i)
            return decompressed

                   
                 
         
               
              
                
         
    
    