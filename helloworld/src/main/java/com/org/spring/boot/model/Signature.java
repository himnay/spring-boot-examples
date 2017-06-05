package com.org.spring.boot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Signature
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "user_signature")
public class Signature {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Column(name = "signature")
    private byte[] signature;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    public Signature(byte[] signature, String fileType) {
        this.signature = signature;
        this.fileType = fileType;
    }
}
