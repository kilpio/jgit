#!groovy

import jenkins.model.*
import java.util.logging.Logger

def logger = Logger.getLogger("")
def installed = false
def initialized = false

def pluginParameter = "\
timestamper \
credentials-binding \
build-timeout \
antisamy-markup-formatter \
cloudbees-folder \
pipeline-stage-view \
pipeline-github-lib \
github-branch-source \
workflow-aggregator \
gradle \
ant \
mailer \
email-ext \
ldap \
pam-auth \
matrix-auth \
ssh-slaves \
github \
git \
groovy \
ansicolor \
authentication-tokens \
badge \
configuration-as-code \
configuration-as-code-groovy \
docker-commons \
docker-workflow \
mask-passwords \
role-strategy \
ws-cleanup \
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
