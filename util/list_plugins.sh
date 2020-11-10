#!/usr/bin/env bash

rm installed
rm deps

find /home/jenkins/jenkins_home/plugins/ -name MANIFEST.MF -exec /home/jenkins/collect_plugins.sh {} \;