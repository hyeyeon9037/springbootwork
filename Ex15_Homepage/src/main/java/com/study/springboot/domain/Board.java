package com.study.springboot.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity(name="board2")
@EntityListeners(AuditingEntityListener.class)
public class Board {
	
	@Id
	@SequenceGenerator (
			name = "boardseq",
			sequenceName = "board2_seq",
			allocationSize = 1
			)
	@GeneratedValue(generator="boardseq")
	private Long bno;
	
	@NonNull
	private String title;
	@NonNull
	private String content;
	@NonNull
	private String writer;
	@Column(insertable=false, columnDefinition="NUMBER DEFAULT 0")
	private Long count;
	
	@CreatedDate
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	@LastModifiedDate
	@Column(name="update_date")
	private LocalDateTime updateDate;
}


/*
@SequenceGenerator
설명: 데이터베이스 시퀀스를 사용하여 엔티티의 기본 키를 생성합니다.
용도: 엔티티의 ID를 자동으로 생성하기 위한 시퀀스 설정에 사용됩니다.

@GeneratedValue
설명: 엔티티의 기본 키 값을 자동으로 생성할 방법을 지정합니다.
용도: 주로 ID 필드를 자동으로 생성하도록 설정할 때 사용됩니다. 일반적으로 @GeneratedValue(strategy = GenerationType.SEQUENCE)와 함께 사용됩니다.

@LastModifiedDate
설명: 엔티티의 마지막 수정 일자를 자동으로 설정합니다.
용도: 엔티티가 마지막으로 수정된 날짜를 자동으로 기록하는 데 사용됩니다.
주로 @EntityListeners(AuditingEntityListener.class)와 함께 사용됩니다.

@NoArgsConstructor
설명: 기본 생성자를 자동으로 생성합니다.
용도: JPA 엔티티나 데이터 클래스에서 기본 생성자를 필요로 할 때 사용됩니다. 

@Column(insertable=false, columnDefinition="NUMBER DEFAULT 0")
JPA 엔티티의 필드를 데이터베이스 열과 매핑할 때 사용하는 어노테이션 속성
*/
