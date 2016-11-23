#!/bin/sh

inkscape -w 750 -h 1334 --export-id=1242x2208 --export-png=../../ios/data/Default-375w-667h@2x.png ios-default-images.svg;
inkscape -w 1242 -h 2208 --export-id=1242x2208 --export-png=../../ios/data/Default-414w-736h@3x.png ios-default-images.svg;
inkscape -w 640 -h 1136 --export-id=1242x2208 --export-png=../../ios/data/Default-568h@2x.png ios-default-images.svg;

inkscape -w 320 -h 480 --export-id=320x480 --export-png=../../ios/data/Default.png ios-default-images.svg;
inkscape -w 640 -h 960 --export-id=320x480 --export-png=../../ios/data/Default@2x.png ios-default-images.svg;
inkscape -w 1536 -h 2008 --export-area-page --export-png=../../ios/data/Default@2x~ipad.png  ios-default-images.svg;
inkscape -w 768 -h 1004 --export-area-page --export-png=../../ios/data/Default~ipad.png ios-default-images.svg;
inkscape -w 2048 -h 2732 --export-area-page --export-png=../../ios/data/Default-1024w-1366h@2x~ipad.png ios-default-images.svg;
