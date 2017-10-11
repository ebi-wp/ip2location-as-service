#!/bin/bash

cd "$( dirname "${BASH_SOURCE[0]}")"

kill $(ps -fu $USER | grep ip2rest | grep -v "grep" | awk '{print $2}')

>nohup.out
