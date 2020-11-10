#!groovy

import jenkins.model.*
import java.util.logging.Logger

def logger = Logger.getLogger("")
def installed = false
def initialized = false

def pluginParameter = "\
checks-api:1.1.0 \
pipeline-build-step:2.13 \
matrix-auth:2.6.4 \
pipeline-rest-api:2.18 \
pipeline-stage-view:2.18 \
github-branch-source:2.9.1 \
ssh:2.6.1 \
ant:1.11 \
workflow-step-api:2.23 \
build-user-vars-plugin:1.6 \
groovy-postbuild:2.5 \
github:1.32.0 \
lockable-resources:2.10 \
ssh-credentials:1.18.1 \
workflow-durable-task-step:2.36 \
build-timeout:1.20 \
credentials:2.3.13 \
git:4.4.5 \
git-server:1.9 \
ace-editor:1.1 \
groovy:2.3 \
pipeline-input-step:2.12 \
structs:1.20 \
echarts-api:4.9.0-2 \
email-ext:2.79 \
configuration-as-code-groovy:1.1 \
credentials-binding:1.24 \
ws-cleanup:0.38 \
workflow-cps-global-lib:2.17 \
configuration-as-code:1.46 \
branch-api:2.6.1 \
ldap:1.26 \
pipeline-stage-tags-metadata:1.7.2 \
mask-passwords:2.13 \
plugin-util-api:1.4.0 \
pipeline-milestone-step:1.3.1 \
junit:1.43 \
pipeline-model-api:1.7.2 \
jsch:0.1.55.2 \
resource-disposer:0.14 \
workflow-job:2.40 \
authorize-project:1.3.0 \
pipeline-model-definition:1.7.2 \
workflow-cps:2.85 \
display-url-api:2.3.3 \
workflow-api:2.40 \
pipeline-graph-analysis:1.10 \
workflow-basic-steps:2.23 \
durable-task:1.35 \
handlebars:1.1.1 \
scm-api:2.6.4 \
trilead-api:1.0.12 \
ssh-steps:2.0.0 \
docker-workflow:1.24 \
jquery-detached:1.2.1 \
cloudbees-folder:6.14 \
workflow-multibranch:2.22 \
okhttp-api:3.14.9 \
workflow-aggregator:2.6 \
apache-httpcomponents-client-4-api:4.5.10-2.0 \
pipeline-stage-step:2.5 \
ssh-slaves:1.31.2 \
gradle:1.36 \
command-launcher:1.5 \
badge:1.8 \
role-strategy:3.1 \
workflow-scm-step:2.11 \
mailer:1.32.1 \
authentication-tokens:1.4 \
matrix-project:1.18 \
antisamy-markup-formatter:2.1 \
jquery3-api:3.5.1-2 \
snakeyaml-api:1.27.0 \
workflow-support:3.6 \
docker-commons:1.17 \
pipeline-github-lib:1.0 \
plain-credentials:1.7 \
jdk-tool:1.4 \
jackson2-api:2.11.3 \
bootstrap4-api:4.5.3-1 \
token-macro:2.12 \
momentjs:1.1.1 \
ssh-agent:1.20 \
pipeline-model-extensions:1.7.2 \
script-security:1.75 \
timestamper:1.11.8 \
git-client:3.5.1 \
github-api:1.116 \
bouncycastle-api:2.18 \
pam-auth:1.6 \
ansicolor:0.7.3 \
popper-api:1.16.0-7 \
font-awesome-api:5.15.1-1 \
"

def plugins = pluginParameter.split()
logger.info("" + plugins)
def instance = Jenkins.getInstance()
def pm = instance.getPluginManager()
def uc = instance.getUpdateCenter()
plugins.each {
  logger.info("Checking " + it)
  if (!pm.getPlugin(it)) {
    logger.info("Looking UpdateCenter for " + it)
    if (!initialized) {
      uc.updateAllSites()
      initialized = true
    }
    def plugin = uc.getPlugin(it)
    if (plugin) {
      logger.info("Installing " + it)
        def installFuture = plugin.deploy()
      while(!installFuture.isDone()) {
        logger.info("Waiting for plugin install: " + it)
        sleep(3000)
      }
      installed = true
    }
  }
}
if (installed) {
  instance.save()
  instance.restart()
}
