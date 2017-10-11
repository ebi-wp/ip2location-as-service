#!/bin/bash

cd "$( dirname "${BASH_SOURCE[0]}")"

nohup java -Djava.io.tmpdir=/net/isilonP/public/rw/homes/es_adm/ip2location/tmp-dir -jar ip2rest-0.0.1-SNAPSHOT.jar &
