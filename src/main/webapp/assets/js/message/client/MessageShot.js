"use strict";

import {Message} from "../Message.js";

export class MessageShot extends Message{

    player;

    constructor() {
        super('SHOT');
    }
}
