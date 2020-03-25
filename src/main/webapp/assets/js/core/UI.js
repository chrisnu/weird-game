"use strict";


export class UI {

    _container;
    _loginBtn;
    _submitForm;

    _displayWait;
    _displayQueue;

    constructor() {
    }

    /**
     * Show login screen
     */
    start() {
        this._container = document.querySelector('div#game');
        this._loginBtn = document.querySelector('button#login');
        this._submitForm = document.querySelector('form.login');
        this._nameInput = document.querySelector('input#name');
        this._displayWait = document.querySelector('div#display-wait');
        this._displayQueue = document.querySelector('div#display-queue');
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
}
