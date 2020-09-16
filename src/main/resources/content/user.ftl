<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
</head>
<body>
<h1 style="text-align: center">${name}</h1>
<div style="text-align: center">
    <img src=${image}
         alt="cat"
         style="width: 30px; height: 30px"> <br/>
    <form action="users" method="POST">
        <input type="submit"
               style="height: 30px; width: 70px"
               name="button"
               value="Yes">
        <input type="submit"
               style="height: 30px; width: 70px"
               name="button"
               value="No">
    </form>
</div>
<script src="/js/user.js"></script>
</body>
</html>