import SockJS from 'sockjs-client'
import Stomp from 'stompjs'
import {BASE_URL} from '../const/base-data'

export default (listener) => {
    let socket = new SockJS(BASE_URL + '/socketConnect');
    let stompClient = Stomp.over(socket);
    // 禁用Debug
    stompClient.debug = null;
    stompClient.connect({}, (frame) => {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/downloadStatus/notice', (response) => {
            listener(JSON.parse(response.body));
        });
    });
}
