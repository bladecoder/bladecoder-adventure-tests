#!/bin/sh

rm *.png

inkscape -w 512 -h 512 --export-area-page --export-png=icon512.png  icon.svg;
inkscape -w 144 -h 144 --export-area-page --export-png=icon144.png  icon.svg;
inkscape -w 96 -h 96 --export-area-page --export-png=icon96.png  icon.svg;
inkscape -w 72 -h 72 --export-area-page --export-png=icon72.png  icon.svg;
inkscape -w 48 -h 48 --export-area-page --export-png=icon48.png  icon.svg;
inkscape -w 36 -h 36 --export-area-page --export-png=icon36.png  icon.svg;

inkscape -w 16 -h 16 --export-area-page --export-png=icon16.png  icon.svg;
inkscape -w 32 -h 32 --export-area-page --export-png=icon32.png  icon.svg;
inkscape -w 128 -h 128 --export-area-page --export-png=icon128.png  icon.svg;
