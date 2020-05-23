<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                                <tr>
                                <#list likedUsers as user>
                                        <td width="10">
                                            <div class="avatar-img">
                                                <img class="img-circle" style="width:550%;" src="${user.imageURL}" />  
                                            </div>
                                        </td>
                                        <td class="align-middle">
                                            ${user.username}
                                        </td>
                                        <td  class="align-middle">
                                            Last Login:  ${user.prettyLastLogin()}<br><small class="text-muted">${user.daysAgo()}</small>
                                        </td>
                                        <td>
                                            <form method="post" action="/users/?messageTo=${user.slug()}">
                                                <div class="col-12 col-lg-6">
                                                    <button type="submit" class="btn btn-outline-success btn-block"><span class="fa fa-message"></span> Send Message</button>
                                                </div>
                                            </form>
                                        </td>
                                </#list>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>