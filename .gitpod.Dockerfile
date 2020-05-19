FROM gitpod/workspace-full-vnc

RUN sudo apt-get update \
    && sudo apt-get install -y openjfx libopenjfx-java matchbox \
    && sudo apt-get clean && sudo rm -rf /var/cache/apt/* && sudo rm -rf /var/lib/apt/lists/* && sudo rm -rf /tmp/*
