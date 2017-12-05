<?php

$file = fopen("songs.csv","r");

$isStart = true;
$label = array();
$finalArray = array();

$labelArr = array("Position" => "position", "Track Name" => "track", "Artist" => "artist", "Streams" => "stream",
                  "URL" => "url", "Date" => "date", "Region" => "region");

while(! feof($file))
{
    $fileContents = fgetcsv($file);
    $size = count($fileContents);

    $movieArray = array();
    for ($i=0; $i < $size; $i++) {

        if ($isStart) {

            array_push($label, $fileContents[$i]);
        } else {

            if ($labelArr[$label[$i]] == 'position' || $labelArr[$label[$i]] == 'region') {
                continue;
            }

            $movieArray[$labelArr[$label[$i]]] = $fileContents[$i];
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

    $myfile = fopen("song_data.json", "w") or die("Unable to open file!");
    fwrite($myfile, $arrJson);
    fclose($myfile);
}