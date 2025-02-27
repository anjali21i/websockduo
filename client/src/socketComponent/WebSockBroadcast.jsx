import { useEffect, useState } from 'react';
import { io } from 'socket.io-client';

const socket = io('http://localhost:3001');

const WebSockBroadcast = () => {

    const [message, setMessage] = useState("");
    const [recievedMessage, setRecievedMessage] = useState("");

    useEffect(() => {
        socket.on("recieve_message", (data) => {
            setRecievedMessage(data);
        })
    }, [socket]);

    const sendMessage = () => {
        socket.emit('send_broadcast_messages', `Hello, ${message}`);
        console.log('Message sent:', message);
    };

    return (
        <>
            <div>
                <input type='text' placeholder='enter message...' onChange={(event) => setMessage(event.target.value)} />
                <button onClick={sendMessage}>Send message</button>
            </div>
            <h1>
                {recievedMessage}
            </h1>
        </>
    )
}

export default WebSockBroadcast;
