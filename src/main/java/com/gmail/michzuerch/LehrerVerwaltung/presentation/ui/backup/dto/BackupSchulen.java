package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.backup.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class BackupSchulen implements Serializable {
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime backupdatum;

    private Set<BackupSchule> backupSchules = new HashSet<>();

    public BackupSchulen() {
    }

    public LocalDateTime getBackupdatum() {
        return backupdatum;
    }

    public void setBackupdatum(LocalDateTime backupdatum) {
        this.backupdatum = backupdatum;
    }

    public Set<BackupSchule> getBackupSchules() {
        return backupSchules;
    }

    public void setBackupSchules(Set<BackupSchule> backupSchules) {
        this.backupSchules = backupSchules;
    }
}
