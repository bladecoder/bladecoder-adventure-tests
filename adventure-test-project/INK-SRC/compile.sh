#!/bin/bash

INKLECATE_PATH=/home/rgarcia/programs/inklecate/inklecate.exe
INK_FILE=test-story.ink

$INKLECATE_PATH -o ../android/assets/model/$INK_FILE.json $INK_FILE
