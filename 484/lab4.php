<?php
    session_start();

    // //$mysql = new mysqli('localhost','root','','myDB');
    // $mysql = new mysqli("localhost","root", "");// or die("Connection failed:\n");
    //     /* check connection */
    // $mysql->query("CREATE DATABASE IF NOT EXISTS myDB COLLATE utf8_general_ci");
    // $mysql->select_db("myDB");
    
    // // setup user table
    // $sql = "CREATE TABLE user (
    //     userid int(10) unsigned NOT NULL AUTO_INCREMENT,
    //     username varchar(20) DEFAULT NULL,
    //     password varchar(65532) DEFAULT NULL,
    //     salt char(128) DEFAULT NULL,
    //     PRIMARY KEY (userid),
    //     UNIQUE KEY username (username)
    // ) ENGINE=InnoDB DEFAULT CHARSET=utf8";
    // // create user table if it does not exist
    // $mysql->query($sql);
    
    // // setup message table
    // $sql = "CREATE TABLE message (
    //     msgid int(10) unsigned NOT NULL AUTO_INCREMENT,
    //     userid int(10) unsigned NOT NULL,
    //     email varchar(65532) NOT NULL,
    //     msg varchar(128) DEFAULT NULL,
    //     timestamp varchar(17) NOT NULL,
    //     sent TINYINT unsigned NOT NULL DEFAULT 0,
    //     PRIMARY KEY (msgid)
    // ) ENGINE=InnoDB DEFAULT CHARSET=utf8";
    // // create message table if it does not exist
    // $mysql->query($sql);


     if (isset($_GET['loggedout'])) {
        session_unset(); 
    }

    /* Check if logged in*/
    /*
    if(isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true){
        $userid = $_SESSION['userid'];
        exit($userid);
        //echo "Hi!";
    }
    */

    // Register User
    if(isset($_POST['regusername']) && isset($_POST['regpassword'])){
       
        $stmt = $mysql->prepare("SELECT * FROM user WHERE username=?");
        $stmt->bind_param("s", $username);
        $username = $_POST['regusername'];
        $password = $_POST['regpassword'];
       
        $stmt->execute();
        $stmt->store_result();
        $result = $stmt->num_rows;
        $stmt->close();
        if($result !== 0){
            exit("PHP: Username '" . $username . "' already exists!");   
        } else if(!preg_match("/^(?=(.*[a-zA-Z].*))(?=.*\d.*)(?=.*\W.*)[a-zA-Z0-9\S]{8,}$/" ,$password)){
            // Stole & modified RegExp from http://regexlib.com/Search.aspx?k=password&c=-1&m=5&ps=20
            exit("PHP: The password must be 8 characters or more with at least one letter, one number, and one special character.");
        } else {
            $stmt = $mysql->prepare("INSERT INTO user (username, password, salt) VALUES (?, ?, ?)");
            $stmt->bind_param("sss", $username , $password, $salt);
            $username = $_POST['regusername'];
            $salt = mcrypt_create_iv(128, MCRYPT_DEV_URANDOM);
            $password = hash('sha512', $salt . $_POST['regpassword']);
            $stmt->execute();
            $stmt->close();
            $_SESSION['loggedin'] = true;
            $_SESSION['userid'] = $mysql->insert_id;
            $_SESSION['username'] = $username;
            //exit("Userid: $mysql->insert_id");
        }
    
        exit();
    }
    // Login User
    if(isset($_POST['loginuser']) && isset($_POST['loginpass'])){
         if(!preg_match("/^(?=(.*[a-zA-Z].*))(?=.*\d.*)(?=.*\W.*)[a-zA-Z0-9\S]{8,}$/" ,$_POST['loginpass']) 
                && !preg_match("/^[a-zA-Z0-9]+$/", $_POST['loginuser'])) {
            exit("PHP: Invalid username or password.");
        } else {

            $stmt = $mysql->prepare("SELECT userid, password, salt FROM user WHERE username=?");
            $stmt->bind_param("s", $username);
            $username = $_POST['loginuser'];
            $password = $_POST['loginpass'];
            $stmt->execute();
            $stmt->store_result();
            $stmt->bind_result($userid, $tblPass, $salt);
                
            $stmt->fetch();
            
            $stmt->close();
            if(hash('sha512', $salt . $password) !== $tblPass)
                exit("PHP: Invalid username or password.");
        
            $_SESSION['loggedin'] = true;
            $_SESSION['userid'] = $userid;
            //exit("Userid: $userid");
        } 
         exit(); 
    }
    if(isset($_POST['emailR']) && isset($_POST['message'])){
        if(!preg_match("/^[\w-]+(?:\.[\w-]+)*@(?:[\w-]+\.)+[a-zA-Z]{2,7}$/", $_POST['emailR'])){
           exit("PHP: Invalid recipient email.");
        } else {
            $stmt = $mysql->prepare("INSERT INTO message (userid, email, msg) VALUES (?, ?, ?)");
            $stmt->bind_param("iss", $userid, $emailR, $msg);
            $userid = $_SESSION['userid'];
            $emailR = $_POST['emailR'];
            $msg = $_POST['message'];
            $stmt->execute();
            $stmt->close();
            exit();
        }
           
    }

?>

    <!DOCTYPE html>
    <html>

    <head>
        <title>Lethal Lima's Webpage</title>
        <meta charset="utf-8">
        <style>
            body {
                background-color: teal;
                text-align: center
            }
            
            #main {
                position: relative;
                margin-top: 15px
            }
            
            #loginDiv {
                width: 308px;
                margin-top: 0;
                padding: 28px 25px;
                background-color: #fff;
                border: 1px solid #000;
                border-radius: 5;
                position: absolute;
                left: 50%;
                margin-left: -180px;
                top: 0
            }
            
            #regDiv,
            #mailDiv {
                width: 308px;
                margin-top: 0;
                padding: 28px 25px;
                background-color: #fff;
                display: none;
                border: 1px solid #000;
                border-radius: 5;
                position: absolute;
                left: 50%;
                margin-left: -180px;
                top: 0
            }
            
            h3 {
                margin-top: 0
            }
            
            input[type=text],
            [type=password], textarea{
                padding: 7px;
                width: 100%;
                height: 40px;
                margin-top: 15px;
                font-size: 18px
            }
            
            input[type=button] {
                background: teal;
                width: 308px;
                height: 40px;
                margin-top: 15px;
                font-size: 18px;
                font-weight: 700;
            }
            
            a {
                text-decoration: none;
                color: deepskyblue;
            }
            
            p {
                margin-bottom: 0
            }
            
            .ulist {
                text-align: left;
                color: red;
                font-weight: bolder;
            }
        </style>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <script>
            // Stole & modified the function isAlphaOrParen(str) from: http://stackoverflow.com/questions/2450641/validating-alphabetic-only-string-in-javascript
            function isValidUsername(user) {
                return /^[a-zA-Z0-9]+$/.test(user);
            }
            // Stole & modified from Miljana Grasilovic at http://regexlib.com/Search.aspx?k=password&c=-1&m=5&ps=20
            function isValidPassFormat(pass) {
                return /^(?=(.*[a-zA-Z].*))(?=.*\d.*)(?=.*\W.*)[a-zA-Z0-9\S]{8,}$/.test(pass);
            }
            // Stole from J. Washam at http://regexlib.com/Search.aspx?k=&c=1&m=-1&ps=20
            function isValidEmail(email) {
                return /^[\w-]+(?:\.[\w-]+)*@(?:[\w-]+\.)+[a-zA-Z]{2,7}$/.test(email);
            }
            
            function validateLog(email, message){
                var errors = "";
                if (email === "") {
                    errors = "<li>Enter an email address.</li>";
                } else if (!isValidEmail(email)) {
                    errors = "<li>Enter a valid email address.</li>";
                }
                if (message === "") {
                    errors += "<li>Enter a message.</li>";
                }
                return errors;
            }
            function validate(user, email, pass, confirmpass) {
                var errors = [];

                if (user === "") {
                    errors.push("<li>Enter a username.</li>");
                } else if (!isValidUsername(user)) {
                    errors.push("<li>Enter a valid alphanumeric username starting with an alphabetic character.</li>");
                }
                if (email === "") {
                    errors.push("<li>Enter an email address.</li>");
                } else if (!isValidEmail(email)) {
                    errors.push("<li>Enter a valid email address.</li>");
                }
                if (pass === "") {
                    errors.push("<li>Enter a password.</li>");
                } else if (!isValidPassFormat(pass)) {
                    errors.push("<li>" + "JS: The password must be 8 characters or more with at least one letter, one number, and one special character." + "</li>");
                } else if (pass !== confirmpass) {
                    errors.push("<li>Password does not match the confirm password.</li>");
                }
                return errors;
            }

            function login(e) {
                var user = $("#loginuser").val();
                var pass = $("#loginpass").val();
                if (!isValidUsername(user) || !isValidPassFormat(pass)) {
                    $("#validationErrors").html("<li>JS: Invalid username or password.</li>");
                    $("#loginpass").val("");
                    $("#loginuser").focus();
                } else {
                    $.post(
                        "lab4.php", {
                            loginuser: user,
                            loginpass: pass
                        },
                        function (errors) {
                            if (errors === "") {
                                alert("Signed in!");
                                $("#form")[0].reset();
                                $("#loginDiv").slideUp("slow", function () {
                                    $("#mailDiv").slideDown("slow", function () {
                                        $("#entry").focus();
                                    });
                                });
                            } else {
                                $("#validationErrors").html("<li>" + errors + "</li>");
                                $("#loginpass").val("");
                                $("#loginuser").val("").focus();
                            }
                        }
                    );

                }

                e.preventDefault();
            }

            function register(e) {
                //e.preventDefault();
                var user = $("#registerusername").val();
                var email = $("#registeremail").val();
                var pass = $("#registerpassword").val();
                var confirmpass = $("#confirmpassword").val();
                var errors = validate(user, email, pass, confirmpass);

                if (errors.length === 0) {
                    // AJAX Code To Submit Form.
                    $.post(
                        "lab4.php", {
                            regusername: user,
                            regpassword: pass
                        },
                        function (data) {
                            if (data === "") {
                                alert("Registered!");
                                $("#form")[0].reset();
                                $("#regDiv").hide("slow");
                            } else {
                                //alert()
                                $("#validationErrors2").html("<li>" + data + "</li>");
                                $("#registerpassword").val("");
                                $("#confirmpassword").val("");
                                $("#registerusername").focus();
                            }
                        }
                    );
                    
                    $("#regDiv").slideUp("slow", function () {
                        $("#mailDiv").slideDown("slow");
                    });

                } else {
                    $("#validationErrors2").html("");
                    var i;
                    for (i = 0; i < errors.length; i++) {
                        $("#validationErrors2").append(errors[i]);
                    }
                    $("#confirmpassword").val("");
                    $("#registerusername").focus();
                }
                e.preventDefault();
            }

            function logEntry(e) {
                alert($("select#month option:selected").text() + $("select#day option:selected").text() + $("select#year option:selected").text());
                var errors = validateLog($("#recipientemail").val(), $("#message").val());
                
                if(errors === ""){
                    //alert($("#recipientemail").val() + '\n' + $("#message").val());
                    $.post(
                        "lab4.php", {
                            emailR: $("#recipientemail").val(),
                            message: $("#message").val(),
                            //timestamp: $("#month option:selected").val()
                        },
                        function(errors){
                            if(errors === ""){
                                alert("Messaged Saved!");
                                
                            } else {
                                $("#validationErrors3").html("<li>" + errors + "</li>");
                            }
                                
                        }
                    );
                    $("#mailDiv").slideUp("slow", function(){
                        $("#validationErrors3").html("");
                        $("#recipientemail").val("");
                        $("#message").val("");
                        $("#mailDiv").slideDown("slow");
                    });
                    
                } else{
                    $("#validationErrors3").html("").html(errors);
                }
                    
                e.preventDefault();
            }

            function start() {
                $("#login").focus();
                // add event listeners
                $("#login").click(login);
                $("#register").click(register);
                $("#entry").click(logEntry);
                $("#signup").click(function () {
                    $("#loginDiv").slideUp("slow", function () {
                        $("#regDiv").slideDown("slow");
                        $("#form")[0].reset();
                        $("#validationErrors").html("");
                        $("#register").focus();
                    });
                });
                // On Click SignIn It Will Hide Registration Form and Display Login Form
                $("#signin").click(function () {
                    $("#regDiv").slideUp("slow", function () {
                        $("#loginDiv").slideDown("slow");
                        $("#login").focus();
                    });
                });
                // Let's user press Enter key anyone time they want to login, or register.
                $("#loginuser, #loginpass").keypress(function(key){
                    if(key.keyCode === 13){
                        login();
                    }
                });
                $("#registerusername, #registeremail, #registerpassword, #confirmpassword").keypress(function(key){
                    if(key.keyCode === 13){
                        register();
                    }
                });
            }

            $(document).ready(start);
        </script>
    </head>

    <body>
        <div id="main">
            <!-- Login Form -->
            <div id="loginDiv">
                <form action="" method="post">
                    <h3>Login Meow!</h3>
                    <input id="loginuser" placeholder="Username" type="text">
                    <input id="loginpass" placeholder="Password" type="password">
                    <input id="login" type="button" value="Sign In">
                    <p>Don't have an account? <a class="signup" href="#" id="signup">Register here!</a></p>
                    <ul id="validationErrors" class="ulist"></ul>
                </form>
            </div>
            <!-- Signup Form-->
            <div id="regDiv">
                <form action="" id="form" method="post" name="form">
                    <h3>Register Meow!</h3>
                    <input id="registerusername" placeholder="Username" type="text" >
                    <input id="registeremail" placeholder="Email" type="text" >
                    <input id="registerpassword" placeholder="Password" type="password" >
                    <input id="confirmpassword" placeholder="Confirm Password" type="password" >
                    <input id="register" type="button" value="Create Your Account!">
                    <p>Already have an account? <a class="signin" href="#" id="signin">Sign in!</a></p>
                    <ul id="validationErrors2" class="ulist"></ul>
                </form>
            </div>
            <div id="mailDiv">
                <form action="" method="post">
                    <h3>Mail Entry</h3>
                    <input id="recipientemail" placeholder="Recipient's email" type="text">
                    <h4>Message:</h4>
                    <textarea id="message" placeholder="" type="text"></textarea>
                    <?php
                    $starttime = "00:00";
                    $time = new DateTime($starttime);
                    $interval = new DateInterval("PT30M");
                    $temptime = $time->format("H:i");
                    ?>
                    
                    <?php
                    $date = new DateTime();
                    $months = array(1 => 'Jan', 2 => 'Feb', 3 => 'Mar', 4 => 'Apr', 5 => 'May', 6 => 'Jun', 7 => 'Jul', 8 => 'Aug', 9 => 'Sep', 10 => 'Oct', 11 => 'Nov', 12 => 'Dec');
                    ?>
                    <!-- Day Format -->
                    <select name="month">
                        <?php foreach($months as $key => $month) { ?>
                            <?php $default_month = ($key == $date->format('m'))?'selected':''; ?>
                            <option <?php echo $default_month; ?> value="<?php echo $key; ?>">
                                <?php echo $month; ?>
                            </option>
                        <?php } ?>
                    </select>


                    <select name="day">
                        <?php for($day = 1; $day <= 31; $day++) { ?>
                            <?php $default_day = ($day == $date->format('d'))?'selected':''; ?>
                            <option <?php echo $default_day; ?> value="<?php echo $day; ?>">
                                <?php echo $day; ?>
                            </option>
                        <?php } ?>
                    </select>


                    <select name="year">
                        <?php for($year = $date->format('Y'); $year <= 2021; $year++) { ?>
                            <option value="<?php echo $year; ?>">
                                <?php echo $year; ?>
                            </option>
                        <?php } ?>
                    </select>
                    
                    <select id="time">
                    
                    <!-- Time in 24hr format in 30-minute intervals -->
                    <?php    
                    do {
                       echo "<option>" . $temptime . "</option>";
                       $time->add($interval);
                       $temptime = $time->format("H:i");
                    } while ($temptime !== $starttime);

                    ?>
                    </select>
                    <input id="entry" type="button" value="Schedule Message">
                    <ul id="validationErrors3" class="ulist"></ul>
                </form>
            </div>
        </div>
    </body>

    </html>