call mvn package
call docker build --rm -t lemanoman/websocket-server:latest . -f docker/Dockerfile
call docker login -u %DOCKER_USER% -p%DOCKER_PWD%
call docker push lemanoman/websocket-server:latest
