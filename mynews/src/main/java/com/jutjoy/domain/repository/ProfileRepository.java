package com.jutjoy.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jutjoy.domain.entity.profile.Profile;

	@Repository /* リポジトリであることを示すアノテーション */
	public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	
}
