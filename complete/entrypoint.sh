#!/bin/sh

setup_ssh() {
  echo "Create App Services root password"
  echo "root:Docker!" | chpasswd

  echo "Create ssh host keys"
  ssh-keygen -A

  echo "Start ssh server"
  /usr/sbin/sshd
}

start_app(){
  echo "the app starts"
  #java -jar dialog-cg-ws-exec.jar --spring.config.location=file:/config --spring.profiles.active=dev
  java -Djava.security.egd=file:/dev/./urandom -jar app.jar
}

# only start ssh when necessary
echo "hello world"
echo $ENABLE_SSH | grep -qi true && setup_ssh

start_app