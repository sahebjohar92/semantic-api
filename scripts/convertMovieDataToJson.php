<?php

$file = fopen("tmdb_5000_movies.csv","r");

$isStart = true;
$label = array();
$finalArray = array();

while(! feof($file))
{
    $fileContents = fgetcsv($file);
    $size = count($fileContents);

    $movieArray = array();
    for ($i=0; $i < $size; $i++) {

        if ($isStart) {

            array_push($label, $fileContents[$i]);
        } else {

            if ($label[$i] == 'keywords' || $label[$i] == 'production_companies') {
                continue;
            }

            $movieArray[$label[$i]] = preProcessData($fileContents[$i]);
        }
    }

    if (!$isStart) {
        array_push($finalArray, $movieArray);
    }

    $isStart = false;
}


$movieArr['data'] = $finalArray;

append_in_file($movieArr);

fclose($file);

function append_in_file ($arr) {

    $arrJson = json_encode($arr);

    $myfile = fopen("song_new_data.json", "w") or die("Unable to open file!");
    fwrite($myfile, $arrJson);
    fclose($myfile);
}

function preProcessData($data) {

    $object = json_decode($data, true);

    $size = count($object);

    $array = array();
    if ($size > 1) {

        for ($i=0; $i<$size; $i++) {

            $innerArr = $object[$i];

            //$arr['name'] = $innerArr['name'];

            array_push($array, $innerArr['name']);

        }
        return $array;
    } else {
        return $data;
    }
}

function cleanData($data) {

    $data = trim($data);
    $data = str_replace($data, "http://", "");

    return $data;
}

?>