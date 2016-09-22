#! /bin/bash
#docker run --rm -it -v `pwd`:/data:private -e "GRADLE_USER_HOME=/root/.gradle" sirmc/ds-build
docker run --rm -it -v `pwd`:/data:private sirmc/ds-build
