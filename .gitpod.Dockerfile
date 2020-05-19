FROM gitpod/workspace-full-vnc

RUN sudo apt-get update \
    && sudo apt-get install -y openjfx libopenjfx-java matchbox \
    && sudo apt-get clean && rm -rf /var/cache/apt/* && rm -rf /var/lib/apt/lists/* && rm -rf /tmp/*
    
USER gitpod
