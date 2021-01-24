<#ftl encoding="UTF-8"/>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Вход-Restoke</title>
    <link rel="stylesheet" type="text/css" href="RegistrationWindow/style_for_login_window.css">
	<link rel="shortcut icon" href="RegistrationWindow/icon.png" type="image/x-icon"/>
</head>
<body>

<div class="registrationBox">

	<form  name="myForm" action="/restoke_war/login" method="post">
		<svg width="7em" height="7em" viewBox="0 0 16 16" class="bi bi-shop" fill="#363237" xmlns="http://www.w3.org/2000/svg">
			<path fill-rule="evenodd" d="M2.97 1.35A1 1 0 0 1 3.73 1h8.54a1 1 0 0 1 .76.35l2.609 3.044A1.5 1.5 0 0 1 16 5.37v.255a2.375 2.375 0 0 1-4.25 1.458A2.371 2.371 0 0 1 9.875 8 2.37 2.37 0 0 1 8 7.083 2.37 2.37 0 0 1 6.125 8a2.37 2.37 0 0 1-1.875-.917A2.375 2.375 0 0 1 0 5.625V5.37a1.5 1.5 0 0 1 .361-.976l2.61-3.045zm1.78 4.275a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 1 0 2.75 0V5.37a.5.5 0 0 0-.12-.325L12.27 2H3.73L1.12 5.045A.5.5 0 0 0 1 5.37v.255a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0zM1.5 8.5A.5.5 0 0 1 2 9v6h1v-5a1 1 0 0 1 1-1h3a1 1 0 0 1 1 1v5h6V9a.5.5 0 0 1 1 0v6h.5a.5.5 0 0 1 0 1H.5a.5.5 0 0 1 0-1H1V9a.5.5 0 0 1 .5-.5zM4 15h3v-5H4v5zm5-5a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1h-2a1 1 0 0 1-1-1v-3zm3 0h-2v3h2v-3z"/>
		</svg>
		<h1>Welcome!</h1>
		<p>${varning}</p>
		<input type="text" name="username" placeholder="Username">
		<input type="password" name="password" placeholder="Password">
		<a href="../Main_Page/main_page.html" style="text-decoration: none"><input type="submit" name="" value="Login"></a>
		<input type="checkbox" name="remember_me" value="true"><div class="remember_me"><b>Remember me</b></div>
	</form>

	<div class="notification"> Don't have an account? <a href="/restoke_war/sign-up"><b>Sign Up</b></a> </div>
	<div class="anonymous"><a href="/restoke_war/main"><b>Anonymous</b></a> login</div>

</div>




    <script src="RegistrationWindow/script_for_login_window.js"></script>
</body>
</html>