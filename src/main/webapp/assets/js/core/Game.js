"use strict";

import {ReceivedMessageHandler} from "./ReceivedMessageHandler.js";
import {EventHandler} from "./EventHandler.js";

export class Game {

    _ui; _player; _players; _websocket; _status; _targets;

    constructor(ui, webSocket, player) {
        this._ui = ui;
        this._websocket = webSocket;
        this._player = player;
        this._targets = [];
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
        this._websocket.send(JSON.stringify(message));
    }

    updatePlayers(players) {
        this._players = players;
        this._ui.updateDashboard(this._player, players);
    }

    updatePlayer(player) {
        if (!player) {
            return;
        }

        if (!this._player || !this._player.session || (player.session && player.session === this._player.session)) {
            this._player = player;
        }

        if (this._players) {
            this._players = this._players.map(p => {
                if (p.session === player.session) {
                    return player;
                }

                return p;
            });
        }

        this._ui.updateDashboard(this._player, this._players);
    }

    addTarget(target) {
        this._targets.push(target);
    }

    removeTargets(targets) {
        this._targets = this._targets.filter(t => targets.some(toRemove => toRemove.id !== t.id));
        this.renderTarget();
    }

    renderTarget() {
        this._ui.drawTargets(this._targets);
    }
}
