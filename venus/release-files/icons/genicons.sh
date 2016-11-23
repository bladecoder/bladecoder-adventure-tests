#!/bin/sh

#ANDROID ICONS
inkscape -w 192 -h 192 --export-area-page --export-png=../../android/res/drawable-xxxhdpi/ic_launcher.png  icon.svg;
inkscape -w 144 -h 144 --export-area-page --export-png=../../android/res/drawable-xxhdpi/ic_launcher.png  icon.svg;
inkscape -w 96 -h 96 --export-area-page --export-png=../../android/res/drawable-xhdpi/ic_launcher.png  icon.svg;
inkscape -w 72 -h 72 --export-area-page --export-png=../../android/res/drawable-hdpi/ic_launcher.png  icon.svg;
inkscape -w 48 -h 48 --export-area-page --export-png=../../android/res/drawable-mdpi/ic_launcher.png  icon.svg;
#inkscape -w 36 -h 36 --export-area-page --export-png=../../android/res/drawable-ldpi/ic_launcher.png  icon.svg;

inkscape -w 512 -h 512 --export-area-page --export-png=../../android/ic_launcher-web.png  icon.svg;


#DESKTOP
inkscape -w 16 -h 16 --export-area-page --export-png=../../desktop/src/icons/icon16.png  icon.svg;
inkscape -w 32 -h 32 --export-area-page --export-png=../../desktop/src/icons/icon32.png  icon.svg;
inkscape -w 128 -h 128 --export-area-page --export-png=../../desktop/src/icons/icon128.png  icon.svg;
inkscape -w 256 -h 256 --export-area-page --export-png=../../desktop/src/icons/icon256.png  icon.svg;

#IOS
inkscape -w 57 -h 57 --export-area-page --export-png=../../ios/data/Icon.png  icon.svg;
inkscape -w 114 -h 114 --export-area-page --export-png=../../ios/data/Icon@2x.png  icon.svg;
inkscape -w 72 -h 72 --export-area-page --export-png=../../ios/data/Icon-72.png  icon.svg;
inkscape -w 144 -h 144 --export-area-page --export-png=../../ios/data/Icon-72@2x.png  icon.svg;

#IOS 7.0
inkscape -w 76 -h 76 --export-area-page --export-png=../../ios/data/Icon-76.png  icon.svg;
inkscape -w 152 -h 152 --export-area-page --export-png=../../ios/data/Icon-76@2x.png  icon.svg;
inkscape -w 120 -h 120 --export-area-page --export-png=../../ios/data/Icon-120.png  icon.svg;



convert ../../desktop/src/icons/icon*.png icon.ico
png2icns icon.icns ../../desktop/src/icons/icon*.png


