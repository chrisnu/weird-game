"use strict";


export class UI {

    _container;
    _loginBtn;
    _submitForm;
    _nameInput;
    _nameLabel;
    _canvas;
    _canvasContext;
    _dashboard;
    _playersUl;
    _playersLiTemplate;
    _playersLiBoldTemplate;

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
        this._dashboard = document.querySelector('div#dashboard');
        this._playersUl = document.querySelector('ul.players');
        this._playersLiTemplate = document.querySelector('li.player').outerHTML;
        document.querySelector('li.player').classList.add('bold');
        this._playersLiBoldTemplate = document.querySelector('li.player').outerHTML;
        this._canvas = document.querySelector('canvas#canvas');
        this._canvasContext = this._canvas.getContext('2d');
    }

    attachListeners(eventHandler) {
        this._loginBtn.addEventListener('click',  () => eventHandler.onLogin(this._nameInput.value.trim()));
        this._submitForm.addEventListener('submit',  event => {
            event.preventDefault();
            eventHandler.onLogin(this._nameInput.value.trim());
            return false;
        });
        this._canvas.addEventListener('click', event => {
            const rect = this._canvas.getBoundingClientRect();
            eventHandler.onShoot({
                x: event.clientX - rect.left,
                y: event.clientY - rect.top,
            });
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
        this._nameLabel.classList.add('hidden');

        this._displayQueue.classList.remove('hidden');
    }

    /**
     * We contain the target into a square because it's easier to calculate the coordinate
     * @param targets
     */
    drawTargets(targets) {
        this._canvasContext.clearRect(0, 0, this._canvas.width, this._canvas.height);

        targets.forEach(target => {
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
            this._canvasContext.strokeStyle = '#212121';
            this._canvasContext.lineWidth = 1;
            this._canvasContext.beginPath();
            this._canvasContext.strokeRect(target.coordinate.x - (target.size * 4), target.coordinate.y - (target.size * 4), target.size * 8, target.size * 8);
            this._canvasContext.closePath();
            this._canvasContext.fill();
        });
    }

    updateDashboard(player, players) {
        if (!player || !players) {
            return;
        }

        this._dashboard.classList.remove('hidden');

        // Clean the ul child nodes
        while (this._playersUl.firstChild) {
            this._playersUl.removeChild(this._playersUl.lastChild);
        }

        let innerHTML = '';

        players.forEach(p => {
            const template = player.session === p.session ? this._playersLiBoldTemplate : this._playersLiTemplate;
            const li = template
                .replace('{{players.name}}', p.name)
                .replace('{{players.score}}', p.score);
            innerHTML += li;
        });

        this._playersUl.innerHTML = innerHTML;
    }
}
