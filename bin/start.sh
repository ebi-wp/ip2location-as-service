#!/bin/bash

IP2LOC=$HOME/ip2location-as-service/
VERSION=0.0.2

java -Djava.io.tmpdir=$IP2LOC/tmp-dir -jar $IP2LOC/target/ip2rest-$VERSION.jar
