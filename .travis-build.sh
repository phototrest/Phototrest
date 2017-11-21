#!/bin/bash
ROOT=$TRAVIS_BUILD_DIR/
# Fail the whole script if any command fails
set -e

echo "------ Downloading Phototrest ------"
# Clone Phototrest
if [ -d $ROOT/Phototrest ] ; then
    (cd $ROOT/Phototrest && git pull)
else
    (cd $ROOT/ && git clone --depth 1 https://github.com/phototrest/Phototrest.git )
fi

# Build 
(cd $ROOT/Phototrest && mvn clean package)
