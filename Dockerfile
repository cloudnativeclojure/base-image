FROM clojure:openjdk-11-tools-deps

# TODO: set :mvn/local-repo in deps.edn to something other than ~/.m2/repository, to setup caching

# Don't run as root

LABEL maintainer="Jason Whitlark <jason@whitlark.org>"


WORKDIR /usr/src/
COPY deps.edn /usr/src/
RUN echo '(println "Caching common dependencies.")' | clj -

Run rm /usr/src/deps.edn