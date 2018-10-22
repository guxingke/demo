#!/usr/bin/env bash

mvn clean && mvn package

native-image -cp ./target/com.gxk.demo.aot.Example.jar -H:Name=target/aot-cli -H:ReflectionConfigurationFiles=./target/cli-reflect.json -H:+ReportUnsupportedElementsAtRuntime --no-server com.gxk.demo.aot.Example
