package com.study.springboot.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Member {
	@Id
	private String id;
	@NonNull
	private String password;
	@NonNull
	private String name;
	private String email;
	private LocalDate birthday;
	private String gender;
	private String phone;
	private String address;
	
	@CreatedDate
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	@LastModifiedDate
	@Column(name="update_date")
	private LocalDateTime updateDate;
}

/*

@Data: 이 어노테이션은 Lombok 라이브러리에서 제공하는 것으로, 클래스에 대해 getter, setter, toString(), equals(), hashCode() 메서드를 자동으로 생성해줍니다.

@NoArgsConstructor: 이 어노테이션은 Lombok에서 제공하며, 파라미터가 없는 기본 생성자를 자동으로 생성해줍니다. 엔티티 클래스는 JPA에서 기본 생성자를 필요로 하기 때문에 유용합니다.

@Entity: 이 어노테이션은 JPA (Java Persistence API)에서 사용하는 것으로, 해당 클래스가 데이터베이스 테이블에 매핑되는 엔티티임을 나타냅니다.

@EntityListeners: 이 어노테이션은 엔티티의 생명 주기 이벤트를 처리하기 위한 리스너를 지정할 때 사용합니다.
예를 들어, 엔티티가 생성되거나 업데이트될 때 특정 동작을 수행하도록 설정할 수 있습니다. 여기서는 @EntityListeners에 대한 구체적인 리스너가 지정되지 않은 것 같습니다.

@NonNull: 이 어노테이션은 Lombok에서 제공하며, 해당 필드가 null이 될 수 없음을 명시합니다. 이 어노테이션은 메서드 파라미터와 필드에 적용할 수 있습니다.

@CreatedDate: 이 어노테이션은 Spring Data JPA에서 제공하며, 엔티티가 생성된 날짜를 자동으로 기록하도록 설정합니다. 이 어노테이션이 적용된 필드는 엔티티가 처음 저장될 때 자동으로 현재 날짜로 설정됩니다.

@LastModifiedDate: 이 어노테이션은 Spring Data JPA에서 제공하며, 엔티티가 마지막으로 수정된 날짜를 자동으로 기록합니다. 엔티티가 수정될 때마다 이 필드의 값이 업데이트됩니다.

@Column: 이 어노테이션은 JPA에서 제공하며, 데이터베이스 테이블의 컬럼과 엔티티의 필드를 매핑합니다. 이 어노테이션을 통해 컬럼의 이름, 길이, nullable 여부 등 다양한 속성을 설정할 수 있습니다.
 
 
*/