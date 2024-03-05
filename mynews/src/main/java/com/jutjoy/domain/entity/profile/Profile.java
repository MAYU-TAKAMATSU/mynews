package com.jutjoy.domain.entity.profile;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity /*エンティティクラス*/
@Table(name = "profile")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "hobby")
	private String hobby;
	
	@Column(name = "introduction")
	private String introduction;
	
	@CreatedDate
	@Column(name = "registeredDate")
	private Timestamp registeredDate;
	
	@LastModifiedDate
	@Column(name = "updatedDate")
	private Timestamp updatedDate;

}
