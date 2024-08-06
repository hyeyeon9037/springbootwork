package com.study.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/*@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	//@NonNull
	// Not null이 들어오면 안된다는 소리
	@NonNull
	private int boardno;
	
	//@NonNull
	// 할거면 하나하나씩 위에서 해줌
	private String title;
	private String writer;
	private String content;
	
}
