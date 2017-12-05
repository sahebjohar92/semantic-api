<?php

$file = fopen("BX-Books.csv","r");

$isStart = true;
$label = array();
$finalArray = array();

$labelArr = array("ISBN" => "isbn","Book-Title" => "title", "Book-Author" => "author", "Year-Of-Publication" => "year", "Publisher" => "publisher");

$i = 0;
while(! feof($file))
{
    $fileContents = fgetcsv($file);

    $size = count($fileContents);

    $movieArray = array();
    for ($i=0; $i < $size; $i++) {

        if ($isStart) {

            array_push($label, $fileContents[$i]);
        } else {

            if ($labelArr[$label[$i]] == null) {

                continue;
            }
            $movieArray[$labelArr[$label[$i]]] = $fileContents[$i];
        }
    }

    if (!$isStart) {
        array_push($finalArray, $movieArray);
    }

    $isStart = false;

    $i++;
}


$movieArr['data'] = $finalArray;

append_in_file($movieArr);

fclose($file);

function append_in_file ($arr) {

    var_dump($arr);

    $arrJson = json_encode($arr);

    var_dump($arrJson);

    //var_dump(strlen($arrJson));

    $myfile = fopen("book_data.json", "a") or die("Unable to open file!");
    fwrite($myfile, $arrJson);
    fclose($myfile);
}