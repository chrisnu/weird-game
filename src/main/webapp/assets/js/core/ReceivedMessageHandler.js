"use strict";

export class ReceivedMessageHandler {
    _game;

    constructor(game) {
        this._game = game;
    }

    process(message) {
        switch (message.type) {
            case 'WAIT':
                this._game.setStatus('WAIT');
                break;

            case 'START':
                this._game.setStatus('PLAY');
                break;

            case 'END':
                this._game.setStatus('END');
                break;

            case 'QUEUED':
                this._game.setStatus('QUEUED');
                break;

            case 'TARGET':
                message.targets.forEach(target => this._game._ui.drawTarget(target));
                break;
        }

        console.log(message);
    }
}
