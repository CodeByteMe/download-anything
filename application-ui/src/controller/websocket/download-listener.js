import SockJS from 'sockjs-client'
import Stomp from 'stompjs'

// TODO
let testBaseUrl = "http://localhost:10086";

export default (listener) => {
    let socket = new SockJS(testBaseUrl + '/socketConnect');
    let stompClient = Stomp.over(socket);
    stompClient.debug = null; // 禁用Debug
    stompClient.connect({}, (frame) => {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/downloadStatus/notice', (response) => {
            listener(JSON.parse(response.body));
            console.dir(response.body);
        });
    });
}
