@echo off
set path=C:\Users\GT911043\.m2\repository\allure\allure-2.13.9\bin;C:\Program Files\Java\jdk-16\bin;%path%
allure serve allure-results
pause
exit