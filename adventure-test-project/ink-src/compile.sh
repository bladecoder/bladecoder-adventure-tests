#!/bin/bash

INKLECATE_PATH=$HOME/programs/ink/inklecate.exe
INK_FILE=test-story.ink

$INKLECATE_PATH -o ../assets/model/$INK_FILE.json $INK_FILE
