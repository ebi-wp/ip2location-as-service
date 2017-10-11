#!/bin/bash

cd "$( dirname "${BASH_SOURCE[0]}")/.."
java -Djava.io.tmpdir=tmp-dir -jar target/ip2rest-0.0.2.jar
