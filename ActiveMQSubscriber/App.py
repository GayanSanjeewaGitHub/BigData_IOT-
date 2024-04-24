import time
import json
import stomp
import paho.mqtt.client as mqtt
import socket
 

# ActiveMQ and MQTT broker details
ACTIVEMQ_HOST = 'tcp://localhost'
ACTIVEMQ_PORT = 61613
MQTT_HOST = 'tcp://localhost'
MQTT_PORT = 1883

# ActiveMQ topics
SUBSCRIBE_TOPIC = '/topic/userIssues'
PUBLISH_TOPIC = '/topic/processedIssues'

# MQTT client ID
MQTT_CLIENT_ID = 'mqtt_publisher'

# Connect to ActiveMQ using STOMP protocol
class ActiveMQListener(stomp.ConnectionListener):

    def __init__(self, mqtt_client):
        self.mqtt_client = mqtt_client

    def on_message(self, headers, body):
        try:
            message = json.loads(body)
            request_id = message.get('RequestId')
            departments = message.get('Departments')

            # Perform calculations (example: count departments)
            department_count = len(departments)

            # Prepare processed message
            processed_message = {
                'ClientId': request_id,
                'Departments': departments
            }
            processed_message_json = json.dumps(processed_message)

            # Publish processed message to MQTT
            self.mqtt_client.publish(PUBLISH_TOPIC, processed_message_json)

            print(f"Processed message published: {processed_message_json}")

        except Exception as e:
            print(f"Error processing message: {str(e)}")


# Setup MQTT client for publishing
mqtt_username = 'admin'  # Replace with your ActiveMQ username
mqtt_password = 'admin'  # Replace with your ActiveMQ password
# mqtt_client = mqtt.Client(mqtt.CallbackAPIVersion.VERSION2)
mqtt_client = mqtt.Client(mqtt.CallbackAPIVersion.VERSION2 ,client_id=MQTT_CLIENT_ID 
                          ,clean_session = True  ,reconnect_on_failure = True )
mqtt_client.username_pw_set(username=mqtt_username, password=mqtt_password)
try:
    mqtt_client.connect(MQTT_HOST, MQTT_PORT, 120)
    print("Connected to MQTT broker successfully")
except Exception as e:
    print(f"Error connecting to MQTT broker: {e}")
 


# Setup MQTT client for publishing


# Setup ActiveMQ listener
listener = ActiveMQListener(mqtt_client)
conn = stomp.Connection([(ACTIVEMQ_HOST, ACTIVEMQ_PORT)])
conn.set_listener('', listener)
 

try:
    conn.connect()
    print("Connected to ActiveMQ successfully")
except Exception as e:
    print(f"Error connecting to ActiveMQ: {e}")
 

# Subscribe to ActiveMQ topic
conn.subscribe(destination=SUBSCRIBE_TOPIC, id=1, ack='auto')

# Keep the program running to receive messages
while True:
    try:
        time.sleep(1)
    except KeyboardInterrupt:
        break

# Disconnect from ActiveMQ and MQTT
conn.disconnect()
mqtt_client.disconnect()
