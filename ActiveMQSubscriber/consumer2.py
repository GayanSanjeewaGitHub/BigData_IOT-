import time
import stomp

class MyListener(stomp.ConnectionListener):
    def on_error(self, frame):
        print('received an error "%s"' % frame.body)

    def on_message(self, frame):
        print('received a message "%s"' % frame.body)

# Define ActiveMQ broker connection details
host = 'localhost'
port = 61613
username = 'admin'
password = 'admin'
topic_name = '/topic/userIssues'

# Create a connection to the ActiveMQ broker
conn = stomp.Connection([(host, port)])
conn.set_listener('', MyListener())

try:
    # Connect to the broker
    conn.connect(username=username, password=password)

    # Subscribe to the topic to receive messages
    conn.subscribe(destination=topic_name, id=1, ack='auto' ,headers={
        'activemq.subscriptionName': 'test-client',
        'activemq.retroactive': 'true'
    })
    print(f"Consumer connected and subscribed to topic: {topic_name}")

    # Keep the program running to receive messages
    while True:
        time.sleep(1)  # Sleep for a short time while waiting for messages

except stomp.exception.ConnectFailedException as e:
    print(f"Error: Could not connect to ActiveMQ broker - {e}")

except KeyboardInterrupt:
    print("Interrupted")

finally:
    # Disconnect from the broker
    conn.disconnect()
