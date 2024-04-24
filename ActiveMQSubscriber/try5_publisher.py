import time
import stomp

# Define ActiveMQ broker connection details
host = 'localhost'
port = 61613

# Create a connection to the ActiveMQ broker
conn = stomp.Connection([(host, port)])

try:
    # Connect to the broker
    conn.connect(username='admin', password='admin')

    while True:
        # Send a test message to the topic
        conn.send(body='Test Message', destination='/topic/topic-1')
        print('Message sent: "Test Message"')
        time.sleep(1)

except stomp.exception.ConnectFailedException as e:
    print(f"Error: Could not connect to ActiveMQ broker - {e}")

except KeyboardInterrupt:
    print("Interrupted")

finally:
    # Disconnect from the broker
    conn.disconnect()
