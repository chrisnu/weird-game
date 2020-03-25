"use strict";

import {Message} from "../Message.js";

export class MessageTarget extends Message {

    targets;

    constructor() {
        super('TARGET');
    }

}
