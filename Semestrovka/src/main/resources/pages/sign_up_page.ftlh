<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h2><a href="?lang=ru">RU</a></h2>
<h2><a href="?lang=en">ENG</a></h2>
<h1><@spring.message "sign_up_page.registration.title"/></h1>
<form action="/signUp" method="post">
    <label for="firstName"><@spring.message "sign_up_page.registration.name"/></label>
    <input id="firstName" name="firstName">
    <label for="lastName"><@spring.message "sign_up_page.registration.second_name"/></label>
    <input id="lastName" name="lastName">
    <label for="email"><@spring.message "sign_up_page.registration.email"/></label>
    <input id= "email" name="email" type="email">
    <label for="password"><@spring.message "sign_up_page.registration.password"/></label>
    <input id="password" name="password" type="password">
    <input type="submit" value="<@spring.message "sign_up_page.registration.title"/>">
</form>
</body>
</html>
