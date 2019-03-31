javac *.java -cp /home/minecraft/spigot-1.13.2.jar
jar -cf out/mccpu.jar *.class plugin.yml
cp out/mccpu.jar /home/minecraft/plugins/
rm *.class
