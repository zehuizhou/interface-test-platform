#!/bin/bash
source /etc/profile

none_images=`docker images -f "dangling=true" -q`
if [ "${none_images}x" != "x" ]; then
    docker rmi ${none_images}
fi