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
inkscape -w 16 -h 16 --export-area-page --export-png=../../desktop/src/main/resources/icons/icon16.png  icon.svg;
inkscape -w 32 -h 32 --export-area-page --export-png=../../desktop/src/main/resources/icons/icon32.png  icon.svg;
inkscape -w 128 -h 128 --export-area-page --export-png=../../desktop/src/main/resources/icons/icon128.png  icon.svg;
inkscape -w 256 -h 256 --export-area-page --export-png=../../desktop/src/main/resources/icons/icon256.png  icon.svg;

#IOS
inkscape -w 1024 -h 1024 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/app-store-icon-1024@1x.png  icon.svg;
inkscape -w 76 -h 76 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/ipad-app-icon-76@1x.png  icon.svg;
inkscape -w 152 -h 152 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/ipad-app-icon-76@2x.png  icon.svg;
inkscape -w 20 -h 20 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/ipad-notifications-icon-20@1x.png  icon.svg;
inkscape -w 40 -h 40 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/ipad-notifications-icon-20@2x.png  icon.svg;
inkscape -w 167 -h 167 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/ipad-pro-app-icon-83.5@2x.png  icon.svg;
inkscape -w 29 -h 29 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/ipad-settings-icon-29@1x.png  icon.svg;
inkscape -w 58 -h 58 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/ipad-settings-icon-29@2x.png  icon.svg;
inkscape -w 40 -h 40 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/ipad-spotlight-icon-40@1x.png  icon.svg;
inkscape -w 80 -h 80 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/ipad-spotlight-icon-40@2x.png  icon.svg;
inkscape -w 120 -h 120 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/iphone-app-icon-60@2x.png  icon.svg;
inkscape -w 180 -h 180 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/iphone-app-icon-60@3x.png  icon.svg;
inkscape -w 40 -h 40 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/iphone-notification-icon-20@2x.png  icon.svg;
inkscape -w 60 -h 60 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/iphone-notification-icon-20@3x.png  icon.svg;
inkscape -w 80 -h 80 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/iphone-spotlight-icon-40@2x.png  icon.svg;
inkscape -w 120 -h 120 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/iphone-spotlight-icon-40@3x.png  icon.svg;
inkscape -w 58 -h 58 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/iphone-spotlight-settings-icon-29@2x.png  icon.svg;
inkscape -w 87 -h 87 --export-area-page --export-png=../../ios/data/Media.xcassets/AppIcon.appiconset/iphone-spotlight-settings-icon-29@3x.png  icon.svg;




#convert ../../desktop/src/icons/icon*.png icon.ico
#png2icns icon.icns ../../desktop/src/icons/icon*.png


