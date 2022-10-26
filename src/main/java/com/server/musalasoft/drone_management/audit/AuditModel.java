package com.server.musalasoft.drone_management.audit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"created_at", "updated_at", "created_by", "modified_by"},
        allowGetters = true
)
public class AuditModel implements Serializable {
    private static final long serialVersionUID = -357106134453027731L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Colombo")
    private Date createdAt;

    @Column(name = "created_by")
    @CreatedBy
    protected String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Colombo")
    private Date updatedAt;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;
}
