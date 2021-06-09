#!/usr/bin/env bash

native-image -cp target/compile-demo.jar -H:Name=compile-demo -H:+ReportUnsupportedElementsAtRuntime --no-server 