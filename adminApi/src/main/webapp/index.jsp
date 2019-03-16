<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div>
    Input City Name in form
    <form action="/adminApi/set" method="post">
        <input type="text" name="city" placeholder="City name">
        <input type="submit" value="Submit">
    </form>
    <div>
        ${message}
    </div>
</div>
</body>
</html>
