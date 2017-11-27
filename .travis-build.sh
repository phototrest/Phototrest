#!/bin/bash
ROOT=$TRAVIS_BUILD_DIR
# Fail the whole script if any command fails
set -e

echo "------ Start Building ------"
# Build
(cd $ROOT/phototrest/Phototrest && mvn clean test)
