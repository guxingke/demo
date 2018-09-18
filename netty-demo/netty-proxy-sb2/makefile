#!/bin/bash

PROJECT_NAME := netty-proxy-sb2

DIST_DIR := target
STATIC_FILES := Dockerfile
STATIC_FOLDERS := bin

STATIC_CONF_FILES := makefile
STATIC_CONF_FOLDERS := bin

build:
	mvn package -DskipTests -U

run-local:
	java -jar target/netty-proxy-sb2.jar

test:
	mvn test -U

package:
	@cd $(DIST_DIR) && zip -r $(PROJECT_NAME).zip $(PROJECT_NAME).jar
	@zip -g $(DIST_DIR)/$(PROJECT_NAME).zip $(STATIC_FILES)
	@zip -r -g $(DIST_DIR)/$(PROJECT_NAME).zip $(STATIC_FOLDERS)

package_conf:
	@zip -g $(DIST_DIR)/$(PROJECT_NAME)_conf.zip $(STATIC_CONF_FILES)
	@zip -r -g $(DIST_DIR)/$(PROJECT_NAME)_conf.zip $(STATIC_CONF_FOLDERS)

clean:
	mvn clean

deploy:
	echo deploy

deploy_docker:
	echo deploy_docker