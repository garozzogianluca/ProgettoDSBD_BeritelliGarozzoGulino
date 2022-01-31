import matplotlib.pyplot as plt
import numpy
import matplotlib.gridspec as gridspec

file1 = open('monitoring.txt', 'r')
Lines = file1.readlines()
response_time = []
date = []

data = []
contatori_data = []

req = []
contatori_req = []

 
count = 0
# Strips the newline character
for line in Lines:
    count += 1
    #print("Line{}: {}".format(count, line.strip()))
    words = line.split(" ")
    
    #Aggiunge words[11] 
    response_time.append(int(words[11]))
    
    print(words[1])
    
    date.append(words[16] + " " + words[18])
    
    #Se è la prima volta, aggiunge a "data" la prima data che trova e aggiunge il valore 1 nel contatore
    if (count==1):
        data.append(words[16])
        contatori_data.append(1)
    #Altrimenti controlla se la data nella riga del file è già presente su "data" ...
    else:    
        i = 0
        trovato = 0
        for x in data:
            # ... se la data è presente incrementa il contatore
            if (numpy.array_equal(data[i],words[16])):
                contatori_data[i] = contatori_data[i]+1
                trovato = 1
            i = i+1
        # ... se non è presente la aggiunge e aggiunge anche il valore 1 nel contatore
        if (trovato == 0):
           data.append(words[16])
           contatori_data.append(1)
      
      
           
     #Se è la prima volta, aggiunge a "req" la prima req che trova e aggiunge il valore 1 nel contatore
    if (count==1):
        req.append(words[1])
        contatori_req.append(1)
    #Altrimenti controlla se la req nella riga del file è già presente su "req" ...
    else:    
        i = 0
        trovato = 0
        for x in req:
            # ... se la req è presente incrementa il contatore
            if (numpy.array_equal(req[i],words[1])):
                contatori_req[i] = contatori_req[i]+1
                trovato = 1
            i = i+1
        # ... se non è presente la aggiunge e aggiunge anche il valore 1 nel contatore
        if (trovato == 0):
           req.append(words[1])
           contatori_req.append(1)

file1.close()

print(response_time)
print(date)

print(contatori_data)
print(data)

gs = gridspec.GridSpec(2, 2)
plt.figure().canvas.manager.set_window_title('Monitorng')
ax = plt.subplot(gs[0, :])
plt.title('Response Time', color='green')
plt.plot(date, response_time)
plt.xlabel('Data', color='blue')
plt.ylabel('Response Time', color='blue')

ax = plt.subplot(gs[1, 0])
plt.title('Richieste al giorno', color='green')
plt.plot(data, contatori_data)
plt.xlabel('Data', color='blue')
plt.ylabel('N° Richieste', color='blue')

ax = plt.subplot(gs[1, 1])
plt.title('Richieste', color='green')
plt.plot(req, contatori_req)
plt.xlabel('Request', color='blue')
plt.ylabel('N° Richieste', color='blue')

plt.show()






