package com.jutjoy.domain.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.form.profile.ProfileCreateForm;
import com.jutjoy.domain.repository.ProfileRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProfileCreateService {

	@Autowired
	private ProfileRepository profileRepository;

	public void create(ProfileCreateForm form) {

		Profile entity = new Profile();
		entity.setName(form.getName());
		entity.setGender(form.getGender());
		entity.setHobby(form.getHobby());
		entity.setIntroduction(form.getIntroduction());
		
		profileRepository.save(entity);

	}

}
