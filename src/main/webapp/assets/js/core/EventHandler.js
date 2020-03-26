"use strict";

import {MessageLogin} from "../message/client/MessageLogin.js";
import {MessageShot} from "../message/client/MessageShot.js";

export class EventHandler {

    _game; _player;

    constructor(game, player) {
        this._game = game;
        this._player = player;
    }

    onLogin(name) {
        if (!name) {
            alert('Please fill in your name');
            return;
        }

        this._player.name = name;
        const message = new MessageLogin();
        message.player = this._player;
        this._game.notify(message);
    }

    onShoot(coordinate) {
        if (this._game._status !== 'PLAY') {
            return;
        }

        this._player.coordinate = {
            x: Math.round(coordinate.x),
            y: Math.round(coordinate.y),
        };
        this._player.shoot();
        const message = new MessageShot(this._player);
        this._game.notify(message);
    }

}
