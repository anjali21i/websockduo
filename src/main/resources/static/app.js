var stompClient = null;
var username = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        document.querySelector('#chat-wrap').classList.remove('hidden');
        $("#chat-wrap").show();
    }
    else {
        $("#chat-wrap").hide();
    }
    $("#msg-text-wrap-mine").html("");
}

function connect() {
    console.log("inside connect")
    username = document.querySelector("#username").value.trim();
    if(username) {
        var socket = new SockJS('/stomp-endpoint');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/welcomeme', function (welcome) {
                console.log("subscribed after call");
                showChatWindow(JSON.parse(welcome.body).content);
            });
        });
    }
    
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessages() {
    stompClient.send("/app/welcome", {}, JSON.stringify({'messages': $("#messages").val()}));
}

function showChatWindow(message) {
    console.log("chat win")
    $("#msg-text-wrap-mine").append('<div class="direct-chat-text">' + message.message + "</div>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#msg-send" ).click(function() { sendMessages(); });
});