#!/bin/bash

for a in `ls -d */`; do
./gen.sh $a
done
