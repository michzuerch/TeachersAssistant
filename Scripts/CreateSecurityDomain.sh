#!/usr/bin/env bash
/bin/cp /java/Source/LehrerVerwaltung/usersLehrerVerwaltung.properties /java/wildfly/standalone/configuration/usersLehrerVerwaltung.properties
/bin/cp /java/Source/LehrerVerwaltung/rolesLehrerVerwaltung.properties /java/wildfly/standalone/configuration/rolesLehrerVerwaltung.properties

/java/wildfly/bin/jboss-cli.sh --file=/java/Source/LehrerVerwaltung/Scripts/CreateSecurityDomain.cli
