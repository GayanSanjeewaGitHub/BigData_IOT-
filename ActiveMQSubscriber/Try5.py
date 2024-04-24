import time
import stomp

class Listener(stomp.ConnectionListener):
    def on_error(self, headers, message):
        print(f'Received an error: {message}')

    def on_message(self, headers, message):
        print(f'Received a message: {message}')

# Define ActiveMQ broker connection details
host = 'localhost'
port = 61613

# Create a connection to the ActiveMQ broker
conn = stomp.Connection([(host, port)])
conn.set_listener('', Listener())

try:
    # Connect to the broker with a specific client ID
    conn.connect(username='admin', password='admin', wait=True, clientid='test-client')

    # Subscribe to the topic to receive messages with the specified client ID
    conn.subscribe(destination='/topic/topic-1', id=1, ack='auto', headers={'activemq.subscriptionName': 'test-client'})

    # Keep the program running to receive messages
    while True:
        time.sleep(1)

except stomp.exception.ConnectFailedException as e:
    print(f"Error: Could not connect to ActiveMQ broker - {e}")

except KeyboardInterrupt:
    print("Interrupted")

finally:
    # Disconnect from the broker
    conn.disconnect()
