<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta property="og:title" content="Email template">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <style>
        body {
            box-sizing: border-box;
            margin: 0%;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
            Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
        }
        .userName {
            font-weight: 500;
        }
        .emailContainer {
            width: 80%;
            height: 100vh;
            margin: 0 auto;
            background: #f2f3f8;
        }
        .bodyContainer {
            margin: 20px auto;
            width: 80%;
            background-color: #fff;
            padding: 90px;
            overflow: auto;
            border-radius: 50px;
            box-shadow: 1px 5px 25px rgba(109, 109, 109, 0.562);
        }
        .codeBtn {
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
        }
        .contentContainer {
            font-size: 17px;
            font-weight: 600;
        }
        .codeContainer {
            background-color: rgb(8, 8, 97);
            padding-left: 20px;
            padding-right: 20px;
            padding-top: 10px;
            padding-bottom: 10px;
            margin-top: 10px;
            border-radius: 50px;
            color: #fff;
        }

    </style>
</head>
<body>


<div class="emailContainer">
    <div style="width: 50%; margin: 10px auto;">
        <img src="http://www.viasmobotech.in/wp-content/uploads/2020/09/Vias-Logo.png" width="100%" height="100" alt=""
        style="padding: 10px;">
    </div>
    <div class="bodyContainer">
        <div class="userName">Greetings ${username},</div>
        <div class="contentContainer">
            <p>
                You are getting this mail because we received password reset
                request. Your verification code is
            </p>
        </div>
        <div class="codeBtn">
            <div class="codeContainer" id="#code">${otp}</div>
        </div>
        <p>
            If you did not forget your password, you can safely ignore this email.
        </p>
    </div>
</div>
</body>
</html>