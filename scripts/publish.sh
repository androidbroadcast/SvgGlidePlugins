#!/usr/bin/env bash
./gradlew :library:assembleRelease :library:bintrayUpload -PdisablePreDex
