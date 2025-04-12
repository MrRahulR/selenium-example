FROM maven:3.6.3-openjdk-14
WORKDIR /usr/src/app
COPY . /usr/src/app
CMD ["mvn", "clean", "test"]