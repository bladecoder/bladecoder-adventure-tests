#!/bin/sh
VERSION=1.10.0
LIBGDX_BASE_PATH=$HOME/.gradle/caches/modules-2/files-2.1/com.badlogicgames.gdx/
GDX_PATH=`find $LIBGDX_BASE_PATH -name gdx-$VERSION.jar`
GDX_TOOLS_PATH=`find $LIBGDX_BASE_PATH -name gdx-tools-$VERSION.jar`
OUT_DIR=../assets/ui/1/

#cp pack.json ui
java -cp $GDX_PATH:$GDX_TOOLS_PATH com.badlogic.gdx.tools.texturepacker.TexturePacker ui $OUT_DIR ui
