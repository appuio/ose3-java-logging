FROM centos/wildfly

ADD deployments/ROOT.war /opt/wildfly/standalone/deployments/
