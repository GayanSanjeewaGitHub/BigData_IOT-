import time
import json
import stomp
import paho.mqtt.client as mqtt
import socket


Class MsgListener(stomp.ConnectionListener):
    def __init__(self):
        # to keep the count of messages received
        self.msg_recieved = 0 

    def on_error(self, message):
        logger.error(‘received an error “%s”’ %message)

    def on_message(self, message):
        message = json.loads(message)
        self.msg_received +=1
        # add your logic based on the message received here


con = stomp.StompConnection([(‘Host’,port)])
#listener class to be instantiated.
listener = MsgListener()
con.set_listener(‘name_of_listener’, listener)
#wait will ensure it waits till connection is established and acknowledged.
con.connect(‘usernanme’, ‘password’, wait=True)
#subscribe to a particular topic or queue by giving the path and headers if required by the server.
con.subscribe(‘topic/path’, headers={})