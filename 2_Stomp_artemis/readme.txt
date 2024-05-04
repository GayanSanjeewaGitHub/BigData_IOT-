docker run --detach --name mycontainer -p 61616:61616 -p 8161:8161 --rm apache/activemq-artemis:latest-alpine

run the sender application 1:

run the sender application 2:


===================================================

send a request from sender 1:
http://localhost:7856/queue/sendMessage?message="Hello  all"&userId=123&chatRoom=chatRoom1&userName=sender1

 


===================================================

send a request from sender 2:
http://localhost:7857/queue/sendMessage?message="Hello  everyone"&userId=123&chatRoom=chatRoom1&userName=sender2


 



