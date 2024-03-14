package com.jutjoy.domain.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.form.profile.ProfileEditForm;
import com.jutjoy.domain.repository.ProfileRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
@Service
public class ProfileEditService {

	@Autowired
	private ProfileRepository profileRepository;

	public void edit(int id, ProfileEditForm form) {
		Profile entity = profileRepository.findById(id).get();

		//プロフィール更新処理
		editProfile(entity, form);
	}

	public Profile findProfile(int id) {
		
		//プロフィール編集履歴参照
		Profile profile = profileRepository.findById(id).get();
		return profile;
	}


	private Profile editProfile(Profile entity, ProfileEditForm form) {
		entity.setName(form.getName());
		entity.setGender(form.getGender());
		entity.setHobby(form.getHobby());
		entity.setIntroduction(form.getIntroduction());
		
		return profileRepository.save(entity);
	}
}
