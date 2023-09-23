.PHONY: all build docker_up stop clean rebuild

all: build docker_up

build:
	docker-compose up db -d
	gradle build

docker_up: build
	docker-compose up -d

stop:
	docker stop rest postgres_clients

clean:
	docker rm rest postgres_clients

rebuild: stop clean build docker_up
