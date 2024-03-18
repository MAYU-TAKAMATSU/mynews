package com.jutjoy.domain.entity.news;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="news")
@Data
@EntityListeners(AuditingEntityListener.class)
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "image_name")
    private String imageName;

    @CreatedDate
    @Column(name = "registered_date")
    private Timestamp registeredDate;

    @LastModifiedDate
    @Column(name = "updated_date")
    private Timestamp updatedDate;
    
    //2-6追加
    @OneToMany(mappedBy = "news") //一対多を表すアノテーション、マップする対象フィールドをmappedByで指定
    private List<NewsHistories> histories;
    //型をList<NewsHistories>としているのは、複数のNewsHistoriesを取得する可能性があるから(一対多の関係)
}