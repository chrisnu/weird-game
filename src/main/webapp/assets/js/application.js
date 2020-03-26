"use strict";

import {Game} from "./core/Game.js";
import {UI} from "./core/UI.js";
import {Player} from "./model/Player.js";

(function mainApp() {
    const game = new Game(
        new UI('#E50024', '#FFFFFF'),
        new WebSocket('ws://localhost:8080/game'),
        new Player(null, )
    );

    game.start();
}());
