dev: ensure-conf build
	cp target/dataservices-0.0.1-SNAPSHOT.jar src/main/resources
	cd src/main/resources; java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -jar dataservices-0.0.1-SNAPSHOT.jar

build:
	mvn clean package -DskipTests

ensure-conf:
	if [[ ! -f src/main/resources/application.properties ]]; then vim -O src/main/resources/application.properties src/main/resources/application.properties.example ; fi
