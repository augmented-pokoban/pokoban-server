#!/bin/bash
mvn clean install -U
mvn -s settings.xml azure-webapp:deploy
