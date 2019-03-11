#!/usr/bin/env bash

native-image -cp target/lpc-demo.jar -H:Name=lpc-demo -H:+ReportUnsupportedElementsAtRuntime --no-server 