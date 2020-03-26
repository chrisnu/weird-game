# weird-game
###Game assignment as part of weird of science welcome ritual

####To build and run in one command

On Linux / Mac

`mvn package && sh target/bin/webapp`

On windows

`mvn package && target\bin\webapp`

####Access on the browser

`http://localhost:8080`

####To run the test

`mvn test`

####How to play

- On welcome screen, enter your name and start the game
- Shoot (click) the targets. Smaller targets have bigger point
- When all targets are shot, the game ends
- The one with the most score wins (not shown on UI)

####Game mechanic (With default configuration)

- Game will not start until max number of players (2) is reached
- Players who are late to enter the game will be queued in the "lobby" until the existing game is done
- Maximum only 1 game at a time
- When the game ends, it will kick out the existing player and start a new game with any player in the queue if exists
- Note: game can be configured on `/src/main/resources/config.properties`

####Tools that are used

- Java with javax.websocket for communication
- Lombok to reduce boilerplate
- Gson for encoding/decoding
- junit5 for unit test
- ES6 pure javascript on frontend
- Material for styling
- Canvas for drawing

####Known bugs

- Sometimes when you shoot a target, the target still shows on the frontend, however it actually registers in the backend
- Delayed "shotgun" sound

####What can be improved

- Implement some sort of message ACK to ensure that messages sent from client or server are delivered
- Implement some sort of ping calculation and interpolation so that there's less lag between players
- Front end unit testing (no experience with it on vanilla javascript)
