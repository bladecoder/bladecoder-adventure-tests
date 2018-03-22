#!/bin/bash
set -e

DIST_DIR=$HOME"/PACKAGES"
PROJECT_NAME=venus
IOS_PROVISIONING_PROFILE=Venus

if [ "$#" -eq 0 ]
then
  echo "Release type param needed: amazon, android, ios, macos or steam"
  exit 0
else
  RELEASE_MODE=$1
fi

echo -n "Version: "
read VERSION
echo

if [[ "$OSTYPE" == 'darwin'* ]]; then
  sed -i .bak 's/version=.*/version='$VERSION'/' gradle.properties
else
  sed -i 's/version=.*/version='$VERSION'/' gradle.properties
fi

if [ "$RELEASE_MODE" == "amazon" ]  || [ "$RELEASE_MODE" == "android" ]  || [ "$RELEASE_MODE" == "underground" ]; then

  echo -n "Keystore Password: "
  read -s KEYSTORE_PASSWD
  echo
  echo -n "Key Password: "
  read -s KEY_PASSWD
  echo

  if [ "$RELEASE_MODE" == "amazon" ] ; then
    sed -i 's/bonasera_android_url=.*/bonasera_android_url=amzn:\/\/apps\/android?asin=B01MU6RADE/' assets/BladeEngine.properties
    RELFILENAME="$DIST_DIR"/$PROJECT_NAME-amazon-$VERSION.apk
  else
    RELFILENAME="$DIST_DIR"/$PROJECT_NAME-$VERSION.apk
  fi

  ./gradlew -Pkeystore=$HOME/Dropbox/docs/ids/rgarcia_android.keystore -PstorePassword=$KEYSTORE_PASSWD -Palias=bladecoder -PkeyPassword=$KEY_PASSWD android:assembleFullRelease -Pversion=$VERSION --stacktrace

  cp android/build/outputs/apk/android-full-release.apk "$RELFILENAME"

  if [ "$RELEASE_MODE" == "amazon" ] ; then
    # Restore BladeEngine.properties
    git checkout android/assets/BladeEngine.properties
  fi

elif [[ "$RELEASE_MODE" == "steam" ]]; then
  RELFILENAME="$DIST_DIR"/$PROJECT_NAME-steam-$VERSION.jar

  ./gradlew desktop:dist -Pversion=$VERSION -Psteam=true
  cp desktop/build/libs/$PROJECT_NAME-desktop-$VERSION.jar "$RELFILENAME"
elif [[ "$RELEASE_MODE" == "mac" ]]; then
  RELFILENAME="$DIST_DIR"/$PROJECT_NAME-mac-$VERSION.jar

  ./gradlew desktop:dist -Pversion=$VERSION -Psteam=false
  cp desktop/build/libs/$PROJECT_NAME-desktop-$VERSION.jar "$RELFILENAME"
elif [[ "$RELEASE_MODE" == "ios" ]]; then
  RELFILENAME="$DIST_DIR"/$PROJECT_NAME-$VERSION.ipa

  echo -n "Version Code: "
  read VERSION_CODE
  echo

  # Update ios/robovm.properties
  sed -i .bak 's/app.version=.*/app.version='$VERSION'/' ios/robovm.properties
  sed -i .bak 's/app.build=.*/app.build='$VERSION_CODE'/' ios/robovm.properties

  ./gradlew -Probovm.iosSignIdentity="iPhone Distribution" -Probovm.iosProvisioningProfile="$IOS_PROVISIONING_PROFILE" ios:clean ios:createIPA

  cp ios/build/robovm/IOSLauncher.ipa "$RELFILENAME"
else
  echo Release type param not valid: $RELEASE_MODE. Valid options: amazon, android, ios or steam.
  exit -1
fi

echo -- RELEASE OK: $RELFILENAME --
