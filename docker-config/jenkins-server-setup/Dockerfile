# Docker configuration for Jenkins - Android setup
# Credits to https://hub.docker.com/u/xmartlabs/ for idea and major part of this config 

# Creating AndroidSdk image
FROM ubuntu

RUN apt-get update && apt-get install -my wget gnupg
RUN echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee /etc/apt/sources.list.d/webupd8team-java.list
RUN echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886
RUN apt-get update
RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | debconf-set-selections

RUN apt-get update && apt-get install -y \
        gradle \
        oracle-java8-installer \
        oracle-java8-set-default \
        unzip \
        wget \
    && rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
ENV ANDROID_HOME /opt/android-sdk-linux
ENV PATH ${PATH}:${ANDROID_HOME}/tools:${ANDROID_HOME}/tools/bin:${ANDROID_HOME}/platform-tools

WORKDIR /opt

ARG SDK_FILE=android-sdk.zip

RUN wget -O ${SDK_FILE} https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip \
    && unzip ${SDK_FILE} -d ${ANDROID_HOME} \
    && rm ${SDK_FILE}

RUN mkdir $HOME/.android && touch $HOME/.android/repositories.cfg

RUN yes | sdkmanager --licenses

VOLUME /opt/android-sdk-linux

# Creating Jenkins image on top of the AndroidSdk image
FROM jenkins/jenkins

ENV ANDROID_HOME /opt/android-sdk-linux
COPY --from=0 ${ANDROID_HOME} ${ANDROID_HOME}
COPY --from=0 /usr/lib/jvm/java-8-oracle /usr/lib/jvm/java-8-oracle
COPY --from=0 /usr/bin/gradle /usr/bin/gradle
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
ENV PATH ${PATH}:${ANDROID_HOME}/tools:${ANDROID_HOME}/platform-tools

USER root
RUN chown -R jenkins:jenkins ${ANDROID_HOME}
USER jenkins
RUN /usr/local/bin/install-plugins.sh git gradle android-emulator monitoring google-play-android-publisher android-signing copyartifact ftppublisher

ENV ANDROID_EMULATOR_FORCE_32BIT true