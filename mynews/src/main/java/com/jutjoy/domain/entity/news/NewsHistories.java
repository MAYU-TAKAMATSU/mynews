package com.jutjoy.domain.entity.news;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@DynamicUpdate
@Table(name = "news_histories")
@Data
@EntityListeners(AuditingEntityListener.class)
public class NewsHistories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "news_id")
	private Integer newsId;

	@LastModifiedDate
	@Column(name = "edited_date")
	private Timestamp editedDate;

	@ManyToOne //NewsHistoriesに紐づく多対一のテーブルを指定（news_historiesの列を全て取得した際に、それに紐づくNewsテーブルのレコードも取得）
	@JoinColumn(insertable = false, updatable = false) 
	//登録、更新に使用するかを指定。@JoinColumnで指定したカラムをSQLのinsert文、update文に含むか指定できる
	private News news; //テーブルで定義されていないnewsが定義されている
}
