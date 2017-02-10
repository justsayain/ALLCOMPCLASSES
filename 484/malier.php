<?php
    date_default_timezone_set('America/Los_Angeles');
    // https://github.com/PHPMailer/PHPMailer
    require 'PHPMailer-master/PHPMailerAutoload.php';
    function newEmail($username, $email, $msg){
        $mail = new PHPMailer(); // create a new object
        $mail->IsSMTP(); // enable SMTP
        $mail->SMTPDebug = 0; // debugging: 1 = errors and messages, 2 = messages only
        $mail->SMTPAuth = true; // authentication enabled
        $mail->SMTPSecure = 'tls'; // secure transfer enabled REQUIRED for Gmail
        $mail->Host = gethostbyname('smtp.gmail.com');
        $mail->Port = 587; // or  465
        $mail->IsHTML(true);
        $mail->Username = "stevedelgado93@gmail.com";
        $mail->Password = "***************";
        $mail->SetFrom("stevedelgado93@gmail.com");
        $mail->Subject = "ttestmail";
        $mail->Body = "$msg<br><br>Regards,<br><br><br>$username";
        $mail->AddAddress($email);

        if(!$mail->Send()) {
            return "Mailer Error: " . $mail->ErrorInfo . "<br>";
        } else {
            return "";
        }
    }
 
    $mysql = new mysqli('localhost','root','','lab4');
    $stmt = $mysql->prepare("SELECT user.username, message.msgid, message.email, message.msg FROM user join message on 
                            user.userid=message.userid and message.sent=0 and timestamp < CURRENT_TIMESTAMP");
    $stmt->execute();
    $stmt->store_result();
    $stmt->bind_result($username, $msgid, $email, $msg);

    
    while($stmt->fetch()){
        // If there was no error, update message table to mark email as sent (1).
        if(newEmail($username, $email, $msg) === ""){
            $stmt2 = $mysql->prepare("UPDATE message set sent=1 where msgid=$msgid");
            $stmt2->execute();
            $stmt2->close();
        }
        else
            exit("Message to $email not sent.");
    }
    $stmt->close();
/*
*/
?>