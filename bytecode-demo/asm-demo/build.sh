#!/usr/bin/env bash

native-image -cp target/asm-demo.jar -H:Name=asm-demo -H:+ReportUnsupportedElementsAtRuntime --no-server 