"use strict";

import {Player} from "../model/Player.js";
import {MessageLogin} from "../message/client/MessageLogin.js";
import {ReceivedMessageHandler} from "./ReceivedMessageHandler.js";

export class Game {

    _ui; _player; _websocket; _status;

    constructor(ui, webSocket) {
        this._ui = ui;
        this._websocket = webSocket;
    }

    start() {
        const messageHandler = new ReceivedMessageHandler(this);
        this._websocket.onmessage = message => messageHandler.process(JSON.parse(message.data));

        this._ui.start();
        this._ui.attachListeners(this, this._login);
    }

    setStatus(status) {
        this._status = status;
        this._ui.onGameStatusChange(status);
    }

    _login(name) {
        if (!name) {
            alert('Please fill in your name');
            return;
        }

        this._player = new Player(name, 0, 0, {x: 0, y: 0});
        const message = new MessageLogin();
        message.player = this._player;
        this.notify(message);
    }

    notify(message) {
        this._websocket.send(JSON.stringify(message));
    }
}
