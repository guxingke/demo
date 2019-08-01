#!/usr/bin/env bash

native-image -cp target/mix-xxx.jar \
  -H:Name=mix-xxx \
  -H:ReflectionConfigurationFiles=reflections.json \
  -H:+ReportUnsupportedElementsAtRuntime \
  --no-server \
  com.gxk.demo.Main
