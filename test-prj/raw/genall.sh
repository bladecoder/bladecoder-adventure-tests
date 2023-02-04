#!/bin/bash

for a in `find . -type d ! -iname ui ! -iname . -printf "%f\n"`; do
./gen.sh $a
done
