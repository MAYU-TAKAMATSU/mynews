package com.jutjoy.domain.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutjoy.domain.repository.ProfileRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProfileDeleteService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	public void delete(Integer id) {
		
		//プロフィール削除処理
		profileRepository.deleteById(id);
		
	}
	
}
