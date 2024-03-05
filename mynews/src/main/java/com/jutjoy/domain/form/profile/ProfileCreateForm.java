package com.jutjoy.domain.form.profile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProfileCreateForm {
	
	@NotEmpty(message = "＊名前は必ず入力してください。")
	@Size(max = 20, message = "＊名前は20文字以内で入力してください。")
	private String name;
	
	private String gender;

	private String hobby;
	
	private String introduction;

}
