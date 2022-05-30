# Telegram bots
#### These are my first two bots that was implemeted in the end of 2021. Language - Russian.
###### Reason - get familiar with JSON (GSON), Telegram Bot Api, Yandex Weather Api
#### Fist bot can be found by searching @TsavaphBot or using inline query "@TsavaphBot " in Telegram.
#### Second bot is more like not serious bot, but very usefull by a chat group with my friends. It can be used by inline query "@TsavaphCockSizeBot ".


## TsavaphBot

### Classic bot usage commands
#### /cat - Gets a random picture of cat
#### /weather - Gets a button "Share your location" and when it pressed you receive a Yandex Weather information according to your location
#### /help - Sends you a help message

### Inline Bot Usage
#### Погода 🌞 - You'll send a Yandex Weather information according to your location (first you should allow sharing your location)
#### Курс биткоина 💰- You'll send a current Bitcoin price in USD
#### Пожелать здоровья 😇- You'll send a message to your friend (I hope as a joke) "Соси 🍆"
#### Послать котека 🐱 - You'll send a random picture of cat
#### Число 0-100 🔢 - You'll send a random number between 0 and 100

###### PS: Currently you can share your location only using a smartphone

## TsavaphCockSizeBot

### Only Inline Bot Usage
#### Send your cock information - You'll send a random cock information of your cock. The information updates once in 12 hours

## How to Implement the Code to Your Own Bots
#### First you have to open a file src/main/java/bots/Main.java and change
*   botUserName1 - Name of TsavaphBot
*   botToken1 - Token of @TsavaphBot
*   xYandexApiKey1 - Key of your [Yandex Wheather Api](https://yandex.com/dev/weather). Don't forget to change you API rate to [Yandex.Weather on your site](https://yandex.com/dev/weather/doc/dg/concepts/pricing.html)
*   botUserName2 - Name of TsavaphCockSizeBot
*   botToken2 - Token of TsavaphCockSizeBot
#### Then you have to open a file pom.xml and change change in Heroku plugin section
*   yourAppName - [Heroku](https://devcenter.heroku.com/articles/getting-started-with-java) application name 
#### Finally you have to run these two commands
*   mvn clean intall
*   mvn clean heroku:deploy
