#!/bin/bash

rm -rf ../external/moss
mkdir -p ../external/moss
cd ../external/moss
wget www.ontko.com/moss/memory/memory.tgz
tar -xf memory.tgz

find . -name '*.java' -exec sed -i '/^import [A-Z]/d' {} +
