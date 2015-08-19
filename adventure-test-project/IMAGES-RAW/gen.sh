#!/bin/sh
VERSION=1.6.4
LIBGDX_BASE_PATH=~/.gradle/caches/modules-2/files-2.1/com.badlogicgames.gdx/
GDX_PATH=`find $LIBGDX_BASE_PATH -name gdx-$VERSION.jar`
GDX_TOOLS_PATH=`find $LIBGDX_BASE_PATH -name gdx-tools-$VERSION.jar`
OUT_DIR=../android/assets/atlases/1/

if [ $# -eq 0 ]
  then
    echo "gen.sh: Image dir parameter must be supplied."
    exit
fi

cp pack.json $1
java -cp $GDX_PATH:$GDX_TOOLS_PATH com.badlogic.gdx.tools.texturepacker.TexturePacker $1 $OUT_DIR $1
