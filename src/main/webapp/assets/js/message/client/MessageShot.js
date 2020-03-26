"use strict";

import {Message} from "../Message.js";

export class MessageShot extends Message{

    player;

    constructor(player) {
        super('SHOT');
        this.player = player;
    }
}
