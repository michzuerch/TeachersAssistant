#!/usr/bin/env bash
/bin/cp /java/Source/LehrerVerwaltung/usersLehrerVerwaltung.properties /java/wildfly-11.0.0.Final/standalone/configuration/usersLehrerVerwaltung.properties
/bin/cp /java/Source/LehrerVerwaltung/rolesLehrerVerwaltung.properties /java/wildfly-11.0.0.Final/standalone/configuration/rolesLehrerVerwaltung.properties

/java/wildfly-11.0.0.Final/bin/jboss-cli.sh --file=/java/Source/LehrerVerwaltung/Scripts/CreateSecurityDomain.cli
