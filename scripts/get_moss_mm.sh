#!/bin/bash

rm -rf ../external/moss
mkdir -p ../external/moss
cd ../external/moss
wget www.ontko.com/moss/memory/memory.tgz
tar -xf memory.tgz
