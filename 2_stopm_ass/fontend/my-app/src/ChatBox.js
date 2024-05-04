// ChatBox.js
import React from 'react';

function ChatBox({ messages }) {
  return (
    <div className="ChatBox" style={{ backgroundColor: '#f9f9f9' }}>
      {messages.map((message, index) => (
        <div key={index} className="ChatMessage">
          {message}
        </div>
      ))}
    </div>
  );
}

export default ChatBox;
