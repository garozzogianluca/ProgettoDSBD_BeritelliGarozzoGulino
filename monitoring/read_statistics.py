from confluent_kafka import Consumer

c = Consumer({
    'bootstrap.servers': "127.0.0.1",
    'group.id': 'mygroup',
    'auto.offset.reset': 'earliest'
})

c.subscribe(['monitoring'])

while True:
    msg = c.poll(1.0)

    if msg is None:
        continue
    if msg.error():
        print("Consumer error: {}".format(msg.error()))
        continue

    print('Received message: {}'.format(msg.value().decode('utf-8')))
    
    file1 = open("monitoring.txt", "a") # a = append, aggiunge le righe alla fine del file
    file1.writelines(msg.value().decode('utf-8')+'\n')
    file1.close()
    

c.close()
