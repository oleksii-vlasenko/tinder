
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="text-center">
    <form action="user" method="POST" class="form-signin">
        <img class="mb-4" src="https://tinder.com/static/tinder.png" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal">About you</h1>
        <label for="inputName" class="sr-only">Name</label>
        <input id="inputName" type="text" name="name" class="form-control" placeholder="Your name" required autofocus>
        <label for="inputPhoto" class="sr-only">Photo</label>
        <input name="image" type="text" id="inputPhoto" class="form-control" placeholder="Your photo" required>
        <button style="width: 49%" class="btn btn-lg btn-primary" type="submit">Create</button>
        <button style="width: 49%" class="btn btn-lg btn-primary">Cancel</button>
        <p class="mt-5 mb-3 text-muted">&copy; Tinder 2020</p>
    </form>
</body>
</html>