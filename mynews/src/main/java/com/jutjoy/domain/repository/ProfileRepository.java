package com.jutjoy.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jutjoy.domain.entity.profile.Profile;

	@Repository /* リポジトリであることを示すアノテーション */
	public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	
		// 課題2-4
		public List<Profile> findAllByOrderById();
		
		//課題2-4
		public List<Profile> findByNameLike(String name);
}
