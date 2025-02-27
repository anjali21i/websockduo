import React, { useEffect, useState } from 'react';
import { io } from 'socket.io-client';

const socket = io('http://localhost:3001');

const WebSockRoom = () => {

    const [room, setRoom] = useState("");
    const [message, setMessage] = useState("");
    const [recievedMessage, setRecievedMessage] = useState("");

    const joinRoom = () => {
        if (room !== "") {
            socket.emit('join_room', room);
        }
    }

    const sendMessage = () => {
        socket.emit('send_message', { message, room });
    };

    useEffect(() => {
        socket.on("recieve_message", (data) => {
            setRecievedMessage(data.message);
        })
    }, [socket]);

    return (
        <>
            <div>
                <input type='text' placeholder='enter room' onChange={(event) => setRoom(event.target.value)} />
                <button onClick={joinRoom}>Join Room</button>
                <input type='text' placeholder='enter message...' onChange={(event) => setMessage(event.target.value)} />
                <button onClick={sendMessage}>Send message</button>
            </div>
            <h1>
                {recievedMessage}
            </h1>
        </>
    )
}

WebSockRoom.propTypes = {}

export default WebSockRoom;