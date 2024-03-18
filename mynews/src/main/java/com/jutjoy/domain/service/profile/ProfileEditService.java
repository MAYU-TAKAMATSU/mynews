package com.jutjoy.domain.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.entity.profile.ProfileHistories;
import com.jutjoy.domain.form.profile.ProfileEditForm;
import com.jutjoy.domain.repository.ProfileHistoriesRepository;
import com.jutjoy.domain.repository.ProfileRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
@Service
public class ProfileEditService {

	@Autowired
	private ProfileRepository profileRepository;
	
	//2-6追加
	@Autowired
	private ProfileHistoriesRepository profileHistoriesRepository;

	public void edit(int id, ProfileEditForm form) {
		Profile entity = profileRepository.findById(id).get();

		//プロフィール更新処理
		editProfile(entity, form);
		
		//プロフィール編集履歴登録
		registerHistory(entity.getId());

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
	
	//2-6追加
	private void registerHistory(Integer id) {
		ProfileHistories entity = new ProfileHistories();
		entity.setProfileId(id);
		profileHistoriesRepository.save(entity);
	}
}
