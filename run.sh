#!/usr/bin/env bash
#cd /Users/ellioe03/workspace/serverless-examples/aws-java-simple-http-endpoint
#mvn exec:java -Dexec.executable="java"  -Dexec.args=$@

# This script starts the application.  It is used by the cucumber tests (under-test.rb).

BASE_DIR="$( dirname "$0" )"

jar="$( echo "$BASE_DIR"/target/aws-java-simple-http-endpoint.jar )"
exec java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar "$jar" "$@"
