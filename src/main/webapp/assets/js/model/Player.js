"use strict";

import {Actor} from "./Actor.js";

export class Player extends Actor {

    session; _gunshotSound;

    constructor(name) {
        super();
        this.name = name;
        this._gunshotSound = new Audio('assets/sound/gunshot.mp3')
    }

    shoot() {
        this._gunshotSound.play();
    }
}
