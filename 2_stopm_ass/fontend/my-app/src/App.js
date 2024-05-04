// App.js
import React, { useState } from 'react';
// Remove the CSS import since it's not needed here
import ChatBox from './ChatBox';
import MessageInput from './MessageInput';

function App() {
  const [messages, setMessages] = useState([]);
  const [joined, setJoined] = useState(false);

  const handleSendMessage = async () => {
    try {
      const response = await fetch('http://localhost:7856/queue/sendMessage', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          message: "Welcome to Chat Room",
          userId: "123",
          chatRoom: "chatRoom1",
          userName: "Ann",
        }),
      });

      if (!response.ok) {
        throw new Error('Failed to send message');
      }

      // Optionally, you can handle the response if needed
      const data = await response.json();
      console.log(data);

      // Set joined state to true after successful join
      setJoined(true);
    } catch (error) {
      console.error('Error sending message:', error);
    }
  };

  return (
    <div className="App">
      <div className="container">
        {!joined && <button className="joinBtn" onClick={handleSendMessage}>Join the Conversation</button>}
        {joined && (
          <>
            <ChatBox messages={messages} />
            <MessageInput onSendMessage={handleSendMessage} />
          </>
        )}
      </div>
    </div>
  );
}

export default App;
