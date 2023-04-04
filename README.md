# Telegram bot
#### This is my first bot that was implemented in the end of 2021. Language - Russian.
###### Reason - get familiar with JSON (GSON), Telegram Bot Api, Yandex Weather Api
#### Bot can be found by searching @TsavaphBot or using inline query "@TsavaphBot " in Telegram.


## TsavaphBot

### Classic bot usage commands
#### /cat - Gets a random picture of cat
#### /weather - Gets a button "Share your location" and when it pressed you receive a Yandex Weather information according to your location
#### /help - Sends you a help message

### Inline Bot Usage
#### Погода 🌞 - You'll send a Yandex Weather information according to your location (first you should allow sharing your location)
#### Курс биткоина 💰- You'll send a current Bitcoin price in USD
#### Пожелать здоровья 😇- You'll send a message to your friend "Счастья, здоровья! 😇"
#### Послать котека 🐱 - You'll send a random picture of cat
#### Число 0-100 🔢 - You'll send a random number between 0 and 100

###### PS: Currently you can share your location only using a smartphone

## How to Implement the Code to Your Own Bot
#### First you have to open a file 'src/main/java/bots/Main.java' and change
*   BOT_USERNAME - Name of TsavaphBot
*   BOT_TOKEN - Token of @TsavaphBot
*   YANDEX_API_KEY - Key of your [Yandex Weather Api](https://yandex.com/dev/weather). Don't forget to change you API rate to [Yandex.Weather on your site](https://yandex.com/dev/weather/doc/dg/concepts/pricing.html)
#### Then you have to open a file pom.xml and change in Heroku plugin section
*   yourAppName - [Heroku](https://devcenter.heroku.com/articles/getting-started-with-java) application name 
#### Finally, you have to run these two commands
*   mvn clean install
*   mvn clean heroku:deploy
