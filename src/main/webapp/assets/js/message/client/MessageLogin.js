"use strict";

import {Message} from "../Message.js";

export class MessageLogin extends Message{

    player;

    constructor() {
        super('LOGIN');
    }
}
