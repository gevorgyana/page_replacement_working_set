#!/bin/sh

# run from 'project_root'/scripts
top_dir=$(cd ..; pwd)
downloaded=${top_dir}/external/moss
destination=${top_dir}/src/main/java
fixes=${top_dir}/fixes

rm -rf $downloaded && \
    mkdir -p $downloaded

cd $downloaded && \
    wget www.ontko.com/moss/memory/memory.tgz && \
    tar -xf memory.tgz

# remove imports that cause compilation errors
find . -name '*.java' -exec sed -i '/^import [A-Z]/d' {} +

# there are bugs in the original version
patch $downloaded/ControlPanel.java $fixes/patch1

cd $top_dir/external/moss && \
    cp -f *.java $destination
