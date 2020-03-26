"use strict";

import {ReceivedMessageHandler} from "./ReceivedMessageHandler.js";
import {EventHandler} from "./EventHandler.js";

export class Game {

    _ui; _player; _players; _websocket; _status;

    constructor(ui, webSocket, player) {
        this._ui = ui;
        this._websocket = webSocket;
        this._player = player;
    }

    start() {
        const messageHandler = new ReceivedMessageHandler(this);
        this._websocket.onmessage = message => messageHandler.process(JSON.parse(message.data));

        this._ui.start();
        this._ui.attachListeners(new EventHandler(this, this._player));
    }

    setStatus(status) {
        this._status = status;
        this._ui.onGameStatusChange(status);
    }

    notify(message) {
        console.log(message);
        this._websocket.send(JSON.stringify(message));
    }

    updatePlayers(players) {
        this._players = players;
        this._ui.updateDashboard(this._player, players);
    }

    updatePlayer(player) {
        this._player = player;
    }

    removeTarget(target) {
        this._ui.removeTarget(target);
    }
}
