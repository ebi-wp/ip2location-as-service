#### Requirements

* IP2Location client JAR
* IP2Location license
* IP2Location database file

#### Set up Maven
Install IP2Location jar locally with:
```
mvn install:install-file -Dfile=<jar_path> \
-DgroupId=com.ip2location \
-DartifactId=java \
-Dversion=0.0.2 \
-Dpackaging=jar
```

#### Run

```bin/start.sh```

Go to [http://localhost:8080/wp/ip2loc/](http://localhost:8080/wp/ip2loc/)

