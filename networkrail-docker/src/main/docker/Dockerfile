FROM area51/kernel:latest-opendata
MAINTAINER Peter Mount <peter@retep.org>

# Default cache base directory
ENV FILESYSTEM_BASE                 /opt/filesystem

# Default location of AWS credentials if S3 is used
ENV AWS_CREDENTIAL_PROFILES_FILE    /opt/kernel/etc/.aws

ADD ${project.build.finalName}-assembly.tar /opt/kernel/
