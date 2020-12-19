#makefile for purplelab-api-admincenter
compile:
	@gradle build --daemon

clean:
	@gradle clean

execute:
	@java -jar build/libs/api-0.0.1-SNAPSHOT.jar
