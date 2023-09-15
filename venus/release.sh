#!/bin/bash
set -e

DIST_DIR=$HOME"/PACKAGES"
PROJECT_NAME=venus
IOS_PROVISIONING_PROFILE=Venus

if [ "$#" -eq 0 ]
then
  echo "Release type param needed: amazon, apk, aab, ios, macos, itchio or steam"
  exit 0
else
  RELEASE_MODE=$1
fi

echo -n "Version: "
read VERSION
echo

VERSION_CODE=$((`echo $VERSION | cut -d. -f1` * 100 + `echo $VERSION | cut -d. -f2`))

if [[ "$OSTYPE" == 'darwin'* ]]; then
  sed -i .bak 's/version=.*/version='$VERSION'/' gradle.properties
else
  sed -i 's/version=.*/version='$VERSION'/' gradle.properties
fi

if [ "$RELEASE_MODE" == "amazon" ]  || [ "$RELEASE_MODE" == "apk" ]   || [ "$RELEASE_MODE" == "aab" ]  || [ "$RELEASE_MODE" == "underground" ]; then
  echo -n "Keystore Password: "
  read -s KEYSTORE_PASSWD
  echo
  echo -n "Key Password: "
  read -s KEY_PASSWD
  echo
  if [ "$RELEASE_MODE" == "aab" ] ; then
       RELFILENAME="$DIST_DIR"/$PROJECT_NAME-$VERSION.aab

       ./gradlew -Pkeystore=$HOME/Dropbox/docs/ids/rgarcia_android.keystore -PstorePassword=$KEYSTORE_PASSWD -Palias=$PROJECT_NAME -PkeyPassword=$KEY_PASSWD android:bundleRelease -Pversion=$VERSION  -PversionCode=30000$VERSION_CODE -Passet_pack
       cp android/build/outputs/bundle/release/android-release.aab "$RELFILENAME"
  else
    if [ "$RELEASE_MODE" == "amazon" ] ; then
      #sed -i 's/bonasera_android_url=.*/bonasera_android_url=amzn:\/\/apps\/android?asin=B01MU6RADE/' assets/BladeEngine.properties
      RELFILENAME="$DIST_DIR"/$PROJECT_NAME-amazon-$VERSION.apk
    else
      #sed -i 's/bonasera_android_url=.*/bonasera_android_url=https\:\/\/play.google.com\/store\/apps\/details?id\=com.bladecoder.lj/' assets/BladeEngine.properties
      RELFILENAME="$DIST_DIR"/$PROJECT_NAME-$VERSION.apk
    fi

    ./gradlew -Pkeystore=$HOME/Dropbox/docs/ids/rgarcia_android.keystore -PstorePassword=$KEYSTORE_PASSWD -Palias=bladecoder -PkeyPassword=$KEY_PASSWD android:assembleRelease -Pversion=$VERSION  -PversionCode=$VERSION_CODE

    cp android/build/outputs/apk/release/android-release.apk "$RELFILENAME"

    if [ "$RELEASE_MODE" == "amazon" ] ; then
      # Restore BladeEngine.properties
      git checkout android/assets/BladeEngine.properties
    fi
  fi

elif [[ "$RELEASE_MODE" == "steam" ]]; then
  RELFILENAME="$DIST_DIR"/$PROJECT_NAME-steam-$VERSION.jar

  ./gradlew desktop:dist -Pversion=$VERSION -Psteam=true
  cp desktop/build/libs/$PROJECT_NAME-desktop-$VERSION.jar "$RELFILENAME"
elif [[ "$RELEASE_MODE" == "mac" ]]; then
  RELFILENAME="$DIST_DIR"/$PROJECT_NAME-mac-$VERSION.jar

  ./gradlew desktop:dist -Pversion=$VERSION -Psteam=false
  cp desktop/build/libs/$PROJECT_NAME-desktop-$VERSION.jar "$RELFILENAME"
elif [[ "$RELEASE_MODE" == "itchio" ]]; then
  sed -i 's/bonasera_desktop_url=.*/bonasera_desktop_url=https:\/\/bladecoder.itch.io\/johnny-bonasera/' assets/BladeEngine.properties
  RELFILENAME="$DIST_DIR"/$PROJECT_NAME-itchio-$VERSION.jar

  ./gradlew desktop:dist -Pversion=$VERSION -Psteam=false
  cp desktop/build/libs/$PROJECT_NAME-desktop-$VERSION.jar "$RELFILENAME"  
elif [[ "$RELEASE_MODE" == "ios" ]]; then
  RELFILENAME="$DIST_DIR"/$PROJECT_NAME-$VERSION.ipa

  # Update ios/robovm.properties
  sed -i .bak 's/app.version=.*/app.version='$VERSION'/' ios/robovm.properties
  sed -i .bak 's/app.build=.*/app.build='$VERSION_CODE'/' ios/robovm.properties

  ./gradlew -Probovm.iosSignIdentity="Apple Distribution" -Probovm.iosProvisioningProfile="$IOS_PROVISIONING_PROFILE" ios:clean ios:createIPA

  cp ios/build/robovm/IOSLauncher.ipa "$RELFILENAME"

  PASSWS=`cat $HOME/.config/upload-app-pass.local`
  #xcrun altool --upload-app --type ios --file "$RELFILENAME" --username bladecoder@gmail.com --password $PASSWS
else
  echo Release type param not valid: $RELEASE_MODE. Valid options: amazon, apk, aab, ios or steam.
  exit -1
fi

echo -- RELEASE OK: $RELFILENAME --
