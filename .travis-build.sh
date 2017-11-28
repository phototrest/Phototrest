#!/bin/bash
ROOT=$TRAVIS_BUILD_DIR/
# Fail the whole script if any command fails
set -e

echo "------ Start Building ------"
# Build
(mvn test)
