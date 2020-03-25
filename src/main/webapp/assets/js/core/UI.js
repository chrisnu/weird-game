"use strict";


export class UI {

    _container;
    _loginBtn;
    _submitForm;
    _nameInput;
    _nameLabel;
    _canvas;
    _canvasContext;

    _displayWait;
    _displayQueue;

    _color1;
    _color2;

    constructor(color1, color2) {
        this._color1 = color1;
        this._color2 = color2;
    }

    /**
     * Show login screen
     */
    start() {
        this._container = document.querySelector('div#game');
        this._loginBtn = document.querySelector('button#login');
        this._submitForm = document.querySelector('form.login');
        this._nameInput = document.querySelector('input#name');
        this._nameLabel = document.querySelector('label#label-name');
        this._displayWait = document.querySelector('div#display-wait');
        this._displayQueue = document.querySelector('div#display-queue');
        this._canvas = document.querySelector('canvas#canvas');
        this._canvasContext = this._canvas.getContext('2d');
    }

    attachListeners(game, loginEventListener) {
        const self = this;
        this._loginBtn.addEventListener('click',  () => loginEventListener.call(game, self._nameInput.value.trim()));
        this._submitForm.addEventListener('submit',  event => {
            event.preventDefault();
            loginEventListener.call(game, self._nameInput.value.trim());
            return false;
        });
    }

    onGameStatusChange(state) {
        switch (state) {
            case 'WAIT':
                this._showWaitScreen();
                break;
            case 'QUEUED':
                this._showQueueScreen();
                break;
            case 'PLAY':
                this._showPlayScreen();
                break;
            case 'END':
                this._showEndScreen();
                break;
        }
    }

    _showWaitScreen() {
        this._loginBtn.classList.add('hidden');
        this._nameInput.classList.add('hidden');
        this._nameLabel.classList.add('hidden');

        this._displayWait.classList.remove('hidden');
    }

    _showPlayScreen() {
        this._displayQueue.classList.add('hidden');
        this._displayWait.classList.add('hidden');
    }

    _showEndScreen() {
        window.onbeforeunload = null;    }

    _showQueueScreen() {
        this._loginBtn.classList.add('hidden');
        this._nameInput.classList.add('hidden');

        this._displayQueue.classList.remove('hidden');
    }

    drawTarget(target) {
        if (!target || !target.coordinate || !target.coordinate.x || !target.coordinate.y || !target.size) {
            console.error('Invalid target object, missing some properties', target);
            return;
        }

        for (let i = 4; i >= 1; i--) {
            this._canvasContext.fillStyle = i % 2 === 0 ? this._color1 : this._color2;
            this._canvasContext.beginPath();
            this._canvasContext.arc(target.coordinate.x, target.coordinate.y, target.size * i, 0, Math.PI * 2);
            this._canvasContext.closePath();
            this._canvasContext.fill();
        }
    }
}
