#!/bin/bash
# Fail the whole script if any command fails
set -e

echo "------ Start Building ------"
# Build
(mvn clean test)
