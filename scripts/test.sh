#!/bin/sh
# vi: set expandtab shiftwidth=4 softtabstop=4

docker run -it --rm -v $PWD:/src -w /src gradle gradle test
