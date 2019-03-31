#!/bin/bash
NAME=MCCPU
FILES=$(ls *.java | sed 's/\..*//' | tr '\n' ',')
javac $(sed 's/,/.java /g' <<< $FILES) -cp /home/minecraft/spigot-1.13.2.jar
jar -cf out/$NAME.jar $(sed 's/,/.class /g' <<< $FILES) plugin.yml
rm *.class
cp out/$NAME.jar /home/minecraft/plugins/
