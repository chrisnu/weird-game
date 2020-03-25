"use strict";

import {Game} from "./core/Game.js";
import {UI} from "./core/UI.js";

(function mainApp() {
    const game = new Game(
        new UI('#E50024', '#FFFFFF'),
        new WebSocket('ws://localhost:8080/game')
    );

    game.start();
}());
