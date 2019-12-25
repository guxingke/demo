#!/usr/bin/env bash

native-image -cp target/mix-zk.jar -H:Name=mix-zk -H:+ReportUnsupportedElementsAtRuntime --no-server 