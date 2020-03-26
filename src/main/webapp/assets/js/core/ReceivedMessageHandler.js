"use strict";

export class ReceivedMessageHandler {
    _game;

    constructor(game) {
        this._game = game;
    }

    process(message) {
        switch (message.type) {
            case 'LOGIN_CONFIRMED':
                this._game.updatePlayer(message.player);
                break;

            case 'WAIT':
                this._game.setStatus('WAIT');
                break;

            case 'START':
                this._game.updatePlayers(message.players);
                this._game.setStatus('PLAY');
                break;

            case 'END':
                this._game.setStatus('END');
                break;

            case 'QUEUED':
                this._game.setStatus('QUEUED');
                break;

            case 'TARGET':
                message.targets.forEach(target => {
                    this._game.addTarget(target);
                });

                this._game.renderTarget();
                break;

            case 'HIT':
                this._game.updatePlayer(message.player);
                this._game.removeTargets(message.targets);
                break;
        }

        console.info(message);
    }
}
