#!/bin/sh

UI_DIR=android/assets/ui
ORG=~/git/bladecoder-adventure-engine/adventure-composer/src/main/resources/projectTmpl/$UI_DIR

cp $ORG/1/ui.* ~/git/bladecoder-adventure-tests/adventure-test-3d/$UI_DIR/1
cp $ORG/ui.json ~/git/bladecoder-adventure-tests/adventure-test-3d/$UI_DIR

cp $ORG/1/ui.* ~/git/bladecoder-adventure-tests/adventure-test-project/$UI_DIR/1
cp $ORG/ui.json ~/git/bladecoder-adventure-tests/adventure-test-project/$UI_DIR

cp $ORG/1/ui.* ~/git/bladecoder-adventure-tests/adventure-test-spine/$UI_DIR/1
cp $ORG/ui.json ~/git/bladecoder-adventure-tests/adventure-test-spine/$UI_DIR

echo SKIN FOR TEST PROJECTS UPDATED. VENUS PROJECT NOT UPDATED!
