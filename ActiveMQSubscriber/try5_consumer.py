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

# Create a connection to the ActiveMQ broker
conn = stomp.Connection([(host, port)])
conn.set_listener('', MyListener())

try:
    # Connect to the broker
    conn.connect(username='admin', password='admin')

    # Subscribe to the topic to receive messages, including retroactive messages
    conn.subscribe(destination='/topic/topic-1', id=1, ack='auto', headers={
        'activemq.subscriptionName': 'test-client',
        'activemq.retroactive': 'true'
    })

    # Keep the program running to receive messages
    while True:
        pass
        # print('Listening for messages...')
        # time.sleep(0.1)

except stomp.exception.ConnectFailedException as e:
    print(f"Error: Could not connect to ActiveMQ broker - {e}")

except KeyboardInterrupt:
    print("Interrupted")

finally:
    # Disconnect from the broker
    conn.disconnect()
