#!/usr/bin/env bash

native-image -cp target/bytes-demo.jar -H:Name=lmoskrlimg -H:+ReportUnsupportedElementsAtRuntime --no-server com.gxk.demo.Main
