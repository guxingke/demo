#!/usr/bin/env bash

native-image -cp target/{{name}}.jar -H:Name={{name}} -H:+ReportUnsupportedElementsAtRuntime --no-server {{mainClass}}