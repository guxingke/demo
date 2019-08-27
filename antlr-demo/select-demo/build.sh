#!/usr/bin/env bash

native-image -cp target/select-demo.jar -H:Name=target/sd -H:+ReportUnsupportedElementsAtRuntime --no-server Main

